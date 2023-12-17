package com.example.afshinahvazics360app;
public class Item {
    private String itemName;
    private int itemCount;

    public Item(String name, int count) {
        this.itemName = name;
        this.itemCount = count;
    }
    public String getItemName() {
        return this.itemName;
    }
    public void setItemName(String name) {
        this.itemName = name;
    }
    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int count) {
        this.itemCount = count;
    }
}
