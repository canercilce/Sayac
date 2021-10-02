package com.example.sayac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnTikla;
    Button kayit;
    Button sifirla;
    TextView txtView;
    int sayi;
    int kaldigi_sayi;

    public static final String sharedPrefs="sharedPrefs";
    public static final String TEXT="text";
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTikla=(Button) findViewById(R.id.button);
        txtView =(TextView) findViewById(R.id.textView);
        kayit= (Button) findViewById(R.id.button2);
        sifirla= (Button) findViewById(R.id.button3);
        Random random = new Random();

        btnTikla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayi= Integer.parseInt(String.valueOf(txtView.getText()));
                txtView.setText(Integer.toString(sayi+1));
                float r = random.nextInt(255);
                float g = random.nextInt(255);
                float b = random.nextInt(255);
                int color = Color.rgb(r,g,b);
                btnTikla.setBackgroundColor(color);
            }
        });

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        sifirla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        loadData();
        updateViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        kaldigi_sayi = sayi;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.putString("@string/_0",Integer.toString(kaldigi_sayi));
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefs,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT,txtView.getText().toString());
        editor.apply();
        Toast.makeText(this,"Kaydedildi",Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPrefs,MODE_PRIVATE);
        text= sharedPreferences.getString(TEXT,"");
    }

    public void updateViews(){
        txtView.setText(text);
    }

    public void reset(){
        txtView.setText("0");
    }
}