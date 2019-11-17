package com.example.librarygp2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

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

public class AllBooksActivity extends AppCompatActivity {
    RecyclerView BookrecyclerView ;
    Books_Adapter books_adapter ;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    List<Books> booksList ;

    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        setTitle("All Books");

        BookrecyclerView = findViewById(R.id.allBooks);
        BookrecyclerView.setLayoutManager(new LinearLayoutManager(AllBooksActivity.this));
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Books");
//search function // need adapter list name

       /*search= (EditText) findViewById(R.id.search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (this).books_adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                booksList = new ArrayList<>();

                for (DataSnapshot booksnap : dataSnapshot.getChildren()){
                    Books book = booksnap.getValue(Books.class);
                    booksList.add(book);
                }
                books_adapter = new Books_Adapter(AllBooksActivity.this,booksList);
                BookrecyclerView.setAdapter(books_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
