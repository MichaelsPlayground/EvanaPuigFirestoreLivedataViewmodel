package com.evanamargain.android.myshoppinglist.ViewModel;

import android.util.Log;

import com.evanamargain.android.myshoppinglist.LiveData.ShoppingListLiveData;
import com.evanamargain.android.myshoppinglist.Model.ShoppingItem;
import com.evanamargain.android.myshoppinglist.Repository.ShoppingListRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ShoppingListViewModel extends ViewModel {
  private ShoppingListRepository repository = new ShoppingListRepository();
  public MutableLiveData<List<ShoppingItem>> shoppingList = new MutableLiveData<List<ShoppingItem>>();
  ShoppingListLiveData liveData = new ShoppingListLiveData(null);

  public LiveData<List<ShoppingItem>> getShoppingListLiveData() {
    Log.d("TAG", "view model triggers the repo ");
    liveData = repository.getFirestoreLiveData();
    shoppingList.setValue(liveData.shoppingList.getValue());
    return liveData;
  }

  public LiveData<List<ShoppingItem>> getShoppingList() {
    Log.d("TAG", "view model check the list too ");
    return liveData.shoppingList;
  }
}
