package com.example.login_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
     FloatingActionButton floatingActionButton;
     RecyclerView recyclerView;
     ImageButton menubtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatingActionButton = findViewById(R.id.addnote_btn);
        recyclerView = findViewById(R.id.recycler_view);
        menubtn = findViewById(R.id.menu_bar);
        floatingActionButton.setOnClickListener((v) ->
                startActivity(
                        new Intent(MainActivity.this,
                                notedetails.class)));
        menubtn.setOnClickListener((v) ->showMenu());
    }
    void showMenu(){

    }
}