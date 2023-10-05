package com.example.login_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.sql.Timestamp;
import java.util.Date;
public class notedetails extends AppCompatActivity {
    EditText titleedittext, contenteditText;
    ImageButton savenotebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedetails);
        titleedittext = findViewById(R.id.note_title);
        contenteditText = findViewById(R.id.note_content_title);
        savenotebutton = findViewById(R.id.save_new_note);
        savenotebutton.setOnClickListener((v)->saveNote());
    }
    void saveNote(){
        String notetitle = titleedittext.getText().toString();
        String notecontent = contenteditText.getText().toString();
        if(notetitle==null || notetitle.isEmpty()){
            titleedittext.setError("Title is required");
        }
        if(notecontent == null || notecontent.isEmpty()){
            contenteditText.setError("Content is required");
        }
        note Note = new note();
        Note.setTitle(notetitle);
        Note.setContent(notecontent);
        Note.setTimestamp(new Timestamp(new Date().getTime()));
        savenotetofirebase(Note);
    }
    void savenotetofirebase(note Note){
        DocumentReference documentReference;
        documentReference = utility.getcollectionreference().document();
        documentReference.set(Note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //note is added successfully
                    utility.showToast(notedetails.this,"Notes added succesfully");
                } else{
                    // notes not added
                    utility.showToast(notedetails.this,
                            "Failed while adding notes");
                }
            }
        });
    }
}