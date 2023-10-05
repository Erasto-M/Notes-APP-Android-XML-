package com.example.login_page;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class utility {
    static void showToast(Context context, String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
    static CollectionReference getcollectionreference(){
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
      return   FirebaseFirestore.getInstance()
                .collection("Notes").document(currentuser.getUid())
                .collection("My_notes");
    }
}
