package com.evanamargain.android.myshoppinglist.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.evanamargain.android.myshoppinglist.Model.ShoppingItem;

import java.util.ArrayList;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingItem> {
  public ShoppingListAdapter(Context context, ArrayList<ShoppingItem> items) {
    super(context, 0, items);
  }

  public View getView(int position, View convertView, ViewGroup parent) {

    ShoppingItem item = getItem(position);

    if (convertView == null) {
      convertView = LayoutInflater.from(this.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
    }

    TextView tvName = (TextView) convertView.findViewById(android.R.id.text1);
    tvName.setText(item.getName());

    return convertView;
  }
}