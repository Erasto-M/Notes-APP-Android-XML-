package com.example.login_page;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

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
    static  String timestampToString(Timestamp timestamp){
        return  new SimpleDateFormat("dd/MM/yyyy").format(timestamp.toDate());
    }
}
