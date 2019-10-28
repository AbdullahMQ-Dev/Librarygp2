package com.example.librarygp2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.librarygp2.Adapters.Comment_Adapter;
import com.example.librarygp2.Models.Comments;
import com.example.librarygp2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

public class BookDetailsActivity extends AppCompatActivity {
    TextView booktitle , bookowner,bookdescription ;
    EditText edComment ;
    Button addcommentbtn;
    String bookkey ;
    ImageView picture ;
    FirebaseAuth firebaseAuth ;
    FirebaseUser firebaseUser ;
    FirebaseDatabase firebaseDatabase ;
    private RecyclerView CommentRec ;
    Comment_Adapter comment_adapter ;
    List<Comments> listcomments ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        CommentRec = findViewById(R.id.commentList);
        booktitle = findViewById(R.id.Bookdetails_title);
        bookowner = findViewById(R.id.Bookdetails_owener);
        bookdescription = findViewById(R.id.Bookdetails_description);
        edComment = findViewById(R.id.AddComment);
        addcommentbtn = findViewById(R.id.comment_btn);
        picture = findViewById(R.id.BookPic);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        addcommentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference commentReference = firebaseDatabase.getReference("Comment").child(bookkey).push();
                String comment_content = edComment.getText().toString();
                String uid = firebaseUser.getUid();
                String uname = firebaseUser.getDisplayName();

                Comments comment = new Comments(comment_content,uid,uname);
                if (!comment.getContent().isEmpty()){
                commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ShowMessage("Comment Added");
                        edComment.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        ShowMessage("Faill to add Comment"+e.getMessage());
                    }
                });}
                else {
                    ShowMessage("Write comment !!");
                }

            }
        });


        String title = getIntent().getExtras().get("title").toString();
        String description = getIntent().getExtras().get("description").toString();
        String category = getIntent().getExtras().get("category").toString();
        String owner = getIntent().getExtras().get("username").toString();
        bookkey = getIntent().getExtras().get("bookKey").toString();
        String picurl = getIntent().getExtras().get("picture").toString();
        setTitle(category);
        booktitle.setText(title);
        bookowner.setText(owner);
        bookdescription.setText(description);

        Picasso.with(this).load(picurl).into(picture);

        iniCommentList();



    }

    private void iniCommentList() {

        CommentRec.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference commentRef = firebaseDatabase.getReference("Comment").child(bookkey);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listcomments = new ArrayList<>();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Comments comments = snapshot.getValue(Comments.class);
                    listcomments.add(comments);
                }

                comment_adapter = new Comment_Adapter(BookDetailsActivity.this,listcomments);
                CommentRec.setAdapter(comment_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void ShowMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
