package com.example.waveletmenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class LinearActivity extends AppCompatActivity {
    final static int THRESHOLD = 50000;

    ResultsTextMaker results;

    // Six icons are placed evenly around the outer menu
    // All the others are placed in the inner menu

    // The central button of the wavelet menu
    ImageButton mainItem1, mainItem2, mainItem3, mainItem4, mainItem5,
                subItem1, subItem2, subItem3, subItem4, subItem5, subItem6;

    // There are two menu layers in this menu
    ImageView innerMenu, outerMenu;

    // Keep track of the visibility of the outer/inner menus
    boolean outerIsExpanded = false;

    boolean iconSelected = false;
    protected int menuItem, submenuItem;

    // Keeps track of the start time the user touches the screen
    private long touchStartTime = 0;
    LinearLayout submenu;
    ImageView heading, subheading;
    ImageView testImage1, testImage2;
    int test1;
    int test2;
    int current;
    int count = 11; //exclusive

    public void nextTest(){
        Random rand = new Random();
        test1 = rand.nextInt(5)+1;
        if(test1 == 4){
            test2 = rand.nextInt(4)+1;
        }else if(test1 == 3){
            test2 = rand.nextInt(5)+1;
        }else{
            test2 = rand.nextInt(6)+1;
        }


        String iconCode1 = "icon"+test1;
        String iconCode2 = "icon"+test1+"_"+test2;

        if((test1 ==4 && test2==5)|| test1 ==3 && test2==6){
            iconCode2 = iconCode1;
        }

        count--;
        Log.v("HELP", iconCode2);
        int id1 = getResources().getIdentifier(iconCode1, "drawable", getPackageName());
        int id2 = getResources().getIdentifier(iconCode2, "drawable", getPackageName());
        testImage1.setImageResource(id1);
        testImage2.setImageResource(id2);
        results.WriteToFile("Linear", String.format("%d",count), "1", String.format("%d",System.currentTimeMillis() - touchStartTime));
        touchStartTime = System.currentTimeMillis();
        if(count < 0){
            try {
                results.PublishFile(getApplicationContext());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            startActivity(new Intent(LinearActivity.this, HomeActivity.class));
        }
    }
    // Indicator of which icon is selected
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String name = getIntent().getStringExtra("Name");
        File path = getApplicationContext().getFilesDir();
        String pa = path.getPath();
        Log.v("Find Path", pa);
        results = new ResultsTextMaker("Linear",name, path);


        setContentView(R.layout.linear);
        submenu = findViewById(R.id.submenu);
        //initalize main menu items
        mainItem1 = findViewById(R.id.mainitem1);
        mainItem2 = findViewById(R.id.mainitem2);
        mainItem3 = findViewById(R.id.mainitem3);
        mainItem4 = findViewById(R.id.mainitem4);
        mainItem5 = findViewById(R.id.mainitem5);

        //innitalize sub menu items
        subItem1 = findViewById(R.id.subitem1);
        subItem2 = findViewById(R.id.subitem2);
        subItem3 = findViewById(R.id.subitem3);
        subItem4 = findViewById(R.id.subitem4);
        subItem5 = findViewById(R.id.subitem5);
        subItem6 = findViewById(R.id.subitem6);

        heading = findViewById(R.id.heading);
        subheading = findViewById(R.id.subheading);

        testImage1 = findViewById(R.id.from);
        testImage2 = findViewById(R.id.to);
        current = 0;
        touchStartTime = System.currentTimeMillis();
        nextTest();
        hideSubMenu();
        //set listeners for the main items
        mainItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heading.setForeground(getDrawable(R.drawable.icon1_heading));
                showSubMenu();
                showSubMenu1();
            }
        });
        mainItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heading.setForeground(getDrawable(R.drawable.icon2_heading));
                showSubMenu();
                showSubMenu2();
            }
        });
        mainItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heading.setForeground(getDrawable(R.drawable.icon3_heading));
                showSubMenu();
                showSubMenu3();
            }
        });
        mainItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heading.setForeground(getDrawable(R.drawable.icon4_heading));
                showSubMenu();
                showSubMenu4();
            }
        });
        mainItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heading.setForeground(getDrawable(R.drawable.icon5_heading));
                showSubMenu();
                showSubMenu5();
            }
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void showSubMenu1(){
        subItem1.setForeground(getDrawable(R.drawable.icon1_1));
        subItem2.setForeground(getDrawable(R.drawable.icon1_2));
        subItem3.setForeground(getDrawable(R.drawable.icon1_3));
        subItem4.setForeground(getDrawable(R.drawable.icon1_4));
        subItem5.setForeground(getDrawable(R.drawable.icon1_5));
        subItem6.setForeground(getDrawable(R.drawable.icon1_6));
        subItem1.setOnClickListener(view -> {
            current = 11;

            if(current == test1*10 + test2){
                nextTest();
            }
            subheading.setForeground(getDrawable(R.drawable.icon1_1heading));
        });
        subItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 12;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon1_2heading));
            }
        });
        subItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 13;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon1_3heading));
            }
        });
        subItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 14;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon1_4heading));
            }
        });
        subItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 15;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon1_5heading));
            }
        });

        subItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 16;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon1_6heading));
            }
        });
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void showSubMenu2(){
        subItem1.setForeground(getDrawable(R.drawable.icon2_1));
        subItem2.setForeground(getDrawable(R.drawable.icon2_2));
        subItem3.setForeground(getDrawable(R.drawable.icon2_3));
        subItem4.setForeground(getDrawable(R.drawable.icon2_4));
        subItem5.setForeground(getDrawable(R.drawable.icon2_5));
        subItem6.setForeground(getDrawable(R.drawable.icon2_6));
        subItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 21;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon2_1heading));
            }
        });
        subItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 22;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon2_2heading));
            }
        });
        subItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 23;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon2_3heading));
            }
        });
        subItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 24;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon2_4heading));
            }
        });
        subItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 25;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon2_5heading));
            }
        });
        subItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 26;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon2_6heading));
            }
        });
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void showSubMenu3(){
        subItem1.setForeground(getDrawable(R.drawable.icon3_1));
        subItem2.setForeground(getDrawable(R.drawable.icon3_2));
        subItem3.setForeground(getDrawable(R.drawable.icon3_3));
        subItem4.setForeground(getDrawable(R.drawable.icon3_4));
        subItem5.setForeground(getDrawable(R.drawable.icon3_5));
        subItem6.setForeground(null);
        subItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 31;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon3_1heading));
            }
        });
        subItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 32;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon3_2heading));
            }
        });
        subItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 33;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon3_3heading));
            }
        });
        subItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 34;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon3_4heading));
            }
        });
        subItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 35;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon3_5heading));
            }
        });
        subItem6.setOnClickListener(null);
        subItem6.setVisibility(View.INVISIBLE);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void showSubMenu4(){
        subItem1.setForeground(getDrawable(R.drawable.icon4_1));
        subItem2.setForeground(getDrawable(R.drawable.icon4_2));
        subItem3.setForeground(getDrawable(R.drawable.icon4_3));
        subItem4.setForeground(getDrawable(R.drawable.icon4_4));
        subItem5.setForeground(null);
        subItem6.setForeground(null);
        subItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 41;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon4_1heading));
            }
        });
        subItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 42;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon4_2heading));
            }
        });
        subItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 43;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon4_3heading));
            }
        });
        subItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 44;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon4_4heading));
            }
        });
        subItem5.setOnClickListener(null);
        subItem6.setOnClickListener(null);
        subItem5.setVisibility(View.INVISIBLE);
        subItem6.setVisibility(View.INVISIBLE);
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void showSubMenu5(){
        subItem1.setForeground(getDrawable(R.drawable.icon5_1));
        subItem2.setForeground(getDrawable(R.drawable.icon5_2));
        subItem3.setForeground(getDrawable(R.drawable.icon5_3));
        subItem4.setForeground(getDrawable(R.drawable.icon5_4));
        subItem5.setForeground(getDrawable(R.drawable.icon5_5));
        subItem6.setForeground(getDrawable(R.drawable.icon5_6));
        subItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 51;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon5_1heading));
            }
        });
        subItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 52;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon5_2heading));
            }
        });
        subItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 53;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon5_3heading));
            }
        });
        subItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 54;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon5_4heading));
            }
        });
        subItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 55;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon5_5heading));
            }
        });
        subItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = 56;
                if(current == test1*10 + test2){
                    nextTest();
                }
                subheading.setForeground(getDrawable(R.drawable.icon5_6heading));
            }
        });
    }

    protected void hideSubMenu(){
        subItem1.setVisibility(View.INVISIBLE);
        subItem2.setVisibility(View.INVISIBLE);
        subItem3.setVisibility(View.INVISIBLE);
        subItem4.setVisibility(View.INVISIBLE);
        subItem5.setVisibility(View.INVISIBLE);
        subItem6.setVisibility(View.INVISIBLE);
    }
    protected void showSubMenu(){
        TranslateAnimation raiseSubMenu = new TranslateAnimation(0f, 0f, 50f, 0f);
        raiseSubMenu.setDuration(200);
        raiseSubMenu.setFillAfter(true);
        subItem1.setVisibility(View.VISIBLE);
        subItem2.setVisibility(View.VISIBLE);
        subItem3.setVisibility(View.VISIBLE);
        subItem4.setVisibility(View.VISIBLE);
        subItem5.setVisibility(View.VISIBLE);
        subItem6.setVisibility(View.VISIBLE);

        submenu.startAnimation(raiseSubMenu);
    }
}
