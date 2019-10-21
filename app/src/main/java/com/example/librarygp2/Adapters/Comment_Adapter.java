package com.example.librarygp2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarygp2.Models.Comments;
import com.example.librarygp2.R;

import org.w3c.dom.Comment;

import java.util.List;

public class Comment_Adapter extends RecyclerView.Adapter<Comment_Adapter.CommentViewHolder> {

    private Context mContext ;
    private List<Comments> mData ;

    public Comment_Adapter(Context mContext, List<Comments> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.comment_row_item,parent,false);


        return new CommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        holder.usernamecomment.setText(mData.get(position).getUname() + " : ");
        holder.contentcomment.setText(mData.get(position).getContent());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView usernamecomment,contentcomment ;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            usernamecomment = itemView.findViewById(R.id.commentusername);
            contentcomment = itemView.findViewById(R.id.commentcontent);

        }

    }
}
