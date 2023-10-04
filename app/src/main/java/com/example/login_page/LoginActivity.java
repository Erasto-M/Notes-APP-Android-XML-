package com.example.login_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {
    EditText emailedittext, passwordedittext;
    Button loginbtn;
    ProgressBar progressBar;
    TextView createAccounttextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailedittext = findViewById(R.id.emailId);
        passwordedittext = findViewById(R.id.password);
        loginbtn= findViewById(R.id.login_button);
        progressBar =findViewById(R.id.progress_bar);
        createAccounttextview =findViewById(R.id.create_account_textview);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        createAccounttextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(LoginActivity.this,
                                signUp.class));
            }
        });
    }
    void loginUser(){
        String email= emailedittext.getText().toString();
        String password = passwordedittext.getText().toString();
        boolean isvalidated = validatedetails(email,password);
        if(!isvalidated){
            return;
        }
        signInfirebase(email,password);
    }
    void signInfirebase(String email, String password){
        showchangeprogress(true);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        showchangeprogress(false);
                        if(task.isSuccessful()){
                            //Login is Succesful
                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                utility.showToast(LoginActivity.this,
                                        "Login Succesful");
                                // go to main activity
                                startActivity(
                                        new Intent(LoginActivity.this,
                                                MainActivity.class));
                                finish();
                            }else {
                                utility.showToast(LoginActivity.this,
                                        "Email not verified," +
                                                " please verify your email");
                            }
                        }
                        else {
                            // Login is not Successful
                            utility.showToast(LoginActivity.this,
                                    task.getException().getLocalizedMessage());
                        }
                    }
                });
    }
    void showchangeprogress(boolean inprogress){
        if(inprogress){
            progressBar.setVisibility(View.VISIBLE);
            loginbtn.setVisibility(View.GONE);
        } else{
            progressBar.setVisibility(View.GONE);
            loginbtn.setVisibility(View.VISIBLE);
        }
    }
    boolean validatedetails(
            String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailedittext.setError("Invalid Email");
            return  false;
        }
        if(password.length()<8){
            passwordedittext.setError("Invalid password length");
            return false;
        }
        return  true;
    }
}