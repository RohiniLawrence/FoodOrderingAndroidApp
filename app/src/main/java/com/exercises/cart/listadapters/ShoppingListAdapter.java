package com.exercises.cart.listadapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.exercises.cart.MenuItems;
import com.exercises.cart.R;

import java.util.List;

public class ShoppingListAdapter extends ArrayAdapter<MenuItems> {

    List<MenuItems> cartItems;
    Context context;



    public ShoppingListAdapter(@NonNull Context _context, List<MenuItems> _cartItems) {
        super(_context, R.layout.row, R.id.title_textView, _cartItems);
        this.context = _context;
        this.cartItems = _cartItems;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);

        ImageView images = row.findViewById(R.id.img);
        TextView myTitle = row.findViewById(R.id.title_textView);
        TextView myDes = row.findViewById(R.id.description_textView);
        TextView myPrice = row.findViewById(R.id.price_textView);


        myTitle.setText(cartItems.get(position).getName());
        myDes.setText(String.valueOf(cartItems.get(position).getQuantity()) +"*"+String.valueOf(cartItems.get(position).getPrice())+"="+(cartItems.get(position).getQuantity()) +(cartItems.get(position).getPrice()));
myPrice.setVisibility(View.GONE);



        byte[] byteArray = cartItems.get(position).getImage();
        if (byteArray != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            images.setImageBitmap(bitmap);
        }




        return row;
    }

}
