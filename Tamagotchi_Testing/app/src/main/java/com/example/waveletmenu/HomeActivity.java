package com.example.waveletmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private ImageButton b1, b2, b3;
    private EditText name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        b1 = findViewById(R.id.wavelet_button);
        b2 = findViewById(R.id.drag_drop_button);
        b3 = findViewById(R.id.linear_button);
        name = findViewById(R.id.namefield);
    }

    public void click(View v){
        if(name.getText().toString() == "Tap To Enter Name")
            name.setText("Subject");
        if(v == b1){
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            intent.putExtra("Name", name.getText().toString());
            startActivity(intent);
        }else if(v == b2){
            Intent intent = new Intent(HomeActivity.this, DragDropActivity.class);
            intent.putExtra("Name", name.getText().toString());
            startActivity(intent);
        }else if(v == b3){
            Intent intent = new Intent(HomeActivity.this, LinearActivity.class);
            intent.putExtra("Name", name.getText().toString());
            startActivity(intent);
        }
    }
}
