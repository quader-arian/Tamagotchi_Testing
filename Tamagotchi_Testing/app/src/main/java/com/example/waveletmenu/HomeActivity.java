package com.example.waveletmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private ImageButton b1, b2, b3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        b1 = findViewById(R.id.wavelet_button);
        b2 = findViewById(R.id.drag_drop_button);
        b3 = findViewById(R.id.linear_button);
    }

    public void click(View v){
        if(v == b1){
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }else if(v == b2){
            startActivity(new Intent(HomeActivity.this, DragDropActivity.class));
        }else if(v == b3){
            startActivity(new Intent(HomeActivity.this, Activity2.class));
        }
    }
}
