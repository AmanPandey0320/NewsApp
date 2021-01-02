package com.dark_phoenix09.khabarilal;

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

import com.dark_phoenix09.khabarilal.models.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Articles>articles;

    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Articles a=articles.get(position);
            Picasso.with(context).load(a.getUrlToImage()).into(holder.imageView);

        holder.tvTitle.setText(a.getTitle());
        holder.tvSource.setText(a.getSource().getName());
        holder.tvDate.setText(a.getPublishedAt());
        holder.tvAuthor.setText(a.getAuthor());
        holder.tvdesc.setText(a.getDescription());



        holder.cardView.setOnClickListener(v -> {
            Intent i=new Intent(context,content.class);
            i.putExtra("t",a.getTitle());
            i.putExtra("i",a.getUrlToImage());
            i.putExtra("d",a.getDescription());
            i.putExtra("s",a.getSource().getName());
            i.putExtra("u",a.getUrl());
            i.putExtra("a",a.getAuthor());
            context.startActivity(i);
        });

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
