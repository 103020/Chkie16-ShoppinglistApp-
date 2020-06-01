package com.example.shoppinglistapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemRecyclerViewActivity extends AppCompatActivity {

    EditText editTextAmount;
    EditText editTextName;
    EditText editTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_recyclerview);

        getSupportActionBar().setTitle("Add Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editTextAmount = (EditText)findViewById(R.id.editTextItemAmountAdd);
        editTextName = (EditText)findViewById(R.id.editTextItemNameAdd);
        editTextDescription = (EditText)findViewById(R.id.editTextItemDescriptionAdd);
    }
    public void sendData(View view){
        Intent data = new Intent();
        data.putExtra("amount", Integer.parseInt(editTextAmount.getText().toString()));
        data.putExtra("name", editTextName.getText().toString());
        data.putExtra("description", editTextDescription.getText().toString());
        setResult(RESULT_OK,data);
        finish();
    }
}
