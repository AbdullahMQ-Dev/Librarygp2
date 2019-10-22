package com.example.librarygp2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.librarygp2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText usereamil,userpassword ;
    private Button signin_btn , signup_btn ,forget;
    private FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usereamil = findViewById(R.id.edemail);
        userpassword = findViewById(R.id.edpassword);
        signin_btn = findViewById(R.id.loin_btn);
        signup_btn = findViewById(R.id.signupBtn);
        mAuth = FirebaseAuth.getInstance();

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regAct = new Intent(getApplicationContext(),Register.class);
                startActivity(regAct);
                finish();
            }


        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = usereamil.getText().toString();
                final String password = userpassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()){
                    ShowMessage("Please fill all field");
                }else {
                    SignIn(email,password);
                }
            }
        });

        forget = findViewById(R.id.forgetpass);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ForgetPass = new Intent(getApplicationContext(),ForgetPassActivity.class);
                startActivity(ForgetPass);
            }
        });
    }

    private void SignIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   updateUI();
               }else {
                   ShowMessage(task.getException().getMessage());
               }
            }
        });
    }

    private void ShowMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser() ;

        if (user != null){
            updateUI();
        }

    }
}
