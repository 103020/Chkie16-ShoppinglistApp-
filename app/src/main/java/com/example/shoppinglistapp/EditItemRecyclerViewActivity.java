package com.example.shoppinglistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditItemRecyclerViewActivity extends AppCompatActivity {

    EditText editTextAmount;
    EditText editTextName;
    EditText editTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_recyclerview_fragment);

        getSupportActionBar().setTitle("Edit Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editTextAmount = (EditText)findViewById(R.id.editTextItemAmountEdit);
        editTextName = (EditText)findViewById(R.id.editTextItemNameEdit);
        editTextDescription = (EditText)findViewById(R.id.editTextItemDescriptionEdit);
    }

    public void editData(View view){
        Intent data = new Intent();
        int tempAmount;
        String tempname;
        String tempDescription;
        if (editTextAmount.getText().toString().equals(null)) {
            data.putExtra("amount", 1);
        } else {
            data.putExtra("amount", Integer.parseInt(editTextAmount.getText().toString()));
        }
        if (editTextAmount.getText().toString().equals(null)) {
            data.putExtra("amount", 1);
        } else {
            data.putExtra("name", editTextName.getText().toString());
        }
        if (editTextAmount.getText().toString().equals(null)) {
            data.putExtra("amount", 1);
        } else {
            data.putExtra("description", editTextDescription.getText().toString());
        }


        setResult(RESULT_OK,data);
        finish();
    }
}
