package com.example.afshinahvazics360app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridLayout gridLayout;
    private Button buttonAdd;

    private DbOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);
        buttonAdd = findViewById(R.id.buttonAdd);

        // temporary data structure to store data in grid/table
        List<Item> itemList = getDataForGrid();

        // load data into grid
        populateGridLayout(itemList);

        // click listener for the add button for table
        buttonAdd.setOnClickListener(view -> {});

        DbOperations dbOperations = new DbOperations(this);
        dbOperations.open();
        long itemId = dbOperations.insertItem("Sample Item", 10);

        Cursor cursor = dbOperations.getAllItems();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(DbHelper.COLUMN_ID));
                @SuppressLint("Range") String itemName = cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_ITEM_NAME));
                @SuppressLint("Range") int itemCount = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_ITEM_COUNT));

            } while (cursor.moveToNext());
        }
        int updatedRows = dbOperations.updateItemCount(itemId, 15);
        dbOperations.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Ensure the database is closed when the activity is destroyed
        if (dbOperations != null) {
            dbOperations.close();
        }
    }

    // temporary method to retrieve or add data for the table
    private List<Item> getDataForGrid() {
        List<Item> itemList = new ArrayList<>();

        itemList.add(new Item("Item A", 10));
        itemList.add(new Item("Item B", 5));
        itemList.add(new Item("Item C", 0));

        return itemList;
    }

    private void populateGridLayout(List<Item> itemList) {
        // clear existing views in the grid
        gridLayout.removeAllViews();
        for (Item item : itemList) {
            // setting item name
            TextView itemNameTextView = new TextView(this);
            itemNameTextView.setText(item.getItemName());
            itemNameTextView.setGravity(Gravity.CENTER);
            gridLayout.addView(itemNameTextView);

            // setting item count
            TextView itemCountTextView = new TextView(this);
            itemCountTextView.setText(String.valueOf(item.getItemCount()));
            itemCountTextView.setGravity(Gravity.CENTER); // Center text
            gridLayout.addView(itemCountTextView);

            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // delete logic will be added here
                }
            });
            gridLayout.addView(deleteButton);

            Button btnRequestSmsPermission = findViewById(R.id.btnRequestSmsPermission);
            btnRequestSmsPermission.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, SmsPermissionActivity.class);
                startActivity(intent);
            });
        }
    }
}