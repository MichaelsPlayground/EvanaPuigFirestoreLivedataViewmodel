package com.evanamargain.android.myshoppinglist.Repository;

import com.evanamargain.android.myshoppinglist.LiveData.ShoppingListLiveData;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class ShoppingListRepository {
  private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

  public ShoppingListLiveData getFirestoreLiveData() {
    DocumentReference documentReference = firebaseFirestore
                                              .collection("MyGroceries")
                                              .document("March26-2020");

    return new ShoppingListLiveData(documentReference);
  }
}