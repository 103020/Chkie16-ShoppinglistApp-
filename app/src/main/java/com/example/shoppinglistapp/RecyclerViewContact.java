package com.example.shoppinglistapp;

import java.util.ArrayList;

public class RecyclerViewContact {
    private String name;
    private String description;
    private int amount;

    public RecyclerViewContact(String name, String description, int amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getAmount(){return amount;}

    //method to add test data to the RecyclerList
    public static ArrayList<RecyclerViewContact> createContactsList(int numItems) {
        ArrayList<RecyclerViewContact> contacts = new ArrayList<RecyclerViewContact>();

        for (int i = 1; i <= numItems; i++) {
            contacts.add(new RecyclerViewContact("Item " + i, "Description " + i, 1));
        }

        return contacts;
    }
}
