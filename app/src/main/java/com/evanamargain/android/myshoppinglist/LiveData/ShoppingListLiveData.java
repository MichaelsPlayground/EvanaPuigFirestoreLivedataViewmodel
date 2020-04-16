package com.evanamargain.android.myshoppinglist.LiveData;

import android.util.Log;

import com.evanamargain.android.myshoppinglist.Model.ShoppingItem;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ShoppingListLiveData extends LiveData<List<ShoppingItem>> implements EventListener<DocumentSnapshot> {
  private DocumentReference documentReference;

  private List<ShoppingItem> shoppingListTemp = new ArrayList<>();

  public MutableLiveData<List<ShoppingItem>> shoppingList = new MutableLiveData<List<ShoppingItem>>();

  private ListenerRegistration listenerRegistration = new ListenerRegistration() {
    @Override
    public void remove() {

    }
  };

  public ShoppingListLiveData(DocumentReference documentReference) {
    this.documentReference = documentReference;
  }

  @Override
  protected void onActive() {
    Log.d("TAG", "snapshot listener went to on active");
    listenerRegistration = documentReference.addSnapshotListener(this);
    super.onActive();
  }

  @Override
  protected void onInactive() {
    listenerRegistration.remove();
    super.onInactive();
  }

  public void addItemToList(ShoppingItem item) {
    shoppingListTemp.add(item);
  }

  @Override
  public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
    Log.d("TAG", "on event in live data");
    if(documentSnapshot != null && documentSnapshot.exists()) {

      Map<String, Object> shoppingListItems = documentSnapshot.getData();
      shoppingListTemp.clear();
      for (Map.Entry<String, Object> entry : shoppingListItems.entrySet()) {
        Log.d("TAG", "item to add " + entry.getValue().toString());
        ShoppingItem itemToAdd = new ShoppingItem();
        itemToAdd.setName(entry.getValue().toString());
        addItemToList(itemToAdd);
      }
      shoppingList.setValue(shoppingListTemp);
    }
  }
}
