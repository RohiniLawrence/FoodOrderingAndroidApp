package com.exercises.cart;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.exercises.cart.database.DatabaseAccess;

import java.util.List;

public class DialogBox extends AppCompatDialogFragment {

    private int i;
    private int quant ;




    DatabaseAccess dbAccess = DatabaseAccess.getInstance(getActivity());
    MenuItems mi = new MenuItems();

    public DialogBox(int i) {
        this.i = i;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);

        final TextView quantityTv = (TextView) view.findViewById(R.id.quantity_textView);
        Button plus = (Button) view.findViewById(R.id.plus_button);
        Button minus = (Button) view.findViewById(R.id.minus_button);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quant++;
              quantityTv.setText(String.valueOf(quant));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quant>0){
                quant--;
                quantityTv.setText(String.valueOf(quant));
                }
            }
        });



        builder.setView(view)
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext().getApplicationContext(),"No changes made!",Toast.LENGTH_SHORT).show();


            }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getContext().getApplicationContext(),"Added to cart!",Toast.LENGTH_SHORT).show();
                mi = dbAccess.getMenuItem(i+1);

                mi.setQuantity(quant);


                MainActivity.cart.add(mi);



            }
        });
        return builder.create();

    }




}


