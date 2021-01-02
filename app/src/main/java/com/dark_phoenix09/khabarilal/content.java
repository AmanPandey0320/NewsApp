package com.dark_phoenix09.khabarilal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.dark_phoenix09.khabarilal.bookmarkDB.Mydatabase;
import com.dark_phoenix09.khabarilal.bookmarkDB.model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class content extends AppCompatActivity {

    WebView webView;
    FloatingActionButton share_this,add_this,mark_this;
    int c;
    public static Mydatabase db;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);


        //initializations
        db= Room.databaseBuilder(getApplicationContext(),Mydatabase.class,"boolmarkdb").allowMainThreadQueries().build();
        c=1;
        add_this=findViewById(R.id.add_btn);
        mark_this=findViewById(R.id.bookmark_btn);
        share_this=findViewById(R.id.share_btn);
        webView=findViewById(R.id.web);
        Intent intent=getIntent();
        String t=intent.getStringExtra("t");
        String i=intent.getStringExtra("i");
        String d=intent.getStringExtra("d");
        String s=intent.getStringExtra("s");
        String u=intent.getStringExtra("u");
        String a=intent.getStringExtra("a");

        //bookmarking process
        mark_this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model model= new model();
                model.setA(a);
                model.setD(d);
                model.setI(i);
                model.setS(s);
                model.setT(t);
                model.setU(u);
                db.bookmarkDAO().InsertBookmark(model);
                Toast.makeText(content.this, "Done", Toast.LENGTH_SHORT).show();

            }
        });

        //pop up action
        add_this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_this.animate().rotationBy(495f).setDuration(250);
                if(c%2==1){
                    mark_this.animate().translationY(-225f).setDuration(250);
                    share_this.animate().translationY(-460f).setDuration(250);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mark_this.setClickable(true);
                            share_this.setClickable(true);
                        }
                    },449);
                }else{
                    mark_this.animate().translationY(0f).setDuration(250);
                    share_this.animate().translationY(0f).setDuration(250);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mark_this.setClickable(false);
                            share_this.setClickable(false);
                        }
                    },1);
                }
                c++;
            }
        });

        //


        //sharing content to others
        share_this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, u);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(u);

    }
}