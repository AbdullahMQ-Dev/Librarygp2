package com.example.librarygp2.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.librarygp2.Models.Books;
import com.example.librarygp2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class uploadBookActivity extends AppCompatActivity {
    Button upload ;
    EditText Edtitle,Eddescription ;
    String Catdrop ;
    Spinner categorydopdown;
    ImageView picupload ;
    private FirebaseAuth mAuth ;
    private FirebaseUser currentuser ;
    private static final int PReqCode = 2 ;
    private static final int REQUESCODE = 1 ;
    private Uri pickImguri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_book);
        setTitle("Upload");

        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser() ;

        Edtitle = findViewById(R.id.edtitle);
        Eddescription = findViewById(R.id.eddescription);
        picupload = findViewById(R.id.imageButtonUpload);


        picupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permastionForphoto();
            }
        });


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
                if (!Edtitle.getText().toString().isEmpty() && !Eddescription.getText().toString().isEmpty() && pickImguri != null){
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Books_image");
                    final StorageReference imgFilePath = storageReference.child(pickImguri.getLastPathSegment());

                    imgFilePath.putFile(pickImguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imgFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownloadLink = uri.toString();
                                    Books books = new Books(Edtitle.getText().toString(),imageDownloadLink,Eddescription.getText().toString(),Catdrop,currentuser.getUid(),currentuser.getDisplayName());
                                    addBook(books);

                                }
                            });
                        }
                    });


                }else {
                    ShowMessage("please Fill all field");
                }
            }
        });

//        ImageClick();

    }

//    private void ImageClick() {
//        picupload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                permastionForphoto();
//
//            }
//        });
//    }

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

    private void permastionForphoto(){
        if (ContextCompat.checkSelfPermission(uploadBookActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(uploadBookActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                ShowMessage("Pleas accept permission");
            }
            else {
                ActivityCompat.requestPermissions(uploadBookActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PReqCode);
            }

        }
        else {
            openGallery();
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null){

            pickImguri = data.getData();
            picupload.setImageURI(pickImguri);


        }

    }
}
