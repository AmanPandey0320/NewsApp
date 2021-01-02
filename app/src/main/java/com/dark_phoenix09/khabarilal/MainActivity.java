package com.dark_phoenix09.khabarilal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    Handler handler;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializations
        imageView=findViewById(R.id.app_start_img);
        handler=new Handler();

        //bring image

        imageView.animate().alpha(1f).setDuration(2000);

        //start process
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getBaseContext(),dashBoard.class);
                startActivity(i);
                finish();
            }
        },3500);
    }
}