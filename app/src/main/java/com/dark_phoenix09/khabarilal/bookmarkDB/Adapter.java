package com.dark_phoenix09.khabarilal.bookmarkDB;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.dark_phoenix09.khabarilal.R;
import com.dark_phoenix09.khabarilal.content;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<model>articles;
    Mydatabase db;

    public Adapter(Context context, List<model> articles) {
        this.context = context;
        this.articles = articles;
        db= Room.databaseBuilder(context,Mydatabase.class,"boolmarkdb").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final model a=articles.get(position);
        Picasso.with(context).load(a.getI()).into(holder.imageView);
        holder.tvTitle.setText(a.getT());
        holder.tvSource.setText(a.getS());
        holder.tvAuthor.setText(a.getA());
        holder.tvdesc.setText(a.getD());



        holder.cardView.setOnClickListener(v -> {
            Intent i=new Intent(context, content.class);
            i.putExtra("t",a.getT());
            i.putExtra("i",a.getI());
            i.putExtra("d",a.getD());
            i.putExtra("s",a.getS());
            i.putExtra("u",a.getU());
            i.putExtra("a",a.getA());
            context.startActivity(i);
        });

    }

    public  void deleteItem(int position){
        model m=articles.get(position);
        String u=m.getU();
        db.bookmarkDAO().deleteBookmark(u);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle,tvSource,tvDate,tvAuthor,tvdesc;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAuthor=itemView.findViewById(R.id.author);
            tvDate=itemView.findViewById(R.id.publishedAt);
            tvSource=itemView.findViewById(R.id.source);
            tvTitle=itemView.findViewById(R.id.ntitle);
            imageView=itemView.findViewById(R.id.img);
            cardView=itemView.findViewById(R.id.card);
            tvdesc=itemView.findViewById(R.id.desc);
        }
    }
}

