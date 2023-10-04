package com.example.login_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class signUp extends AppCompatActivity {
    EditText emailedittext, passwordedittext,confirmpasswordedittext;
    Button createaccountbtn;
    ProgressBar progressBar;
    TextView loginbtntextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailedittext = findViewById(R.id.emailId);
        passwordedittext = findViewById(R.id.password);
        confirmpasswordedittext= findViewById(R.id.confirmpassword);
        createaccountbtn= findViewById(R.id.create_account_btn);
        progressBar =findViewById(R.id.progress_bar);
        loginbtntextview =findViewById(R.id.logintextview);
        createaccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creataccount();
            }
        });
        loginbtntextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    void creataccount(){
        String email= emailedittext.getText().toString();
        String password = passwordedittext.getText().toString();
        String confirmpassword = confirmpasswordedittext.getText().toString();
        boolean isvalidated = validatedetails(email,password,confirmpassword);
        if(!isvalidated){
            return;
        }
        createaccountfirebase(email,password);
    }
    void createaccountfirebase(String email,String password){
        showchangeprogress(true);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                signUp.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        showchangeprogress(false);
                        if(task.isSuccessful()){
                            //Account created succesfully
                            utility.showToast(signUp.this,
                                    "Account created succesfully, check email to verify");
                            firebaseAuth.getCurrentUser().sendEmailVerification();
                            firebaseAuth.signOut();
                            finish();
                        }
                        else {
                            //Account creation failed
                            utility.showToast(signUp.this,
                                    task.getException().getLocalizedMessage());
                        }
                    }
                }
        );
    }
    void showchangeprogress(boolean inprogress){
        if(inprogress){
            progressBar.setVisibility(View.VISIBLE);
            createaccountbtn.setVisibility(View.GONE);
        } else{
            progressBar.setVisibility(View.GONE);
            createaccountbtn.setVisibility(View.VISIBLE);
        }
    }
    boolean validatedetails(
            String email, String password, String confirmpassword
    ){
      if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        emailedittext.setError("Invalid Email");
        return  false;
      }
      if(password.length()<8){
          passwordedittext.setError("Invalid password length");
          return false;
      }
      if(!password.equals(confirmpassword)){
          confirmpasswordedittext.setError("Password not matched");
          return false;
      }
      return  true;
    }
}