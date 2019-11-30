package com.example.librarygp2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.librarygp2.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private Button home , category,setting, upload,logout,boot ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_v2);
        setTitle("Home");
        home = findViewById(R.id.AllBooksV2_btn);
        category = findViewById(R.id.CategoryV2_btn);
        setting = findViewById(R.id.SettingV2_btn);
        upload = findViewById(R.id.UploadV2_btn);
        logout = findViewById(R.id.LogoutV2_btn);
        boot = findViewById(R.id.BootV2_btn);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uploadbook = new Intent(getApplicationContext(),uploadBookActivity.class);
                startActivity(uploadbook);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
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

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent category = new Intent(getApplicationContext(),PickCategoreyActivity.class);
                startActivity(category);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(setting);
            }
        });
        boot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent boot =new Intent(getApplicationContext(),Bot.class);
                startActivity(boot);
            }
        });
    }


}
