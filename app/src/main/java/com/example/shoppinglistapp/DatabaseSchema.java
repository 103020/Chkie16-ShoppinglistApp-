package com.example.shoppinglistapp;

import android.provider.BaseColumns;

public class DatabaseSchema {

    private DatabaseSchema() {};

    public static class Schemas implements BaseColumns {
        public static final String TABLE_NAME = "items";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_AMOUNT = "amount";
    }
}
