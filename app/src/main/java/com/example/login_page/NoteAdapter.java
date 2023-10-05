package com.example.login_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.Date;

public class NoteAdapter extends AppCompatActivity {
    EditText titleedittext, contenteditText;
    ImageButton savenotebutton;
    TextView pagetitletextview;
    String title,content,docId;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedetails);
        titleedittext = findViewById(R.id.note_title);
        contenteditText = findViewById(R.id.note_content_title);
        savenotebutton = findViewById(R.id.save_new_note);
        pagetitletextview = findViewById(R.id.page_title);
        //recieve data
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");
        if(docId!=null && !docId.isEmpty()){
            isEditMode = true;
        }
        titleedittext.setText(title);
        contenteditText.setText(content);
        if (isEditMode) {
            pagetitletextview.setText("Edit Your Note");
        } else {
            pagetitletextview.setText("Add New Note");
        }

        savenotebutton.setOnClickListener((v) -> saveNote());

        savenotebutton.setOnClickListener((v) -> saveNote());
    }

    void saveNote() {
        String notetitle = titleedittext.getText().toString();
        String notecontent = contenteditText.getText().toString();

        if (notetitle == null || notetitle.isEmpty()) {
            titleedittext.setError("Title is required");
            return; // Stop execution if title is not provided
        }

        if (notecontent == null || notecontent.isEmpty()) {
            contenteditText.setError("Content is required");
            return; // Stop execution if content is not provided
        }

        Note note = new Note();
        note.setTitle(notetitle);
        note.setContent(notecontent);

        // Use Firestore's Timestamp class
        note.setTimestamp(Timestamp.now());

        savenotetofirebase(note);
        finish();
    }

    void savenotetofirebase(Note note) {
        DocumentReference documentReference;
        if(isEditMode){
            //update the note
            documentReference = utility.getcollectionreference().document(docId);
        }else {
            //create new note
            documentReference = utility.getcollectionreference().document();
        }
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Note is added successfully
                    utility.showToast(NoteAdapter.this, "Note added successfully");
                } else {
                    // Note not added
                    utility.showToast(NoteAdapter.this, "Failed while adding note");
                }
            }
        });
    }
}
