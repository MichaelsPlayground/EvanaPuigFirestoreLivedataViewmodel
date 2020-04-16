package com.evanamargain.android.myshoppinglist.LiveData;

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

  private ListenerRegistration listenerRegistration = () -> {};

  public ShoppingListLiveData(DocumentReference documentReference) {
    this.documentReference = documentReference;
  }

  @Override
  protected void onActive() {
    listenerRegistration = documentReference.addSnapshotListener(this);
    super.onActive();
  }

  @Override
  protected void onInactive() {
    listenerRegistration.remove();
    super.onInactive();
  }

  @Override
  public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

    if(documentSnapshot != null && documentSnapshot.exists()) {
      Map<String, Object> shoppingListItems = documentSnapshot.getData();
      shoppingListTemp.clear();

      for (Map.Entry<String, Object> entry : shoppingListItems.entrySet()) {
        ShoppingItem itemToAdd = new ShoppingItem();
        itemToAdd.setName(entry.getValue().toString());
        shoppingListTemp.add(itemToAdd);
      }

      shoppingList.setValue(shoppingListTemp);
    }
  }

}
