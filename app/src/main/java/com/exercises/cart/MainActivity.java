package com.exercises.cart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.exercises.cart.database.DatabaseAccess;
import com.exercises.cart.listadapters.MenuListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   public static List<MenuItems> cart =new ArrayList<>();
public static  int total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(this);


        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_layout, null);



        Button seeOrderButton = (Button) findViewById(R.id.seeorder_button);
        //getting list of ingredients from the database-------------
        List<MenuItems> allMenuItems = dbAccess.getAllMenuItems();

        ListView listView = (ListView) findViewById(R.id.menu_listView);
        MenuListAdapter adapter = new MenuListAdapter(this, allMenuItems);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openDialog(position);


            }
        });
        listView.setAdapter(adapter);

        seeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cart.class);
                startActivity(intent);
            }
        });


    }

    public void openDialog(int i){
        DialogBox dialogBox = new DialogBox(i);
        dialogBox.show(getSupportFragmentManager(), "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
