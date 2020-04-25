package com.exercises.cart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.MenuItem;

import com.exercises.cart.MenuItems;

import java.util.ArrayList;


public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instances;
    Cursor c = null;


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instances == null) {
            instances = new DatabaseAccess(context);
        }
        return instances;
    }


    public void close() {
        if (db != null) {
            this.db.close();
        }
    }


    //------------getting items-----------------------
    public ArrayList<MenuItems> getAllMenuItems() {


        ArrayList<MenuItems> all_items = new ArrayList<>();

        this.db = openHelper.getReadableDatabase();
        Cursor res = db.rawQuery("select * from menu", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            MenuItems item = new MenuItems();
            item.setName(res.getString(res.getColumnIndex("name")));
            item.setDetailes(res.getString(res.getColumnIndex("details")));
            item.setImage(res.getBlob(res.getColumnIndex("image")));
            item.setPrice(res.getInt(res.getColumnIndex("price")));
            item.setId(res.getInt(res.getColumnIndex("id")));

            all_items.add(item);
            res.moveToNext();
        }
        return all_items;
    }


    //------------getting items-----------------------
    public MenuItems getMenuItem(int id) {

        this.db = openHelper.getReadableDatabase();


        MenuItems item = new MenuItems();

        Cursor res = db.rawQuery("SELECT * FROM menu WHERE  id = ?", new String[] {String.valueOf(id)});
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            item.setName(res.getString(res.getColumnIndex("name")));
            item.setDetailes(res.getString(res.getColumnIndex("details")));
            item.setImage(res.getBlob(res.getColumnIndex("image")));
            item.setPrice(res.getInt(res.getColumnIndex("price")));
            item.setId(res.getInt(res.getColumnIndex("id")));
            res.moveToNext();
        }

        return item;
    }


}
