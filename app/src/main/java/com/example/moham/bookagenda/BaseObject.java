package com.example.moham.bookagenda;

import java.util.ArrayList;

public class BaseObject {

    private ArrayList<BookItem> items;

    public BaseObject(ArrayList<BookItem> items) {
        this.items = items;
    }

    public ArrayList<BookItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<BookItem> items) {
        this.items = items;
    }
}
