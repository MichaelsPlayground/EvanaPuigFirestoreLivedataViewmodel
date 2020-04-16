package com.evanamargain.android.myshoppinglist.ViewModel;

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
    liveData = repository.getFirestoreLiveData();
    return liveData;
  }

  public LiveData<List<ShoppingItem>> getShoppingList() {
    return liveData.shoppingList;
  }
}
