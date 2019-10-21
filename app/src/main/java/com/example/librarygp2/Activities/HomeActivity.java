package com.example.librarygp2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.librarygp2.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private Button allbooks , category,setting, upload,logout ;
    private FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");
        allbooks = findViewById(R.id.allbookd_btn);
        category = findViewById(R.id.cat_btn);
        setting = findViewById(R.id.setting_btn);
        upload = findViewById(R.id.uploadbook_btn);
        logout = findViewById(R.id.logout_btn);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uploadbook = new Intent(getApplicationContext(),uploadBookActivity.class);
                startActivity(uploadbook);
            }
        });

        allbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allbooks = new Intent(getApplicationContext(),AllBooksActivity.class);
                startActivity(allbooks);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(login);
                finish();
            }
        });

    }

}
