package com.example.librarygp2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.librarygp2.Adapters.Books_Adapter;
import com.example.librarygp2.Models.Books;
import com.example.librarygp2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryPageActivity extends AppCompatActivity {
    String catname = "" ;
    RecyclerView BookCatrecyclerView ;
    Books_Adapter books_adapter ;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    List<Books> booksList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);
        catname = getIntent().getStringExtra("Catname");
        setTitle(catname);

        BookCatrecyclerView = findViewById(R.id.categoryList);
        BookCatrecyclerView.setLayoutManager(new LinearLayoutManager(CategoryPageActivity.this));
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Books");


    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                booksList = new ArrayList<>();

                for (DataSnapshot booksnap : dataSnapshot.getChildren()){
                    if (booksnap.child("category").getValue(String.class).equals(catname)){
                        Books book = booksnap.getValue(Books.class);
                        booksList.add(book);
                    }

                }
                books_adapter = new Books_Adapter(CategoryPageActivity.this,booksList);
                BookCatrecyclerView.setAdapter(books_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}