package com.example.waveletmenu;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.waveletmenu.R;

public class Activity2 extends AppCompatActivity {
    final static int THRESHOLD = 50000;

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
    ImageView heading;
    // Indicator of which icon is selected

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

    }

    protected void showSubMenu1(){
        subItem1.setForeground(getDrawable(R.drawable.icon1_1));
        subItem2.setForeground(getDrawable(R.drawable.icon1_2));
        subItem3.setForeground(getDrawable(R.drawable.icon1_3));
        subItem4.setForeground(getDrawable(R.drawable.icon1_4));
        subItem5.setForeground(getDrawable(R.drawable.icon1_5));
        subItem6.setForeground(getDrawable(R.drawable.icon1_6));
        subItem1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon1_1heading));
                return false;
            }
        });
        subItem2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon1_2heading));
                return false;
            }
        });
        subItem3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon1_3heading));
                return false;
            }
        });
        subItem4.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon1_4heading));
                return false;
            }
        });
        subItem5.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon1_5heading));
                return false;
            }
        });
        subItem6.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon1_6heading));
                return false;
            }
        });
    }
    protected void showSubMenu2(){
        subItem1.setForeground(getDrawable(R.drawable.icon2_1));
        subItem2.setForeground(getDrawable(R.drawable.icon2_2));
        subItem3.setForeground(getDrawable(R.drawable.icon2_3));
        subItem4.setForeground(getDrawable(R.drawable.icon2_4));
        subItem5.setForeground(getDrawable(R.drawable.icon2_5));
        subItem6.setForeground(getDrawable(R.drawable.icon2_6));

        subItem1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_1heading));
                return false;
            }
        });
        subItem2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_2heading));
                return false;
            }
        });
        subItem3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_3heading));
                return false;
            }
        });
        subItem4.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_4heading));
                return false;
            }
        });
        subItem5.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_5heading));
                return false;
            }
        });
        subItem6.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_6heading));
                return false;
            }
        });
    }
    protected void showSubMenu3(){
        subItem1.setForeground(getDrawable(R.drawable.icon3_1));
        subItem2.setForeground(getDrawable(R.drawable.icon3_2));
        subItem3.setForeground(getDrawable(R.drawable.icon3_3));
        subItem4.setForeground(getDrawable(R.drawable.icon3_4));
        subItem5.setForeground(getDrawable(R.drawable.icon3_5));
        subItem6.setForeground(null);
        subItem1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon3_1heading));
                return false;
            }
        });
        subItem2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon3_2heading));
                return false;
            }
        });
        subItem3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon3_3heading));
                return false;
            }
        });
        subItem4.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon3_4heading));
                return false;
            }
        });
        subItem5.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon3_5heading));
                return false;
            }
        });
        subItem6.setOnTouchListener(null);
    }
    protected void showSubMenu4(){
        subItem1.setForeground(getDrawable(R.drawable.icon4_1));
        subItem2.setForeground(getDrawable(R.drawable.icon4_2));
        subItem3.setForeground(getDrawable(R.drawable.icon4_3));
        subItem4.setForeground(getDrawable(R.drawable.icon4_4));
        subItem5.setForeground(null);
        subItem6.setForeground(null);
        subItem1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon4_1heading));
                return false;
            }
        });
        subItem2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon4_2heading));
                return false;
            }
        });
        subItem3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon4_3heading));
                return false;
            }
        });
        subItem4.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon4_4heading));
                return false;
            }
        });
        subItem5.setOnTouchListener(null);
        subItem6.setOnTouchListener(null);
    }
    protected void showSubMenu5(){
        subItem1.setForeground(getDrawable(R.drawable.icon5_1));
        subItem2.setForeground(getDrawable(R.drawable.icon5_2));
        subItem3.setForeground(getDrawable(R.drawable.icon5_3));
        subItem4.setForeground(getDrawable(R.drawable.icon5_4));
        subItem5.setForeground(getDrawable(R.drawable.icon5_5));
        subItem6.setForeground(getDrawable(R.drawable.icon5_6));
        subItem1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_1heading));
                return false;
            }
        });
        subItem2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_2heading));
                return false;
            }
        });
        subItem3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_3heading));
                return false;
            }
        });
        subItem4.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_4heading));
                return false;
            }
        });
        subItem5.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_5heading));
                return false;
            }
        });
        subItem6.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                touchStartTime = System.currentTimeMillis();
                heading.setForeground(getDrawable(R.drawable.icon2_6heading));
                return false;
            }
        });
    }

    protected void hideSubMenu(){

    }
    protected void showSubMenu(){
        TranslateAnimation raiseSubMenu = new TranslateAnimation(0f, 0f, 0f, 3f);
        raiseSubMenu.setDuration(200);
        raiseSubMenu.setFillAfter(true);

        submenu.startAnimation(raiseSubMenu);
    }
}
