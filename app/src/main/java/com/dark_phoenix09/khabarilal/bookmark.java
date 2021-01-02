package com.dark_phoenix09.khabarilal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.dark_phoenix09.khabarilal.bookmarkDB.Adapter;
import com.dark_phoenix09.khabarilal.bookmarkDB.Mydatabase;
import com.dark_phoenix09.khabarilal.bookmarkDB.model;

import java.util.List;

public class bookmark extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    Mydatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        //initializations
        db= Room.databaseBuilder(getApplicationContext(),Mydatabase.class,"boolmarkdb").allowMainThreadQueries().build();
        layoutManager=new LinearLayoutManager(this);
        recyclerView=findViewById(R.id.bookmark_recyclerview);
        List<model> list=db.bookmarkDAO().getBookmark();
        adapter=new Adapter(bookmark.this,list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.deleteItem(viewHolder.getAdapterPosition());
                adapter=new Adapter(getApplicationContext(),db.bookmarkDAO().getBookmark());
                recyclerView.setAdapter(adapter);
            }
        }).attachToRecyclerView(recyclerView);


    }

}