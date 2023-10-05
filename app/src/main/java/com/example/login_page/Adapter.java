package com.example.login_page;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions; // Import this if you are using FirestoreRecyclerOptions.

public class Adapter extends FirestoreRecyclerAdapter<Note,Adapter.NoteHolder> {
    Context context;
    public Adapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull Note model) {

    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, contentTextView, timestampTextView;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title_view);
            contentTextView = itemView.findViewById(R.id.content_view);
            timestampTextView = itemView.findViewById(R.id.time_view);
        }
    }
}
