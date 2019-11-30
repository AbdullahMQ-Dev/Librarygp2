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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class SettingActivity extends AppCompatActivity {
    EditText newusername ;
    Button update ,passnew;
    private FirebaseAuth mAuth ;
    private FirebaseUser currentuser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");
        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser() ;

        newusername = findViewById(R.id.edusernameupdate);
        update = findViewById(R.id.UpdateBtn);
        passnew = findViewById(R.id.forgatePassword);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfo();
            }
        });

        passnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent password = new Intent(getApplicationContext(),ForgetPassActivity.class);
                startActivity(password);
                finish();
            }
        });

    }

    private void updateInfo() {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(newusername.getText().toString()).build();

                currentuser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            ShowMessage("username Changed");
                        }
                    }
                });


        }
    private void ShowMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
    }

