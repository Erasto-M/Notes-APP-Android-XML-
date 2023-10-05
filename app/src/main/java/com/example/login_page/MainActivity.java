package com.example.login_page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {
     FloatingActionButton floatingActionButton;
     RecyclerView recyclerView;
     ImageButton menubtn;
     Adapter notedetails;
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
                                NoteAdapter.class)));
        menubtn.setOnClickListener((v) ->showMenu());
    }
    void showMenu(){

    }
    void setupRecycleview(){
        Query query=utility.
                getcollectionreference()
                .orderBy("timestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Note> options
                =new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query,Note.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notedetails = new Adapter(options,this);
        recyclerView.setAdapter(notedetails);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupRecycleview(); // Initialize the RecyclerView and adapter
        notedetails.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        notedetails.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        notedetails.notifyDataSetChanged();
    }
}