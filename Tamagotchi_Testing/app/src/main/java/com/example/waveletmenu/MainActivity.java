package com.example.waveletmenu;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.ViewConfiguration;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity{
    /* ----------------------------------------------
     * Declare field variables
     * ---------------------------------------------- */
    // Maximum duration for pressing screen
    final static int THRESHOLD = 50000;

    // Six icons are placed evenly around the outer menu
    // All the others are placed in the inner menu
    ImageView icon1, icon2, icon3, icon4, icon5,
            icon1_1, icon1_2, icon1_3, icon1_4, icon1_5, icon1_6,
            icon2_1, icon2_2, icon2_3, icon2_4, icon2_5, icon2_6,
            icon3_1, icon3_2, icon3_3, icon3_4, icon3_5,
            icon4_1, icon4_2, icon4_3, icon4_4,
            icon5_1, icon5_2, icon5_3, icon5_4, icon5_5, icon5_6,
            testImage1, testImage2;

    // The central button of the wavelet menu
    ImageButton button;

    // There are two menu layers in this menu
    ImageView innerMenu, outerMenu;

    // Keep track of the visibility of the outer/inner menus
    boolean outerIsExpanded = false, innerIsExpanded = false;

    boolean iconSelected = false;
    // An image that represents when an outer menu item is selected
    Drawable pink_circle;

    // Keeps track of the start time the user touches the screen
    private long touchStartTime = 0;

    int test1; //keep track of tests
    int test2;
    int current;
    int count = 11; //max tests (exclusive)
    ResultsTextMaker results;

    // Indicator of which icon is selected
    boolean icon1Selected, icon2Selected, icon3Selected, icon4Selected, icon5Selected,
            icon1_1Selected, icon1_2Selected, icon1_3Selected, icon1_4Selected, icon1_5Selected, icon1_6Selected,
            icon2_1Selected, icon2_2Selected, icon2_3Selected, icon2_4Selected, icon2_5Selected, icon2_6Selected,
            icon3_1Selected, icon3_2Selected, icon3_3Selected, icon3_4Selected, icon3_5Selected,
            icon4_1Selected, icon4_2Selected, icon4_3Selected, icon4_4Selected,
            icon5_1Selected, icon5_2Selected, icon5_3Selected, icon5_4Selected, icon5_5Selected, icon5_6Selected;

    private View lastSelectedIcon = null;

    // Update the last selected icon when an icon is selected
    private void updateLastSelectedIcon(View icon) {
        lastSelectedIcon = icon;
    }

    // Method to retrieve the last selected icon
    private View getLastSelectedIcon() {
        return lastSelectedIcon;
    }
    public void nextTest(){
        Random rand = new Random();
        test1 = rand.nextInt(5)+1;
        if(test1 == 3){
            test2 = rand.nextInt(5)+1;
        }else if(test1 == 4){
            test2 = rand.nextInt(4)+1;
        }else{
            test2 = rand.nextInt(6)+1;
        }

        String iconCode1 = "icon"+test1;
        String iconCode2 = "icon"+test1+"_"+test2;

        Log.v("HELP", iconCode2);
        int id1 = getResources().getIdentifier(iconCode1, "drawable", getPackageName());
        int id2 = getResources().getIdentifier(iconCode2, "drawable", getPackageName());
        testImage1.setImageResource(id1);
        testImage2.setImageResource(id2);

//        test1 = 1;
//        test2 = 1;

        count--;
        Log.v("HELP", "test: " + count);
        results.WriteToFile("Wavelet", String.format("%d",count), "1", String.format("%d",System.currentTimeMillis() - touchStartTime));
        touchStartTime = System.currentTimeMillis();
        //count--;
        if(count < 0){
            try {
                results.PublishFile(getApplicationContext());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }
    }

    /* ----------------------------------------------
     * onCreate
     * ---------------------------------------------- */
    @SuppressLint({"UseCompatLoadingForDrawables", "ClickableViewAccessibility"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference for selected icon image
        pink_circle = getResources().getDrawable(R.drawable.pink_circle);

        // Sound to play on complete
        final MediaPlayer ping = MediaPlayer.create(this, R.raw.click);

        // Initialize the icons
        icon1 = findViewById(R.id.icon1);
        icon2 = findViewById(R.id.icon2);
        icon3 = findViewById(R.id.icon3);
        icon4 = findViewById(R.id.icon4);
        icon5 = findViewById(R.id.icon5);

        icon1_1 = findViewById(R.id.icon1_1);
        icon1_2 = findViewById(R.id.icon1_2);
        icon1_3 = findViewById(R.id.icon1_3);
        icon1_4 = findViewById(R.id.icon1_4);
        icon1_5 = findViewById(R.id.icon1_5);
        icon1_6 = findViewById(R.id.icon1_6);

        icon2_1 = findViewById(R.id.icon2_1);
        icon2_2 = findViewById(R.id.icon2_2);
        icon2_3 = findViewById(R.id.icon2_3);
        icon2_4 = findViewById(R.id.icon2_4);
        icon2_5 = findViewById(R.id.icon2_5);
        icon2_6 = findViewById(R.id.icon2_6);

        icon3_1 = findViewById(R.id.icon3_1);
        icon3_2 = findViewById(R.id.icon3_2);
        icon3_3 = findViewById(R.id.icon3_3);
        icon3_4 = findViewById(R.id.icon3_4);
        icon3_5 = findViewById(R.id.icon3_5);

        icon4_1 = findViewById(R.id.icon4_1);
        icon4_2 = findViewById(R.id.icon4_2);
        icon4_3 = findViewById(R.id.icon4_3);
        icon4_4 = findViewById(R.id.icon4_4);

        icon5_1 = findViewById(R.id.icon5_1);
        icon5_2 = findViewById(R.id.icon5_2);
        icon5_3 = findViewById(R.id.icon5_3);
        icon5_4 = findViewById(R.id.icon5_4);
        icon5_5 = findViewById(R.id.icon5_5);
        icon5_6 = findViewById(R.id.icon5_6);

        icon1Selected = false;
        icon2Selected = false;
        icon3Selected = false;
        icon4Selected = false;
        icon5Selected = false;

        icon1_1Selected = false;
        icon1_2Selected = false;
        icon1_3Selected = false;
        icon1_4Selected = false;
        icon1_5Selected = false;
        icon1_6Selected = false;

        icon2_1Selected = false;
        icon2_2Selected = false;
        icon2_3Selected = false;
        icon2_4Selected = false;
        icon2_5Selected = false;
        icon2_6Selected = false;

        icon3_1Selected = false;
        icon3_2Selected = false;
        icon3_3Selected = false;
        icon3_4Selected = false;
        icon3_5Selected = false;

        icon4_1Selected = false;
        icon4_2Selected = false;
        icon4_3Selected = false;
        icon4_4Selected = false;

        icon5_1Selected = false;
        icon5_2Selected = false;
        icon5_3Selected = false;
        icon5_4Selected = false;
        icon5_5Selected = false;
        icon5_6Selected = false;

        // Initialize testing
        testImage1 = findViewById(R.id.from);
        testImage2 = findViewById(R.id.to);

        String name = getIntent().getStringExtra("Name");
        File path = getApplicationContext().getFilesDir();
        String pa = path.getPath();
        Log.v("Find Path", pa);
        results = new ResultsTextMaker("Wavelet", name, path);

        nextTest();

        // Initialize the menus
        outerMenu = findViewById(R.id.outerCircle);
        innerMenu = findViewById(R.id.innerCircle);

        // Initialize the buttons
        button = findViewById(R.id.centralButton);

        // Makes sure everything is set up in the correct initial layout
        expandOuter(false, 0);

        /* ----------------------------------------------
         * Button Listeners
         * ---------------------------------------------- */
        final float touchSlop = ViewConfiguration.get(button.getContext()).getScaledTouchSlop();

        button.setOnTouchListener(new View.OnTouchListener() {
            private float startX;
            private float startY;
            private boolean isDragging = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        isDragging = false;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float dx = Math.abs(event.getX() - startX);
                        float dy = Math.abs(event.getY() - startY);
                        if (!isDragging && (dx > touchSlop || dy > touchSlop)) {
                            isDragging = true;
                            Log.d("ButtonClickListener", "Start Dragging");
                            // Start drag operation here
                            @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                            button.startDrag(data, shadowBuilder, button, 0);
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        if (!isDragging) {
                            // Handle click event if not dragging
                            Log.d("ButtonClickListener", "finger off");
                            // Duration for the amount of time the user spends holding onto the screen
                            if (innerIsExpanded) {
                                long touchDuration = System.currentTimeMillis() - touchStartTime;
                                // If the duration is over the threshold, deselect all icons and collapse the inner/outer menus
                                if (touchDuration < THRESHOLD) {
                                    Log.d("ButtonClickListener", "finger offfffff");
                                    deselect();
                                    expandInner(false,0);
                                    expandOuter(false,0);
                                    hideHeadings();
                                    hideSubHeadings();
                                }
                            }
                        }
                        break;
                }
                return true;
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                Log.d("ButtonClickListener", "longclick");
                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });

        /* ----------------------------------------------
         * Menu Listeners
         * ---------------------------------------------- */
        outerMenu.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
//                Log.d("ButtonClickListener", "drag");
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!outerIsExpanded) {

                            // If the drag enters the outer menu, expand the outer menu
                            expandOuter(true,0);
                        }
                        break;
                }
                return true;
            }
        });

        /* ----------------------------------------------
         * Icon 1 Listeners
         * ---------------------------------------------- */
        icon1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon1.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon1);
                        if(iconSelected && !icon1Selected) {
                            deselect();
                            hideHeadings();
                        }
                        icon1.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected) {
                            deselect();
                            expandOuter(false,1);
                            icon1Selected = false;
                            hideHeadings();
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon1Selected && !innerIsExpanded) {
                            expandInner(true,1);
                        }
                        iconSelected = true;
                        icon1Selected = true;
                        showHeadings(icon1);
                        break;
                }
                return true;
            }
        });
        /* ----------------------------------------------
         * Icon 1_x Listeners
         * ---------------------------------------------- */
        icon1_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon1_1.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon1_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon1_1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon1);
                        showSubHeadings(icon1_1);
                        updateLastSelectedIcon(icon1_1);
                        if(iconSelected && !icon1_1Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon1_1.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon1_1) {
                            Log.d("ButtonClickListener", "icon1_1 last");
                            deselect();
                            expandOuter(false,0);
                            if(11 == test1*10 + test2){
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                //check if they selected the right one
                                if(11 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon1_1Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon1_1Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon1_1Selected = true;
                        showHeadings(icon1);
                        showSubHeadings(icon1_1);
                        break;
                }
                return true;
            }
        });

        icon1_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon1_2.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon1_2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon1_2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon1);
                        showSubHeadings(icon1_2);
                        updateLastSelectedIcon(icon1_2);
                        if(iconSelected && !icon1_2Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon1_2.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon1_2) {
                            Log.d("ButtonClickListener", "icon1_2 last");
                            deselect();
                            expandOuter(false,0);
                            if(12 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                //check if they selected the right one
                                if(12 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon1_2Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon1_2Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon1_2Selected = true;
                        showHeadings(icon1);
                        showSubHeadings(icon1_2);
                        break;
                }
                return true;
            }
        });

        icon1_3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon1_3.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon1_3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon1_3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon1);
                        showSubHeadings(icon1_3);
                        updateLastSelectedIcon(icon1_3);
                        if(iconSelected && !icon1_3Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon1_3.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon1_3) {
                            deselect();
                            expandOuter(false,0);
                            if(13 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                //check if they selected the right one
                                if(13 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon1_3Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon1_3Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon1_3Selected = true;
                        showHeadings(icon1);
                        showSubHeadings(icon1_3);
                        break;
                }
                return true;
            }
        });

        icon1_4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon1_4.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon1_4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon1_4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon1);
                        showSubHeadings(icon1_4);
                        updateLastSelectedIcon(icon1_4);
                        if(iconSelected && !icon1_4Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon1_4.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon1_4) {
                            deselect();
                            expandOuter(false,0);
                            if(14 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                //check if they selected the right one
                                if(14 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon1_4Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon1_4Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon1_4Selected = true;
                        showHeadings(icon1);
                        showSubHeadings(icon1_4);
                        break;
                }
                return true;
            }
        });

        icon1_5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon1_5.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon1_5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon1_5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon1);
                        showSubHeadings(icon1_5);
                        updateLastSelectedIcon(icon1_5);
                        if(iconSelected && !icon1_5Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon1_5.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon1_5) {
                            deselect();
                            expandOuter(false,0);
                            if(15 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                //check if they selected the right one
                                if(15 == test1*10 + test2){
                                    nextTest();
                                }

                            }
                            icon1_5Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon1_5Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon1_5Selected = true;
                        showHeadings(icon1);
                        showSubHeadings(icon1_5);
                        break;
                }
                return true;
            }
        });

        icon1_6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon1_6.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon1_6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon1_6.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon1);
                        showSubHeadings(icon1_6);
                        updateLastSelectedIcon(icon1_6);
                        if(iconSelected && !icon1_6Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon1_6.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon1_6) {
                            deselect();
                            expandOuter(false,0);
                            if(16 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                //check if they selected the right one
                                if(16 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon1_6Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon1_6Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon1_6Selected = true;
                        showHeadings(icon1);
                        showSubHeadings(icon1_6);
                        break;
                }
                return true;
            }
        });
        /* ----------------------------------------------
         * Icon 2 Listeners
         * ---------------------------------------------- */
        icon2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon2.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon2);
                        if(iconSelected && !icon2Selected) {
                            deselect();
                            hideHeadings();
                        }
                        icon2.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected) {
                            deselect();
                            expandOuter(false,2);
                            if(innerIsExpanded) {
                                expandInner(false,2);
                                hideHeadings();
                            }
                            icon2Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon2Selected && !innerIsExpanded) {
                            expandInner(true,2);
                        }
                        iconSelected = true;
                        icon2Selected = true;
                        showHeadings(icon2);
                        break;
                }
                return true;
            }
        });
        /* ----------------------------------------------
         * Icon 2_x Listeners
         * ---------------------------------------------- */
        icon2_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon2_1.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon2_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon2_1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon2);
                        showSubHeadings(icon2_1);
                        updateLastSelectedIcon(icon2_1);
                        if(iconSelected && !icon2_1Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon2_1.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon2_1) {
                            deselect();
                            expandOuter(false,0);
                            if(21 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                //check if they selected the right one
                                if(21 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon2_1Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon2_1Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon2_1Selected = true;
                        showHeadings(icon2);
                        showSubHeadings(icon2_1);
                        break;
                }
                return true;
            }
        });

        icon2_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon2_2.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon2_2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon2_2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon2);
                        showSubHeadings(icon2_2);
                        updateLastSelectedIcon(icon2_2);
                        if(iconSelected && !icon2_2Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon2_2.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon2_2) {
                            deselect();
                            expandOuter(false,0);
                            if(22 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(22 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon2_2Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon2_2Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon2_2Selected = true;
                        showHeadings(icon2);
                        showSubHeadings(icon2_2);
                        break;
                }
                return true;
            }
        });

        icon2_3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon2_3.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon2_3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon2_3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon2);
                        showSubHeadings(icon2_3);
                        updateLastSelectedIcon(icon2_3);
                        if(iconSelected && !icon2_3Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon2_3.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon2_3) {
                            deselect();
                            expandOuter(false,0);
                            if(23 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(23 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon2_3Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon2_3Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon2_3Selected = true;
                        showHeadings(icon2);
                        showSubHeadings(icon2_3);
                        break;
                }
                return true;
            }
        });

        icon2_4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon2_4.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon2_4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon2_4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon2);
                        showSubHeadings(icon2_4);
                        updateLastSelectedIcon(icon2_4);
                        if(iconSelected && !icon2_4Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon2_4.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon2_4) {
                            deselect();
                            expandOuter(false,0);
                            if(24 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(24 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon2_4Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon2_4Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon2_4Selected = true;
                        showHeadings(icon2);
                        showSubHeadings(icon2_4);
                        break;
                }
                return true;
            }
        });

        icon2_5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon2_5.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon2_5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon2_5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon2);
                        showSubHeadings(icon2_5);
                        updateLastSelectedIcon(icon2_5);
                        if(iconSelected && !icon2_5Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon2_5.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon2_5) {
                            deselect();
                            expandOuter(false,0);
                            if(25 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(25 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon2_5Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon2_5Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon2_5Selected = true;
                        showHeadings(icon2);
                        showSubHeadings(icon2_5);
                        break;
                }
                return true;
            }
        });

        icon2_6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon2_6.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon2_6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon2_6.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon2);
                        showSubHeadings(icon2_6);
                        updateLastSelectedIcon(icon2_6);
                        if(iconSelected && !icon2_6Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon2_6.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon2_6) {
                            deselect();
                            expandOuter(false,0);
                            if(26 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(26 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon2_6Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon2_6Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon2_6Selected = true;
                        showHeadings(icon2);
                        showSubHeadings(icon2_6);
                        break;
                }
                return true;
            }
        });
        /* ----------------------------------------------
         * Icon 3 Listeners
         * ---------------------------------------------- */
        icon3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon3.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon3);
                        if(iconSelected && !icon3Selected) {
                            deselect();
                            hideHeadings();
                        }
                        icon3.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected) {
                            deselect();
                            expandOuter(false, 3);
                            if(innerIsExpanded) {
                                expandInner(false,3);
                            }
                            icon3Selected = false;
                            hideHeadings();
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon3Selected && !innerIsExpanded) {
                            expandInner(true,3);
                        }
                        iconSelected = true;
                        icon3Selected = true;
                        showHeadings(icon3);
                        break;
                }
                return true;
            }
        });
        /* ----------------------------------------------
         * Icon 3_x Listeners
         * ---------------------------------------------- */
        icon3_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon3_1.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon3_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon3_1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon3);
                        showSubHeadings(icon3_1);
                        updateLastSelectedIcon(icon3_1);
                        if(iconSelected && !icon3_1Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon3_1.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon3_1) {
                            deselect();
                            expandOuter(false,0);
                            if(31 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(31 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon3_1Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon3_1Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon3_1Selected = true;
                        showHeadings(icon3);
                        showSubHeadings(icon3_1);
                        break;
                }
                return true;
            }
        });

        icon3_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon3_2.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon3_2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon3_2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon3);
                        showSubHeadings(icon3_2);
                        updateLastSelectedIcon(icon3_2);
                        if(iconSelected && !icon3_2Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon3_2.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon3_2) {
                            deselect();
                            expandOuter(false,0);
                            if(32 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(32 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon3_2Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon3_2Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon3_2Selected = true;
                        showHeadings(icon3);
                        showSubHeadings(icon3_2);
                        break;
                }
                return true;
            }
        });

        icon3_3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon3_3.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon3_3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon3_3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon3);
                        showSubHeadings(icon3_3);
                        updateLastSelectedIcon(icon3_3);
                        if(iconSelected && !icon3_3Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon3_3.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon3_3) {
                            deselect();
                            expandOuter(false,0);
                            if(33 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(33 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon3_3Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon3_3Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon3_3Selected = true;
                        showHeadings(icon3);
                        showSubHeadings(icon3_3);
                        break;
                }
                return true;
            }
        });

        icon3_4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon3_4.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon3_4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon3_4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon3);
                        showSubHeadings(icon3_4);
                        updateLastSelectedIcon(icon3_4);
                        if(iconSelected && !icon3_4Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon3_4.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon3_4) {
                            deselect();
                            expandOuter(false,0);
                            if(34 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(34 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon3_4Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon3_4Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon3_4Selected = true;
                        showHeadings(icon3);
                        showSubHeadings(icon3_4);
                        break;
                }
                return true;
            }
        });

        icon3_5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon3_5.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon3_5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon3_5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon3);
                        showSubHeadings(icon3_5);
                        updateLastSelectedIcon(icon3_5);
                        if(iconSelected && !icon3_5Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon3_5.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon3_5) {
                            deselect();
                            expandOuter(false,0);
                            if(35 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(35 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon3_5Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon3_5Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon3_5Selected = true;
                        showHeadings(icon3);
                        showSubHeadings(icon3_5);
                        break;
                }
                return true;
            }
        });
        /* ----------------------------------------------
         * Icon 4 Listeners
         * ---------------------------------------------- */
        icon4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon4.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon4);
                        if(iconSelected && !icon4Selected) {
                            deselect();
                            hideHeadings();
                        }
                        icon4.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected) {
                            deselect();
                            expandOuter(false, 4);
                            if(innerIsExpanded) {
                                expandInner(false,4);
                            }
                            icon4Selected = false;
                            hideHeadings();
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon4Selected && !innerIsExpanded) {
                            expandInner(true,4);
                        }
                        iconSelected = true;
                        icon4Selected = true;
                        showHeadings(icon4);
                        break;
                }
                return true;
            }
        });
        /* ----------------------------------------------
         * Icon 4_x Listeners
         * ---------------------------------------------- */
        icon4_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon4_1.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon4_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon4_1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon4);
                        showSubHeadings(icon4_1);
                        updateLastSelectedIcon(icon4_1);
                        if(iconSelected && !icon4_1Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon4_1.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon4_1) {
                            deselect();
                            expandOuter(false,0);
                            if(41 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(41 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon4_1Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon4_1Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon4_1Selected = true;
                        showHeadings(icon4);
                        showSubHeadings(icon4_1);
                        break;
                }
                return true;
            }
        });

        icon4_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon4_2.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon4_2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon4_2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon4);
                        showSubHeadings(icon4_2);
                        updateLastSelectedIcon(icon4_2);
                        if(iconSelected && !icon4_2Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon4_2.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon4_2) {
                            deselect();
                            expandOuter(false,0);
                            if(42 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(42 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon4_2Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon4_2Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon4_2Selected = true;
                        showHeadings(icon4);
                        showSubHeadings(icon4_2);
                        break;
                }
                return true;
            }
        });

        icon4_3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon4_3.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon4_3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon4_3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon4);
                        showSubHeadings(icon4_3);
                        updateLastSelectedIcon(icon4_3);
                        if(iconSelected && !icon4_3Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon4_3.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon4_3) {
                            deselect();
                            expandOuter(false,0);
                            if(43 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(43 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon4_3Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon4_3Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon4_3Selected = true;
                        showHeadings(icon4);
                        showSubHeadings(icon4_3);
                        break;
                }
                return true;
            }
        });

        icon4_4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon4_4.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon4_4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon4_4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon4);
                        showSubHeadings(icon4_4);
                        updateLastSelectedIcon(icon4_4);
                        if(iconSelected && !icon4_4Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon4_4.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon4_4) {
                            deselect();
                            expandOuter(false,0);
                            if(44 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(44 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon4_4Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon4_4Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon4_4Selected = true;
                        showHeadings(icon4);
                        showSubHeadings(icon4_4);
                        break;
                }
                return true;
            }
        });
        /* ----------------------------------------------
         * Icon 5 Listeners
         * ---------------------------------------------- */
        icon5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon5.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon5);
                        if(iconSelected && !icon5Selected) {
                            deselect();
                            hideHeadings();
                        }
                        icon5.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected) {
                            deselect();
                            expandOuter(false, 5);
                            if(innerIsExpanded) {
                                expandInner(false, 5);
                            }
                            hideHeadings();
                            icon5Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon5Selected && !innerIsExpanded) {
                            expandInner(true, 5);
                        }
                        iconSelected = true;
                        icon5Selected = true;
                        showHeadings(icon5);
                        break;
                }
                return true;
            }
        });
        /* ----------------------------------------------
         * Icon 4_x Listeners
         * ---------------------------------------------- */
        icon5_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon5_1.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon5_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon5_1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon5);
                        showSubHeadings(icon5_1);
                        updateLastSelectedIcon(icon5_1);
                        if(iconSelected && !icon5_1Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon5_1.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon5_1) {
                            deselect();
                            expandOuter(false,0);
                            if(51 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(51 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon5_1Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon5_1Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon5_1Selected = true;
                        showHeadings(icon5);
                        showSubHeadings(icon5_1);
                        break;
                }
                return true;
            }
        });

        icon5_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon5_2.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon5_2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon5_2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon5);
                        showSubHeadings(icon5_2);
                        updateLastSelectedIcon(icon5_2);
                        if(iconSelected && !icon5_2Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon5_2.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon5_2) {
                            deselect();
                            expandOuter(false,0);
                            if(52 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(52 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon5_2Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon5_2Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon5_2Selected = true;
                        showHeadings(icon5);
                        showSubHeadings(icon5_2);
                        break;
                }
                return true;
            }
        });

        icon5_3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon5_3.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon5_3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon5_3.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon5);
                        showSubHeadings(icon5_3);
                        updateLastSelectedIcon(icon5_3);
                        if(iconSelected && !icon5_3Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon5_3.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon5_3) {
                            deselect();
                            expandOuter(false,0);
                            if(53  == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(53  == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon5_3Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon5_3Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon5_3Selected = true;
                        showHeadings(icon5);
                        showSubHeadings(icon5_3);
                        break;
                }
                return true;
            }
        });

        icon5_4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon5_4.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon5_4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon5_4.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon5);
                        showSubHeadings(icon5_4);
                        updateLastSelectedIcon(icon5_4);
                        if(iconSelected && !icon5_4Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon5_4.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon5_4) {
                            deselect();
                            expandOuter(false,0);
                            if(54 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(54 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon5_4Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon5_4Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon5_4Selected = true;
                        showHeadings(icon5);
                        showSubHeadings(icon5_4);
                        break;
                }
                return true;
            }
        });

        icon5_5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon5_5.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon5_5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon5_5.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon5);
                        showSubHeadings(icon5_5);
                        updateLastSelectedIcon(icon5_5);
                        if(iconSelected && !icon5_5Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon5_5.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon5_5) {
                            deselect();
                            expandOuter(false,0);
                            if(55 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(55 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon5_5Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon5_5Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon5_5Selected = true;
                        showHeadings(icon5);
                        showSubHeadings(icon5_5);
                        break;
                }
                return true;
            }
        });

        icon5_6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen


                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon5_6.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon5_6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(button);
                v.startDrag(dragData, null,null,0);

                return false;
            }
        });
        icon5_6.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon5);
                        showSubHeadings(icon5_6);
                        updateLastSelectedIcon(icon5_6);
                        if(iconSelected && !icon5_6Selected) {
                            deselect();
                            hideHeadings();
                            hideSubHeadings();
                        }
                        icon5_6.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        hideSubHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected && getLastSelectedIcon() == icon5_6) {
                            deselect();
                            expandOuter(false,0);
                            if(56 == test1*10 + test2){
                                nextTest();
                            }
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
                                if(56 == test1*10 + test2){
                                    nextTest();
                                }
                            }
                            icon5_6Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon5_6Selected && !innerIsExpanded) {
                            expandInner(true,0);
                        }
                        iconSelected = true;
                        icon5_6Selected = true;
                        showHeadings(icon5);
                        showSubHeadings(icon5_6);
                        break;
                }
                return true;
            }
        });
    }

    /* ----------------------------------------------
     * Expand Methods
     * ---------------------------------------------- */
    public void expandOuter(boolean expand, int icon) {
        if(expand) {
            // Expand outer menu if requirements are met
            // Set the scale of the outer menu and the outer icons to normal
            outerMenu.setScaleX(1);
            outerMenu.setScaleY(1);
            icon1.setImageResource(R.drawable.icon1);
            icon1.setScaleX(1);
            icon1.setScaleY(1);
            icon2.setImageResource(R.drawable.icon2);
            icon2.setScaleX(1);
            icon2.setScaleY(1);
            icon3.setImageResource(R.drawable.icon3);
            icon3.setScaleX(1);
            icon3.setScaleY(1);
            icon4.setImageResource(R.drawable.icon4);
            icon4.setScaleX(1);
            icon4.setScaleY(1);
            icon5.setImageResource(R.drawable.icon5);
            icon5.setScaleX(1);
            icon5.setScaleY(1);

            outerIsExpanded = true;
        }
        else {
            // Shrink the outer menu

            // Set the scale of the outer menu and the icons to 0
            outerMenu.setScaleX(0);
            outerMenu.setScaleY(0);
            icon1.setScaleX(0);
            icon1.setScaleY(0);
            icon2.setScaleX(0);
            icon2.setScaleY(0);
            icon3.setScaleX(0);
            icon3.setScaleY(0);
            icon4.setScaleX(0);
            icon4.setScaleY(0);
            icon5.setScaleX(0);
            icon5.setScaleY(0);

            icon1_1.setScaleX(0);
            icon1_1.setScaleY(0);
            icon1_2.setScaleX(0);
            icon1_2.setScaleY(0);
            icon1_3.setScaleX(0);
            icon1_3.setScaleY(0);
            icon1_4.setScaleX(0);
            icon1_4.setScaleY(0);
            icon1_5.setScaleX(0);
            icon1_5.setScaleY(0);
            icon1_6.setScaleX(0);
            icon1_6.setScaleY(0);

            icon2_1.setScaleX(0);
            icon2_1.setScaleY(0);
            icon2_2.setScaleX(0);
            icon2_2.setScaleY(0);
            icon2_3.setScaleX(0);
            icon2_3.setScaleY(0);
            icon2_4.setScaleX(0);
            icon2_4.setScaleY(0);
            icon2_5.setScaleX(0);
            icon2_5.setScaleY(0);
            icon2_6.setScaleX(0);
            icon2_6.setScaleY(0);

            icon3_1.setScaleX(0);
            icon3_1.setScaleY(0);
            icon3_2.setScaleX(0);
            icon3_2.setScaleY(0);
            icon3_3.setScaleX(0);
            icon3_3.setScaleY(0);
            icon3_4.setScaleX(0);
            icon3_4.setScaleY(0);
            icon3_5.setScaleX(0);
            icon3_5.setScaleY(0);

            icon4_1.setScaleX(0);
            icon4_1.setScaleY(0);
            icon4_2.setScaleX(0);
            icon4_2.setScaleY(0);
            icon4_3.setScaleX(0);
            icon4_3.setScaleY(0);
            icon4_4.setScaleX(0);
            icon4_4.setScaleY(0);

            icon5_1.setScaleX(0);
            icon5_1.setScaleY(0);
            icon5_2.setScaleX(0);
            icon5_2.setScaleY(0);
            icon5_3.setScaleX(0);
            icon5_3.setScaleY(0);
            icon5_4.setScaleX(0);
            icon5_4.setScaleY(0);
            icon5_5.setScaleX(0);
            icon5_5.setScaleY(0);
            icon5_6.setScaleX(0);
            icon5_6.setScaleY(0);
            outerIsExpanded = false;
        }
    }
    public void expandInner(boolean expand, int icon) {
        if(expand && icon == 1) {
            // Expand inner menu if requirements are met
            // Create a scale animation to enlarge the outer menu
            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            expandOuter.setDuration(200);
            expandOuter.setFillAfter(true);

            outerMenu.startAnimation(expandOuter);

            // Make the inner menu visible
            innerMenu.setVisibility(View.VISIBLE);
            icon1Visible ();
            // Change the position of the menu icons
            icon1_1.setX(icon1.getX());
            icon1_1.setY(icon1.getY() - 200f);
            icon1_2.setX(icon2.getX() + 120f);
            icon1_2.setY(icon2.getY() - 150f);
            icon1_3.setX(icon2.getX() + 122f);
            icon1_3.setY(icon3.getY() + 30f);
            icon1_4.setX(icon1.getX());
            icon1_4.setY(icon4.getY() + 200f);
            icon1_5.setX(icon4.getX() - 190f);
            icon1_5.setY(icon5.getY() + 225f);
            icon1_6.setX(icon4.getX() - 190f);
            icon1_6.setY(icon5.getY() - 150f);

            deselectInnerIcons();
            showHeadings(icon1);
            showSubHeadings(icon1_1);
            icon1_1.setBackground(pink_circle);

            innerIsExpanded = true;
        }
        else if(expand && icon == 2) {
            // Expand inner menu if requirements are met
            // Create a scale animation to enlarge the outer menu
            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            expandOuter.setDuration(200);
            expandOuter.setFillAfter(true);

            outerMenu.startAnimation(expandOuter);

            // Make the inner menu visible
            innerMenu.setVisibility(View.VISIBLE);
            icon2Visible ();
            // Change the position of the menu icons
            icon2_1.setX(icon1.getX());
            icon2_1.setY(icon1.getY() - 200f);
            icon2_2.setX(icon2.getX() + 120f);
            icon2_2.setY(icon2.getY() - 150f);
            icon2_3.setX(icon2.getX() + 122f);
            icon2_3.setY(icon3.getY() + 30f);
            icon2_4.setX(icon1.getX());
            icon2_4.setY(icon4.getY() + 200f);
            icon2_5.setX(icon4.getX() - 190f);
            icon2_5.setY(icon5.getY() + 225f);
            icon2_6.setX(icon4.getX() - 190f);
            icon2_6.setY(icon5.getY() - 150f);

            deselectInnerIcons();
            showHeadings(icon2);
            showSubHeadings(icon2_1);
            icon2_1.setBackground(pink_circle);

            innerIsExpanded = true;
        }
        else if(expand && icon == 3) {
            // Expand inner menu if requirements are met
            // Create a scale animation to enlarge the outer menu
            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            expandOuter.setDuration(200);
            expandOuter.setFillAfter(true);

            outerMenu.startAnimation(expandOuter);

            // Make the inner menu visible
            innerMenu.setVisibility(View.VISIBLE);
            icon3Visible ();
            // Change the position of the menu icons
            icon3_1.setX(icon1.getX());
            icon3_1.setY(icon1.getY() - 200f);
            icon3_2.setX(icon2.getX() + 150f);
            icon3_2.setY(icon2.getY() - 100f);
            icon3_3.setX(icon2.getX() + 75f);
            icon3_3.setY(icon3.getY() + 100f);
            icon3_4.setX(icon4.getX() - 120f);
            icon3_4.setY(icon4.getY() + 105f);
            icon3_5.setX(icon4.getX() - 210f);
            icon3_5.setY(icon5.getY() - 100f);

            deselectInnerIcons();
            showHeadings(icon3);
            showSubHeadings(icon3_1);
            icon3_1.setBackground(pink_circle);

            innerIsExpanded = true;
        }
        else if(expand && icon == 4) {
            // Expand inner menu if requirements are met
            // Create a scale animation to enlarge the outer menu
            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            expandOuter.setDuration(200);
            expandOuter.setFillAfter(true);

            outerMenu.startAnimation(expandOuter);

            // Make the inner menu visible
            innerMenu.setVisibility(View.VISIBLE);
            icon4Visible ();
            // Change the position of the menu icons
            icon4_1.setX(icon1.getX());
            icon4_1.setY(icon1.getY() - 200f);
            icon4_2.setX(icon2.getX() + 175f);
            icon4_2.setY(icon2.getY() + 30f);
            icon4_3.setX(icon1.getX());
            icon4_3.setY(icon4.getY() + 200f);
            icon4_4.setX(icon5.getX() - 190);
            icon4_4.setY(icon5.getY() + 30f);

            deselectInnerIcons();
            showHeadings(icon4);
            showSubHeadings(icon4_1);
            icon4_1.setBackground(pink_circle);

            innerIsExpanded = true;
        }
        else if(expand && icon == 5) {
            // Expand inner menu if requirements are met
            // Create a scale animation to enlarge the outer menu
            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            expandOuter.setDuration(200);
            expandOuter.setFillAfter(true);

            outerMenu.startAnimation(expandOuter);

            // Make the inner menu visible
            innerMenu.setVisibility(View.VISIBLE);
            icon5Visible ();
            // Change the position of the menu icons
            icon5_1.setX(icon1.getX());
            icon5_1.setY(icon1.getY() - 200f);
            icon5_2.setX(icon2.getX() + 120f);
            icon5_2.setY(icon2.getY() - 150f);
            icon5_3.setX(icon2.getX() + 122f);
            icon5_3.setY(icon3.getY() + 30f);
            icon5_4.setX(icon1.getX());
            icon5_4.setY(icon4.getY() + 200f);
            icon5_5.setX(icon4.getX() - 190f);
            icon5_5.setY(icon5.getY() + 225f);
            icon5_6.setX(icon4.getX() - 190f);
            icon5_6.setY(icon5.getY() - 150f);

            deselectInnerIcons();
            showHeadings(icon5);
            showSubHeadings(icon5_1);
            icon5_1.setBackground(pink_circle);

            innerIsExpanded = true;
        }
        else {
            // Shrink the inner menu if the requirements are met
            // Create a scale animation to collapse the outer menu
            ScaleAnimation shrinkOuter = new ScaleAnimation(1.7f, 1f, 1.7f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            shrinkOuter.setDuration(200);
            shrinkOuter.setFillAfter(true);

            outerMenu.startAnimation(shrinkOuter);
            reselectInnerIcons();
            // Make the inner menu invisible
            innerMenu.setVisibility(View.INVISIBLE);
            innerIsExpanded = false;
        }
    }

    /* ----------------------------------------------
     * Icon Selection
     * ---------------------------------------------- */
    private void deselect() {
        // Set all backgrounds to empty
        icon1.setBackground(null);icon2.setBackground(null);icon3.setBackground(null);icon4.setBackground(null);icon5.setBackground(null);
        icon1Selected = false;icon2Selected = false;icon3Selected = false;icon4Selected = false;icon5Selected = false;

        icon1_1.setBackground(null);icon1_2.setBackground(null);icon1_3.setBackground(null);icon1_4.setBackground(null);icon1_5.setBackground(null);icon1_6.setBackground(null);
        icon2_1.setBackground(null);icon2_2.setBackground(null);icon2_3.setBackground(null);icon2_4.setBackground(null);icon2_5.setBackground(null);icon2_6.setBackground(null);
        icon3_1.setBackground(null);icon3_2.setBackground(null);icon3_3.setBackground(null);icon3_4.setBackground(null);icon3_5.setBackground(null);
        icon4_1.setBackground(null);icon4_2.setBackground(null);icon4_3.setBackground(null);icon4_4.setBackground(null);
        icon5_1.setBackground(null);icon5_2.setBackground(null);icon5_3.setBackground(null);icon5_4.setBackground(null);icon5_5.setBackground(null);icon5_6.setBackground(null);

        icon1_1Selected = false;icon1_2Selected = false;icon1_3Selected = false;icon1_4Selected = false;icon1_5Selected = false;icon1_6Selected = false;
        icon2_1Selected = false;icon2_2Selected = false;icon2_3Selected = false;icon2_4Selected = false;icon2_5Selected = false;icon2_6Selected = false;
        icon3_1Selected = false;icon3_2Selected = false;icon3_3Selected = false;icon3_4Selected = false;icon3_5Selected = false;
        icon4_1Selected = false;icon4_2Selected = false;icon4_3Selected = false;icon4_4Selected = false;
        icon5_1Selected = false;icon5_2Selected = false;icon5_3Selected = false;icon5_4Selected = false;icon5_5Selected = false;icon5_6Selected = false;

    }

    public void showHeadings(ImageView icon) {
        if(icon == icon1) {findViewById(R.id.icon1Heading).setVisibility(View.VISIBLE);}
        else if (icon == icon2) {findViewById(R.id.icon2Heading).setVisibility(View.VISIBLE);}
        else if (icon == icon3) {findViewById(R.id.icon3Heading).setVisibility(View.VISIBLE);}
        else if (icon == icon4) {findViewById(R.id.icon4Heading).setVisibility(View.VISIBLE);}
        else if (icon == icon5) {findViewById(R.id.icon5Heading).setVisibility(View.VISIBLE);}
    }
    public void showSubHeadings(ImageView icon) {
        if(icon == icon1_1) {findViewById(R.id.icon1_1Heading).setVisibility(View.VISIBLE);}
        if(icon == icon1_2) {findViewById(R.id.icon1_2Heading).setVisibility(View.VISIBLE);}
        if(icon == icon1_3) {findViewById(R.id.icon1_3Heading).setVisibility(View.VISIBLE);}
        if(icon == icon1_4) {findViewById(R.id.icon1_4Heading).setVisibility(View.VISIBLE);}
        if(icon == icon1_5) {findViewById(R.id.icon1_5Heading).setVisibility(View.VISIBLE);}
        if(icon == icon1_6) {findViewById(R.id.icon1_6Heading).setVisibility(View.VISIBLE);}

        if(icon == icon2_1) {findViewById(R.id.icon2_1Heading).setVisibility(View.VISIBLE);}
        if(icon == icon2_2) {findViewById(R.id.icon2_2Heading).setVisibility(View.VISIBLE);}
        if(icon == icon2_3) {findViewById(R.id.icon2_3Heading).setVisibility(View.VISIBLE);}
        if(icon == icon2_4) {findViewById(R.id.icon2_4Heading).setVisibility(View.VISIBLE);}
        if(icon == icon2_5) {findViewById(R.id.icon2_5Heading).setVisibility(View.VISIBLE);}
        if(icon == icon2_6) {findViewById(R.id.icon2_6Heading).setVisibility(View.VISIBLE);}

        if(icon == icon3_1) {findViewById(R.id.icon3_1Heading).setVisibility(View.VISIBLE);}
        if(icon == icon3_2) {findViewById(R.id.icon3_2Heading).setVisibility(View.VISIBLE);}
        if(icon == icon3_3) {findViewById(R.id.icon3_3Heading).setVisibility(View.VISIBLE);}
        if(icon == icon3_4) {findViewById(R.id.icon3_4Heading).setVisibility(View.VISIBLE);}
        if(icon == icon3_5) {findViewById(R.id.icon3_5Heading).setVisibility(View.VISIBLE);}

        if(icon == icon4_1) {findViewById(R.id.icon4_1Heading).setVisibility(View.VISIBLE);}
        if(icon == icon4_2) {findViewById(R.id.icon4_2Heading).setVisibility(View.VISIBLE);}
        if(icon == icon4_3) {findViewById(R.id.icon4_3Heading).setVisibility(View.VISIBLE);}
        if(icon == icon4_4) {findViewById(R.id.icon4_4Heading).setVisibility(View.VISIBLE);}

        if(icon == icon5_1) {findViewById(R.id.icon5_1Heading).setVisibility(View.VISIBLE);}
        if(icon == icon5_2) {findViewById(R.id.icon5_2Heading).setVisibility(View.VISIBLE);}
        if(icon == icon5_3) {findViewById(R.id.icon5_3Heading).setVisibility(View.VISIBLE);}
        if(icon == icon5_4) {findViewById(R.id.icon5_4Heading).setVisibility(View.VISIBLE);}
        if(icon == icon5_5) {findViewById(R.id.icon5_5Heading).setVisibility(View.VISIBLE);}
        if(icon == icon5_6) {findViewById(R.id.icon5_6Heading).setVisibility(View.VISIBLE);}
    }
    public void hideSubHeadings(){
        findViewById(R.id.icon1_1Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon1_2Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon1_3Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon1_4Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon1_5Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon1_6Heading).setVisibility(View.INVISIBLE);

        findViewById(R.id.icon2_1Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon2_2Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon2_3Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon2_4Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon2_5Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon2_6Heading).setVisibility(View.INVISIBLE);

        findViewById(R.id.icon3_1Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon3_2Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon3_3Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon3_4Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon3_5Heading).setVisibility(View.INVISIBLE);

        findViewById(R.id.icon4_1Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon4_2Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon4_3Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon4_4Heading).setVisibility(View.INVISIBLE);

        findViewById(R.id.icon5_1Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon5_2Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon5_3Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon5_4Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon5_5Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon5_6Heading).setVisibility(View.INVISIBLE);
    }
    public void hideHeadings() {
        findViewById(R.id.icon1Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon2Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon3Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon4Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon5Heading).setVisibility(View.INVISIBLE);
    }
    public void icon1Visible(){
        icon1_1.setScaleX(1); icon1_1.setScaleY(1);
        icon1_2.setScaleX(1); icon1_2.setScaleY(1);
        icon1_3.setScaleX(1); icon1_3.setScaleY(1);
        icon1_4.setScaleX(1); icon1_4.setScaleY(1);
        icon1_5.setScaleX(1); icon1_5.setScaleY(1);
        icon1_6.setScaleX(1); icon1_6.setScaleY(1);
    }
    public void icon2Visible(){
        icon2_1.setScaleX(1); icon2_1.setScaleY(1);
        icon2_2.setScaleX(1); icon2_2.setScaleY(1);
        icon2_3.setScaleX(1); icon2_3.setScaleY(1);
        icon2_4.setScaleX(1); icon2_4.setScaleY(1);
        icon2_5.setScaleX(1); icon2_5.setScaleY(1);
        icon2_6.setScaleX(1); icon2_6.setScaleY(1);
    }
    public void icon3Visible(){
        icon3_1.setScaleX(1); icon3_1.setScaleY(1);
        icon3_2.setScaleX(1); icon3_2.setScaleY(1);
        icon3_3.setScaleX(1); icon3_3.setScaleY(1);
        icon3_4.setScaleX(1); icon3_4.setScaleY(1);
        icon3_5.setScaleX(1); icon3_5.setScaleY(1);
    }
    public void icon4Visible(){
        icon4_1.setScaleX(1); icon4_1.setScaleY(1);
        icon4_2.setScaleX(1); icon4_2.setScaleY(1);
        icon4_3.setScaleX(1); icon4_3.setScaleY(1);
        icon4_4.setScaleX(1); icon4_4.setScaleY(1);
    }
    public void icon5Visible(){
        icon5_1.setScaleX(1); icon5_1.setScaleY(1);
        icon5_2.setScaleX(1); icon5_2.setScaleY(1);
        icon5_3.setScaleX(1); icon5_3.setScaleY(1);
        icon5_4.setScaleX(1); icon5_4.setScaleY(1);
        icon5_5.setScaleX(1); icon5_5.setScaleY(1);
        icon5_6.setScaleX(1); icon5_6.setScaleY(1);
    }

    private void deselectInnerIcons() {
        icon1.setX(icon1.getX()); icon1.setY(icon1.getY() - 1000f);
        icon2.setX(icon2.getX() + 1500f); icon2.setY(icon2.getY() - 1000f);
        icon3.setX(icon3.getX() + 1500f); icon3.setY(icon3.getY() + 1000f);
        icon4.setX(icon4.getX()); icon4.setY(icon4.getY() + 1750f);
        icon5.setX(icon5.getX() - 1500f); icon5.setY(icon5.getY() + 1000f);
    }
    private void reselectInnerIcons() {
        icon1.setX(icon1.getX()); icon1.setY(icon1.getY() + 1000f);
        icon2.setX(icon2.getX() - 1500f); icon2.setY(icon2.getY() + 1000f);
        icon3.setX(icon3.getX() - 1500f); icon3.setY(icon3.getY() - 1000f);
        icon4.setX(icon4.getX()); icon4.setY(icon4.getY() - 1750f);
        icon5.setX(icon5.getX() + 1500f); icon5.setY(icon5.getY() - 1000f);
    }

}