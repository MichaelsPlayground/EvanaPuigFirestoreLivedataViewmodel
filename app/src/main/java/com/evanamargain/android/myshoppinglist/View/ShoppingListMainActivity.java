package com.evanamargain.android.myshoppinglist.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.evanamargain.android.myshoppinglist.Model.ShoppingItem;
import com.evanamargain.android.myshoppinglist.R;
import com.evanamargain.android.myshoppinglist.ViewModel.ShoppingListViewModel;

import java.util.ArrayList;

public class ShoppingListMainActivity extends AppCompatActivity {
  ShoppingListViewModel model;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ProgressBar progressBar = findViewById(R.id.progressbar);
    progressBar.setVisibility(View.VISIBLE);

    model = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ShoppingListViewModel.class);

    ArrayList<ShoppingItem> arrayOfItems = new ArrayList<>();
    ShoppingListAdapter adapter = new ShoppingListAdapter(this, arrayOfItems);

    model.getShoppingListLiveData().observe(this, Observable -> {});

    model.getShoppingList().observe(this, shoppingList -> {

      if (shoppingList != null ) {
        adapter.clear();
        adapter.addAll(shoppingList);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        progressBar.setVisibility(View.GONE);
      } else {

        Log.d("TAG", "awaiting for info");

      }
    });
  }
}
