package com.example.shoppinglistapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class ItemDetailFragment extends Fragment {
    final static String ARG_POSITION = "position";
    final static String ARG_ITEM_AMOUNT = "amount";
    final static String ARG_ITEM_NAME = "name";
    final static String ARG_ITEM_DESCRIPTION = "description";
    RecyclerViewContact rvc;
    int mCurrentPosition = -1;
    private View tvItem;

    TextView editTextAmount;
    TextView editTextName;
    TextView editTextDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
            int tempAmount = savedInstanceState.getInt(ARG_ITEM_AMOUNT);
            String tempName = savedInstanceState.getString(ARG_ITEM_NAME);
            String tempDescription = savedInstanceState.getString(ARG_ITEM_DESCRIPTION);
            rvc = new RecyclerViewContact(tempName,tempDescription,tempAmount);
        }
        tvItem = inflater.inflate(R.layout.item_recyclerview_fragment, container, false);
        tvItem.findViewById(R.id.editTextItemAmountEdit).findViewById(R.id.editTextItemAmountEdit);
        editTextAmount = (TextView) tvItem.findViewById(R.id.editTextItemAmountEdit).findViewById(R.id.editTextItemAmountEdit);
        editTextName = (TextView) tvItem.findViewById(R.id.editTextItemAmountEdit).findViewById(R.id.editTextItemNameEdit);
        editTextDescription = (TextView) tvItem.findViewById(R.id.editTextItemAmountEdit).findViewById(R.id.editTextItemDescriptionEdit);

        /*editTextAmount.setText(rvc.getAmount());
        editTextName.setText(rvc.getName());
        editTextDescription.setText(rvc.getDescription());*/
        return tvItem;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateItemView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateItemView(mCurrentPosition);
        }
    }

    public void updateItemView(int position) {

        tvItem.findViewById(R.id.item_name);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
        int tempAmount = -1;
        String tempName = "";
        String tempDescription = "";
        outState.putInt(ARG_ITEM_AMOUNT,tempAmount);
        outState.putString(ARG_ITEM_NAME,tempName);
        outState.putString(ARG_ITEM_DESCRIPTION,tempDescription);
        rvc = new RecyclerViewContact(tempName,tempDescription,tempAmount);
    }
}
