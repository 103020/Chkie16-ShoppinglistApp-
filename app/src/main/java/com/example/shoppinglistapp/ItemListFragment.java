package com.example.shoppinglistapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemListFragment extends Fragment {

    private View tvItemList;
    ItemAdapter itemAdapter;
    ArrayList<RecyclerViewContact> contacts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        tvItemList = inflater.inflate(R.layout.item_list_fragment, container, false);

        RecyclerView recyclerView = tvItemList.findViewById(R.id.item_list_fragment_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        contacts = loadDatabase();

        itemAdapter = new ItemAdapter(contacts);
        recyclerView.setAdapter(itemAdapter);


        return tvItemList;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume");
        contacts = loadDatabase();
        itemAdapter.notifyItemChanged(contacts.size());
    }

    public ArrayList<RecyclerViewContact> loadDatabase(){
        DatabaseManager manager = new DatabaseManager(getActivity());
        SQLiteDatabase db = manager.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseSchema.Schemas.TABLE_NAME, null);

        ArrayList<RecyclerViewContact> items = new ArrayList<RecyclerViewContact>();
        while(cursor.moveToNext()) {
            int itemId = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseSchema.Schemas._ID));
            String itemName = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseSchema.Schemas.COLUMN_NAME_NAME));
            String itemDescription = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseSchema.Schemas.COLUMN_NAME_DESCRIPTION));
            int itemAmount = cursor.getInt(
                    cursor.getColumnIndexOrThrow(DatabaseSchema.Schemas.COLUMN_NAME_AMOUNT));
            items.add(new RecyclerViewContact(itemName,itemDescription,itemAmount));
        }
        cursor.close();
        return items;
    }
    public void addItem(RecyclerViewContact rvc) {
        contacts.add(rvc);

        int listSize = contacts.size();
        itemAdapter.notifyItemChanged(listSize);
    }

    public void updateList(){
        int listSize = contacts.size();
        contacts.clear();
        itemAdapter.notifyItemRangeRemoved(0,listSize);
    }
}
