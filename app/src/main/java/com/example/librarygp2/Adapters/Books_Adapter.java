package com.example.librarygp2.Adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarygp2.Activities.BookDetailsActivity;
import com.example.librarygp2.Models.Books;
import com.example.librarygp2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Books_Adapter extends RecyclerView.Adapter<Books_Adapter.MyViewHolder> {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    protected FirebaseUser currentuser = mAuth.getCurrentUser() ;

    Context mContext ;
    List<Books> mData ;

    public Books_Adapter(Context mContext, List<Books> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.books_row_items,parent,false);


        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(mData.get(position).getTitle());
        holder.category.setText(mData.get(position).getCategory());
        Picasso.get().load(mData.get(position).getPicture()).into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title , category ;
        ImageView pic ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.BookTitle);
            category = itemView.findViewById(R.id.BookCategory);
            pic = itemView.findViewById(R.id.imageViewonrow) ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent bookdetailsAct = new Intent(mContext, BookDetailsActivity.class);
                    int position = getAdapterPosition();

                    bookdetailsAct.putExtra("title",mData.get(position).getTitle());
                    bookdetailsAct.putExtra("description",mData.get(position).getDescription());
                    bookdetailsAct.putExtra("bookKey",mData.get(position).getBookKey());
                    bookdetailsAct.putExtra("category",mData.get(position).getCategory());
                    bookdetailsAct.putExtra("username",mData.get(position).getUname());
                    bookdetailsAct.putExtra("picture",mData.get(position).getPicture());


                    mContext.startActivity(bookdetailsAct);



                }
            });
        }
    }
}
