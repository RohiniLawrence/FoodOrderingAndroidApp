package com.exercises.cart;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.exercises.cart.listadapters.ShoppingListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {


    List<MenuItems> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        cartItems.addAll( MainActivity.cart);



        if(cartItems!=null){
           /* Log.i(TAG, "LIST NOT NULL");*/
        ListView listView = (ListView) findViewById(R.id.cart_listView);
        ShoppingListAdapter adapter = new ShoppingListAdapter(this, MainActivity.cart);
listView.setAdapter(adapter);



        }







    }
    public void addToCart(MenuItems m){
     /* Log.i(TAG, "ADDING TO LIST");*/
        cartItems.add(m);
    }
}
