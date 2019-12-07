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
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassActivity extends AppCompatActivity {
    Button reset ;
    EditText emailtosend ;
    FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        setTitle("Reset Password");
        mAuth = FirebaseAuth.getInstance() ;
        emailtosend = findViewById(R.id.edforgetemailsend);
        reset = findViewById(R.id.resetbtn);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.sendPasswordResetEmail(emailtosend.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            ShowMessage("Email Sent");
                            Intent home = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(home);
                            finish();

                        }else {
                            ShowMessage("reset filed");
                        }
                    }
                });



            }
        });
    }
    private void ShowMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
    private void updateUI() {
        Intent Login = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(Login);
        finish();
    }
}
