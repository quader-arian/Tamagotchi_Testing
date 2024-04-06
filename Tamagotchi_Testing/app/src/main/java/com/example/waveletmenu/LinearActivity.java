package com.example.waveletmenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

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
    // Indicator of which icon is selected
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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
            touchStartTime = System.currentTimeMillis();
            subheading.setForeground(getDrawable(R.drawable.icon1_1heading));
        });
        subItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon1_2heading));
            }
        });
        subItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon1_3heading));
            }
        });
        subItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon1_4heading));
            }
        });
        subItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon1_5heading));
            }
        });

        subItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
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
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon2_1heading));
            }
        });
        subItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon2_2heading));
            }
        });
        subItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon2_3heading));
            }
        });
        subItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon2_4heading));
            }
        });
        subItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon2_5heading));
            }
        });
        subItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
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

                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon3_1heading));
            }
        });
        subItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon3_2heading));
            }
        });
        subItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon3_3heading));
            }
        });
        subItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon3_4heading));
            }
        });
        subItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
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
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon4_1heading));
            }
        });
        subItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon4_2heading));
            }
        });
        subItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon4_3heading));
            }
        });
        subItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
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
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon5_1heading));
            }
        });
        subItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon5_2heading));
            }
        });
        subItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon5_3heading));
            }
        });
        subItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon5_4heading));
            }
        });
        subItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
                subheading.setForeground(getDrawable(R.drawable.icon5_5heading));
            }
        });
        subItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                touchStartTime = System.currentTimeMillis();
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
