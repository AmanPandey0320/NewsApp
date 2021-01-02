package com.dark_phoenix09.khabarilal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dark_phoenix09.khabarilal.models.Articles;
import com.dark_phoenix09.khabarilal.models.Headlines;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class dashBoard extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Articles> articles=new ArrayList<>();
    final String API_KEY="85c92e10bdc442dd8cfbb0a6e5090f25";
    TabItem entertainment;
    TabLayout tabLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        //initialization
        entertainment=findViewById(R.id.entertain);
        progressBar=findViewById(R.id.progressbar_d);
        tabLayout=findViewById(R.id.tab_layout);
        entertainment=findViewById(R.id.entertain);
        recyclerView=findViewById(R.id.recyclerview);
        toolbar=findViewById(R.id.dashboard_toolbar);


        setSupportActionBar(toolbar);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String country=getCountry();
        retrieveJson(country,API_KEY);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0: retrieveJson(country,API_KEY);//headlines
                            progressBar.setVisibility(View.VISIBLE);
                            recyclerView.setAdapter(null);
                            break;
                    case 1: retrieveJson2("entertainment",country,API_KEY);//entertainment
                        progressBar.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(null);
                            break;
                    case 2: retrieveJson2("science",country,API_KEY);//science
                        progressBar.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(null);
                        break;
                    case 3: retrieveJson2("sports",country,API_KEY);//sports
                        progressBar.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(null);
                        break;
                    case 4: retrieveJson2("health",country,API_KEY);//health
                        progressBar.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(null);
                        break;
                    case 5: retrieveJson2("technology",country,API_KEY);//technology
                        progressBar.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(null);
                        break;
                    case 6: retrieveJson2("business",country,API_KEY); //business
                        progressBar.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(null);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    public void retrieveJson(String country, String apiKey){
        Call<Headlines> call=ApiClient.getInstance().getApi().getHeadlines(country,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(@NonNull Call<Headlines> call, @NonNull Response<Headlines> response) {
                assert response.body() != null;
                if(response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles=response.body().getArticles();
                    adapter=new Adapter(dashBoard.this,articles);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Headlines> call, @NonNull Throwable t) {
                Toast.makeText(dashBoard.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void retrieveJson2(String category,String country, String apiKey){
        Call<Headlines> call=ApiClient2.getInstance().getApi().getHeadlines(country,category,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(@NonNull Call<Headlines> call, @NonNull Response<Headlines> response) {
                assert response.body() != null;
                if(response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles=response.body().getArticles();
                    adapter=new Adapter(dashBoard.this,articles);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(dashBoard.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.dashboard_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.bookmark){
            startActivity(new Intent(dashBoard.this,bookmark.class));
        }
        return true;
    }
}