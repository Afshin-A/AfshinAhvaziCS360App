package com.example.afshinahvazics360app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbOperations {

    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public DbOperations(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertItem(String itemName, int itemCount) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_ITEM_NAME, itemName);
        values.put(DbHelper.COLUMN_ITEM_COUNT, itemCount);
        return database.insert(DbHelper.TABLE_ITEMS, null, values);
    }

    public Cursor getAllItems() {
        String[] columns = {DbHelper.COLUMN_ID, DbHelper.COLUMN_ITEM_NAME, DbHelper.COLUMN_ITEM_COUNT};
        return database.query(DbHelper.TABLE_ITEMS, columns, null, null, null, null, null);
    }

    public int updateItemCount(long itemId, int newCount) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_ITEM_COUNT, newCount);
        String whereClause = DbHelper.COLUMN_ID + "=?";
        String[] whereArgs = {String.valueOf(itemId)};
        return database.update(DbHelper.TABLE_ITEMS, values, whereClause, whereArgs);
    }
}
