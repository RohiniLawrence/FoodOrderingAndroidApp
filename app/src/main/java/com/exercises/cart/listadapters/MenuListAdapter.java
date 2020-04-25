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

public class MenuListAdapter extends ArrayAdapter<MenuItems> {

    List<MenuItems> menuItems;
    Context context;

    public MenuListAdapter(@NonNull Context _context, List<MenuItems> _menuItems) {
        super(_context, R.layout.row, R.id.title_textView, _menuItems);
        this.context = _context;
        this.menuItems = _menuItems;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);

        ImageView images = row.findViewById(R.id.img);
        TextView myTitle = row.findViewById(R.id.title_textView);
        TextView myDes = row.findViewById(R.id.description_textView);
        TextView myPrice = row.findViewById(R.id.price_textView);

        myTitle.setText(menuItems.get(position).getName());
        myDes.setText(menuItems.get(position).getDetailes());
        myPrice.setText("Price:\n"+String.valueOf(menuItems.get(position).getPrice()));

        byte[] byteArray = menuItems.get(position).getImage();
        if (byteArray != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            images.setImageBitmap(bitmap);
        }

        return row;
    }

}
