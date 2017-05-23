package com.example.developer.parallax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class MenuActivity extends AppCompatActivity {

    public Button btnPlay;
    LinearLayout ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.bringToFront();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ranking = (LinearLayout)findViewById(R.id.ranking);


        HashMap<String, String> lista = GameResources.getInstance().jogadores;

        for (String s : lista.keySet()){
            TextView p = new TextView(MenuActivity.this);
            p.setText(GameResources.getInstance().jogadores.get(s));
            ranking.addView(p);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        HashMap<String, String> lista = GameResources.getInstance().jogadores;

        for (String s : lista.keySet()){
            TextView p = new TextView(MenuActivity.this);
            p.setText(s+" "+GameResources.getInstance().jogadores.get(s));
            ranking.addView(p);
        }

    }
}
