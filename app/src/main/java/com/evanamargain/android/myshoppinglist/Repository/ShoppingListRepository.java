package com.evanamargain.android.myshoppinglist.Repository;

import android.util.Log;

import com.evanamargain.android.myshoppinglist.LiveData.ShoppingListLiveData;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class ShoppingListRepository {
  private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

  public ShoppingListLiveData getFirestoreLiveData() {
    Log.d("TAG", "the repo creates an instance of live data ");
    DocumentReference documentReference = firebaseFirestore.collection("MyGroceries").document("March26-2020");
    return new ShoppingListLiveData(documentReference);
  }
}