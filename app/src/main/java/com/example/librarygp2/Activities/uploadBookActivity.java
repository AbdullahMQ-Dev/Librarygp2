package com.example.librarygp2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.librarygp2.Models.Books;
import com.example.librarygp2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class uploadBookActivity extends AppCompatActivity {
    Button upload ;
    EditText Edtitle,Eddescription ;
    String Catdrop ;
    Spinner categorydopdown;
    private FirebaseAuth mAuth ;
    private FirebaseUser currentuser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_book);
        setTitle("Upload");

        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser() ;

        Edtitle = findViewById(R.id.edtitle);
        Eddescription = findViewById(R.id.eddescription);

        String[] items = new String[]{"Design", "Database", "Network","Security","Programing"};
         categorydopdown =  findViewById(R.id.catdopdown);
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this ,
                android.R.layout.simple_spinner_item, items);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorydopdown.setAdapter(categoriesAdapter);
        categorydopdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Catdrop = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        upload = findViewById(R.id.UploadBtn);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Edtitle.getText().toString().isEmpty() && !Eddescription.getText().toString().isEmpty() ){

                    Books books = new Books(Edtitle.getText().toString(),Eddescription.getText().toString(),Catdrop,currentuser.getUid(),currentuser.getDisplayName());
                    addBook(books);

                }else {
                    ShowMessage("please Fill all field");
                }
            }
        });

    }

    private void addBook(Books books) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Books").push();
        String key = myRef.getKey() ;
        books.setBookKey(key);

        myRef.setValue(books).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                     ShowMessage("Book Added Success");
                     updateUI();
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
}
