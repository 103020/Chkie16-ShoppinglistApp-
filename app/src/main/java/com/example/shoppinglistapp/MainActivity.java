package com.example.shoppinglistapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<RecyclerViewContact> contacts;
    ItemAdapter itemAdapter;
    int requestCode;
    static int editRequestCode;
    ItemListFragment ListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        contacts = loadDatabase();

        //creates test data for the list
        //contacts.addAll(RecyclerViewContact.createContactsList(20);

        itemAdapter = new ItemAdapter(contacts);
        /*recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/

        ItemListFragment ListFragment = new ItemListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_layout, ListFragment);

        transaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            loadDatabase();
            return true;
        } else if (id == R.id.action_clearList) {
            ListFragment.updateList();
            emptyDatabase();
            //Snackbar.make(findViewById(R.id.recyclerView), "List cleared", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, AddItemRecyclerViewActivity.class);
        requestCode = 10;
        startActivityForResult(intent,requestCode);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == this.requestCode) {
            if (resultCode == RESULT_OK) {
                int amount = data.getIntExtra("amount",1);
                String name = data.getStringExtra("name");
                String description = data.getStringExtra("description");

                RecyclerViewContact tempContact = new RecyclerViewContact(name, description, amount);

                addToDatabase(tempContact);
                ItemListFragment temp = (ItemListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
                System.out.println(temp);
                if (temp != null && temp.isAdded()){
                    temp.contacts.add(tempContact);
                    temp.itemAdapter.notifyItemChanged(temp.contacts.size());
                }
                //ListFragment.addItem(tempContact);
            }
        }
    }

    public void addToDatabase(RecyclerViewContact contact) {

        System.out.println("addToDatabase");
        DatabaseManager manager = new DatabaseManager(this);
        SQLiteDatabase db = manager.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseSchema.Schemas.COLUMN_NAME_NAME, contact.getName());
        values.put(DatabaseSchema.Schemas.COLUMN_NAME_DESCRIPTION, contact.getDescription());
        values.put(DatabaseSchema.Schemas.COLUMN_NAME_AMOUNT, contact.getAmount());

        db.insert(DatabaseSchema.Schemas.TABLE_NAME, null, values);
    }

    public  ArrayList<RecyclerViewContact> loadDatabase(){
        DatabaseManager manager = new DatabaseManager(this);
        SQLiteDatabase db = manager.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseSchema.Schemas.TABLE_NAME, null);

        //DEBUG: to show the number of columns and rows that are in the database
        /*Snackbar.make(
                findViewById(R.id.recyclerView),
                "There is: " + cursor.getColumnCount() + " Columns. \nAnd there is: " + cursor.getCount() + " Rows",
                Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/

        ArrayList<RecyclerViewContact> items = new ArrayList<RecyclerViewContact>();
        while(cursor.moveToNext()) {
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

    public void emptyDatabase() {
        DatabaseManager manager = new DatabaseManager(this);
        SQLiteDatabase db = manager.getReadableDatabase();

        db.delete(DatabaseSchema.Schemas.TABLE_NAME,null,null);
    }

    public void onItemSelected(int position) {

        ItemListFragment itemFrag = (ItemListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_layout);

        if (itemFrag != null) {

            System.out.println("entered here");
            //itemFrag.updateItemView(position);
            ItemDetailFragment newFragment = new ItemDetailFragment();
            Bundle args = new Bundle();
            args.putInt(ItemDetailFragment.ARG_POSITION, position);
            args.putInt(ItemDetailFragment.ARG_ITEM_AMOUNT, contacts.get(position).getAmount());
            args.putString(ItemDetailFragment.ARG_ITEM_NAME, contacts.get(position).getName());
            args.putString(ItemDetailFragment.ARG_ITEM_DESCRIPTION, contacts.get(position).getDescription());
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_layout, newFragment);

            transaction.commit();


        } else {

            ItemDetailFragment newFragment = new ItemDetailFragment();
            Bundle args = new Bundle();
            args.putInt(ItemDetailFragment.ARG_POSITION, position);
            args.putInt(ItemDetailFragment.ARG_ITEM_AMOUNT, contacts.get(position).getAmount());
            args.putString(ItemDetailFragment.ARG_ITEM_NAME, contacts.get(position).getName());
            args.putString(ItemDetailFragment.ARG_ITEM_DESCRIPTION, contacts.get(position).getDescription());
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.add(R.id.fragment_layout, newFragment);

            transaction.commit();
        }
    }
}
