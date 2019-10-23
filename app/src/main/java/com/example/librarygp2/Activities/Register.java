package com.example.librarygp2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity {

    private EditText usernameAct,useremail,userpassword ;
    private Button regbtn ;
    private FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Sign Up");
        usernameAct = findViewById(R.id.ETusername);
        useremail = findViewById(R.id.ETemail);
        userpassword = findViewById(R.id.ETpassword);
        regbtn = findViewById(R.id.signup_btn) ;

        mAuth = FirebaseAuth.getInstance() ;

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameAct.getText().toString();
                final String email = useremail.getText().toString();
                final String password = userpassword.getText().toString();

                if (email.isEmpty() || username.isEmpty() || password.isEmpty()){
                    ShowMessage("Please fill All the fields ");
                }else {
                    
                    CreateUserAccount(username,email,password);
                    
                }



            }
        });

    }


    private void CreateUserAccount(final String username, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                ShowMessage("Account Created");
                                UpdateUserInfo(username,mAuth.getCurrentUser());

                            }else if (!task.isSuccessful()){
                                ShowMessage("Email is used ");
                            }
                    }
                });

    }

    private void UpdateUserInfo(String username, FirebaseUser currentUser) {
        UserProfileChangeRequest profileupdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(username).build();

        currentUser.updateProfile(profileupdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            ShowMessage("Register Complete");
                            updateUI();
                        }
                    }
                });
    }

    private void updateUI() {
        Intent homeActivity = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }

    private void ShowMessage(String message_) {
        Toast.makeText(getApplicationContext(),message_,Toast.LENGTH_LONG).show();
    }
}
