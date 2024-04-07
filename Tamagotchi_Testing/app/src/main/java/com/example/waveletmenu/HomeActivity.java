package com.example.waveletmenu;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity {
    private ImageButton b1, b2, b3, l1, l2, l3;
    private EditText name;
    private TextView t1, t2;

    private long frameTime;
    private int frame;
    private MediaPlayer ping;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        b1 = findViewById(R.id.wavelet_button);
        b2 = findViewById(R.id.drag_drop_button);
        b3 = findViewById(R.id.linear_button);
        l1 = findViewById(R.id.stats1_button);
        l2 = findViewById(R.id.stats2_button);
        l3 = findViewById(R.id.stats3_button);
        name = findViewById(R.id.namefield);
        ping = MediaPlayer.create(this, R.raw.click);

        //frameTime = System.nanoTime();
        //frame = 0;
        //updateFPS();
    }

    private void updateFPS() {
        long currentTime = System.nanoTime();
        long deltaTime = currentTime - frameTime;
        frameTime = currentTime;

        if (deltaTime > 0) {
            double fps = 1e9 / deltaTime; // Frames per second (FPS)
            t2.setText("FPS: " + (int)fps);
        }

        frame++;
        t2.postDelayed(this::updateFPS, 1000);
    }

    public void click(View v){
        ping.start();
        if(name.getText().toString() == "")
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
        }else if(v == l1 || v == l2 || v == l3) {
            File file;
            if(v==l1){
                file = new File("/data/user/0/com.example.waveletmenu/files", name.getText().toString()+"Radial.txt");
            }else if(v==l2){
                file = new File("/data/user/0/com.example.waveletmenu/files", name.getText().toString()+"DragNDrop.txt");
            }else{
                file = new File("/data/user/0/com.example.waveletmenu/files", name.getText().toString()+"Linear.txt");
            }

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                StringBuilder fileContent = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    // Process each line as needed
                    fileContent.append(line).append("\n");
                }
                br.close();
                Log.v("INFO GET", fileContent.toString());
                onButtonShowPopupWindowClick(v, fileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle any errors that occur while reading the file
            }
        }
    }

    public void onButtonShowPopupWindowClick(View view, String text) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.information_popup, null);
        t1 = popupView.findViewById(R.id.info_popup);
        t1.setText(text);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
