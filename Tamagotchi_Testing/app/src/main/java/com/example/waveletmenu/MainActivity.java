package com.example.waveletmenu;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.drawable.Drawable;
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

public class MainActivity extends AppCompatActivity{
    /* ----------------------------------------------
     * Declare field variables
     * ---------------------------------------------- */
    // Maximum duration for pressing screen
    final static int THRESHOLD = 50000;

    // Six icons are placed evenly around the outer menu
    //
    ImageView icon1, icon2, icon3, icon4, icon5, icon6,
            icon1_1, icon1_2, icon1_3, icon1_4, icon1_5, icon1_6,
            icon2_1, icon2_2, icon2_3, icon2_4, icon2_5, icon2_6,
            icon3_1, icon3_2, icon3_3, icon3_4, icon3_5, icon3_6,
            icon4_1, icon4_2, icon4_3, icon4_4, icon4_5, icon4_6,
            icon5_1, icon5_2, icon5_3, icon5_4, icon5_5, icon5_6,
            icon6_1, icon6_2, icon6_3, icon6_4, icon6_5, icon6_6;

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

    // Indicator of which icon is selected
    boolean icon1Selected, icon2Selected, icon3Selected, icon4Selected, icon5Selected, icon6Selected,
            icon1_1Selected, icon1_2Selected, icon1_3Selected, icon1_4Selected, icon1_5Selected, icon1_6Selected,
            icon2_1Selected, icon2_2Selected, icon2_3Selected, icon2_4Selected, icon2_5Selected, icon2_6Selected,
            icon3_1Selected, icon3_2Selected, icon3_3Selected, icon3_4Selected, icon3_5Selected, icon3_6Selected,
            icon4_1Selected, icon4_2Selected, icon4_3Selected, icon4_4Selected, icon4_5Selected, icon4_6Selected,
            icon5_1Selected, icon5_2Selected, icon5_3Selected, icon5_4Selected, icon5_5Selected, icon5_6Selected,
            icon6_1Selected, icon6_2Selected, icon6_3Selected, icon6_4Selected, icon6_5Selected, icon6_6Selected;

    /* ----------------------------------------------
     * onCreate
     * ---------------------------------------------- */
    @SuppressLint({"UseCompatLoadingForDrawables", "ClickableViewAccessibility"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference for selected icon image
        pink_circle = getResources().getDrawable(R.drawable.pink_circle);

        // Initialize the icons
        icon1 = findViewById(R.id.icon1);
        icon2 = findViewById(R.id.icon2);
        icon3 = findViewById(R.id.icon3);
        icon4 = findViewById(R.id.icon4);
        icon5 = findViewById(R.id.icon5);
        icon6 = findViewById(R.id.icon6);

        icon1_1 = findViewById(R.id.icon1_1);
        icon1_2 = findViewById(R.id.icon1_2);
        icon1_3 = findViewById(R.id.icon1_3);
        icon1_4 = findViewById(R.id.icon1_4);
        icon1_5 = findViewById(R.id.icon1_5);
        icon1_6 = findViewById(R.id.icon1_6);

//        icon2_1 = findViewById(R.id.icon2_1);
//        icon2_2 = findViewById(R.id.icon2_2);
//        icon2_3 = findViewById(R.id.icon2_3);
//        icon2_4 = findViewById(R.id.icon2_4);
//        icon2_5 = findViewById(R.id.icon2_5);
//        icon2_6 = findViewById(R.id.icon2_6);
//
//        icon3_1 = findViewById(R.id.icon3_1);
//        icon3_2 = findViewById(R.id.icon3_2);
//        icon3_3 = findViewById(R.id.icon3_3);
//        icon3_4 = findViewById(R.id.icon3_4);
//        icon3_5 = findViewById(R.id.icon3_5);
//        icon3_6 = findViewById(R.id.icon3_6);
//
//        icon4_1 = findViewById(R.id.icon4_1);
//        icon4_2 = findViewById(R.id.icon4_2);
//        icon4_3 = findViewById(R.id.icon4_3);
//        icon4_4 = findViewById(R.id.icon4_4);
//        icon4_5 = findViewById(R.id.icon4_5);
//        icon4_6 = findViewById(R.id.icon4_6);
//
//        icon5_1 = findViewById(R.id.icon5_1);
//        icon5_2 = findViewById(R.id.icon5_2);
//        icon5_3 = findViewById(R.id.icon5_3);
//        icon5_4 = findViewById(R.id.icon5_4);
//        icon5_5 = findViewById(R.id.icon5_5);
//        icon5_6 = findViewById(R.id.icon5_6);
//
//        icon6_1 = findViewById(R.id.icon6_1);
//        icon6_2 = findViewById(R.id.icon6_2);
//        icon6_3 = findViewById(R.id.icon6_3);
//        icon6_4 = findViewById(R.id.icon6_4);
//        icon6_5 = findViewById(R.id.icon6_5);
//        icon6_6 = findViewById(R.id.icon6_6);

        icon1Selected = false;
        icon2Selected = false;
        icon3Selected = false;
        icon4Selected = false;
        icon5Selected = false;
        icon6Selected = false;

        icon1_1Selected = false;
//        icon1_2Selected = false;
//        icon1_3Selected = false;
//        icon1_4Selected = false;
//        icon1_5Selected = false;
//        icon1_6Selected = false;
//
//        icon2_1Selected = false;
//        icon2_2Selected = false;
//        icon2_3Selected = false;
//        icon2_4Selected = false;
//        icon2_5Selected = false;
//        icon2_6Selected = false;
//
//        icon3_1Selected = false;
//        icon3_2Selected = false;
//        icon3_3Selected = false;
//        icon3_4Selected = false;
//        icon3_5Selected = false;
//        icon3_6Selected = false;
//
//        icon4_1Selected = false;
//        icon4_2Selected = false;
//        icon4_3Selected = false;
//        icon4_4Selected = false;
//        icon4_5Selected = false;
//        icon4_6Selected = false;
//
//        icon5_1Selected = false;
//        icon5_2Selected = false;
//        icon5_3Selected = false;
//        icon5_4Selected = false;
//        icon5_5Selected = false;
//        icon5_6Selected = false;
//
//        icon6_1Selected = false;
//        icon6_2Selected = false;
//        icon6_3Selected = false;
//        icon6_4Selected = false;
//        icon6_5Selected = false;
//        icon6_6Selected = false;

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
                    touchStartTime = System.currentTimeMillis();

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
        icon1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen
                    touchStartTime = System.currentTimeMillis();

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
        /* ----------------------------------------------
         * Icon 1_x Listeners
         * ---------------------------------------------- */
        icon1_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen
                    touchStartTime = System.currentTimeMillis();

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
                        if(!iconSelected) {
                            deselect();
                            expandOuter(false,0);
                            if(innerIsExpanded) {
                                expandInner(false,0);
                                hideHeadings();
                                hideSubHeadings();
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

        /* ----------------------------------------------
         * Icon 2 Listeners
         * ---------------------------------------------- */
        icon2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen
                    touchStartTime = System.currentTimeMillis();

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
         * Icon 3 Listeners
         * ---------------------------------------------- */
        icon3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen
                    touchStartTime = System.currentTimeMillis();

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
         * Icon 4 Listeners
         * ---------------------------------------------- */
        icon4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen
                    touchStartTime = System.currentTimeMillis();

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
         * Icon 5 Listeners
         * ---------------------------------------------- */
        icon5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen
                    touchStartTime = System.currentTimeMillis();

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
         * Icon 6 Listeners
         * ---------------------------------------------- */
        icon6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen
                    touchStartTime = System.currentTimeMillis();

                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    icon6.startDrag(data, shadowBuilder, button, 0);
                }
                return true;
            }
        });
        icon6.setOnLongClickListener(new View.OnLongClickListener() {
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
        icon6.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        showHeadings(icon6);
                        if(iconSelected && !icon6Selected) {
                            deselect();
                            hideHeadings();
                        }
                        icon6.setBackground(pink_circle);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        deselect();
                        iconSelected = false;
                        hideHeadings();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if(!iconSelected) {
                            deselect();
                            expandOuter(false, 6);
                            if(innerIsExpanded) {
                                expandInner(false, 6);
                            }
                            icon6Selected = false;
                            hideHeadings();
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon6Selected && !innerIsExpanded) {
                            expandInner(true, 6);
                        }
                        iconSelected = true;
                        icon6Selected = true;
                        showHeadings(icon6);
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
            icon6.setImageResource(R.drawable.icon6);
            icon6.setScaleX(1);
            icon6.setScaleY(1);

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
            icon5.setScaleX(0);
            icon6.setScaleY(0);

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
            icon1_2.setX(icon2.getX() + 150f);
            icon1_2.setY(icon2.getY() - 100f);
            icon1_3.setX(icon3.getX() + 150f);
            icon1_3.setY(icon3.getY() + 100f);
            icon1_4.setX(icon4.getX());
            icon1_4.setY(icon4.getY() + 175f);
            icon1_5.setX(icon5.getX() - 150f);
            icon1_5.setY(icon5.getY() + 100f);
            icon1_6.setX(icon6.getX() - 150f);
            icon1_6.setY(icon6.getY() - 100f);

            deselectInnerIcons();
            showHeadings(icon1);
            showSubHeadings(icon1_1);
            icon1_1.setBackground(pink_circle);

            innerIsExpanded = true;
        }
//        else if(expand && icon == 2) {
//            // Expand inner menu if requirements are met
//            // Create a scale animation to enlarge the outer menu
//            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//
//            expandOuter.setDuration(200);
//            expandOuter.setFillAfter(true);
//
//            outerMenu.startAnimation(expandOuter);
//
//            // Make the inner menu visible
//            innerMenu.setVisibility(View.VISIBLE);
//            icon2Visible ();
//            // Change the position of the menu icons
//            icon2_1.setX(icon1.getX());
//            icon2_1.setY(icon1.getY() - 200f);
//            icon2_2.setX(icon2.getX() + 150f);
//            icon2_2.setY(icon2.getY() - 100f);
//            icon2_3.setX(icon3.getX() + 150f);
//            icon2_3.setY(icon3.getY() + 100f);
//            icon2_4.setX(icon4.getX());
//            icon2_4.setY(icon4.getY() + 175f);
//            icon2_5.setX(icon5.getX() - 150f);
//            icon2_5.setY(icon5.getY() + 100f);
//            icon2_6.setX(icon6.getX() - 150f);
//            icon2_6.setY(icon6.getY() - 100f);
//
//            deselectInnerIcons();
//            showHeadings(icon2);
//            showSubHeadings(icon2_1);
//            icon2_1.setBackground(pink_circle);
//
//            innerIsExpanded = true;
//        }
//        else if(expand && icon == 3) {
//            // Expand inner menu if requirements are met
//            // Create a scale animation to enlarge the outer menu
//            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//
//            expandOuter.setDuration(200);
//            expandOuter.setFillAfter(true);
//
//            outerMenu.startAnimation(expandOuter);
//
//            // Make the inner menu visible
//            innerMenu.setVisibility(View.VISIBLE);
//            icon3Visible ();
//            // Change the position of the menu icons
//            icon3_1.setX(icon1.getX());
//            icon3_1.setY(icon1.getY() - 200f);
//            icon3_2.setX(icon2.getX() + 150f);
//            icon3_2.setY(icon2.getY() - 100f);
//            icon3_3.setX(icon3.getX() + 150f);
//            icon3_3.setY(icon3.getY() + 100f);
//            icon3_4.setX(icon4.getX());
//            icon3_4.setY(icon4.getY() + 175f);
//            icon3_5.setX(icon5.getX() - 150f);
//            icon3_5.setY(icon5.getY() + 100f);
//            icon3_6.setX(icon6.getX() - 150f);
//            icon3_6.setY(icon6.getY() - 100f);
//
//            deselectInnerIcons();
//            showHeadings(icon3);
//            showSubHeadings(icon3_1);
//            icon3_1.setBackground(pink_circle);
//
//            innerIsExpanded = true;
//        }
//        else if(expand && icon == 4) {
//            // Expand inner menu if requirements are met
//            // Create a scale animation to enlarge the outer menu
//            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//
//            expandOuter.setDuration(200);
//            expandOuter.setFillAfter(true);
//
//            outerMenu.startAnimation(expandOuter);
//
//            // Make the inner menu visible
//            innerMenu.setVisibility(View.VISIBLE);
//            icon4Visible ();
//            // Change the position of the menu icons
//            icon4_1.setX(icon1.getX());
//            icon4_1.setY(icon1.getY() - 200f);
//            icon4_2.setX(icon2.getX() + 150f);
//            icon4_2.setY(icon2.getY() - 100f);
//            icon4_3.setX(icon3.getX() + 150f);
//            icon4_3.setY(icon3.getY() + 100f);
//            icon4_4.setX(icon4.getX());
//            icon4_4.setY(icon4.getY() + 175f);
//            icon4_5.setX(icon5.getX() - 150f);
//            icon4_5.setY(icon5.getY() + 100f);
//            icon4_6.setX(icon6.getX() - 150f);
//            icon4_6.setY(icon6.getY() - 100f);
//
//            deselectInnerIcons();
//            showHeadings(icon4);
//            showSubHeadings(icon4_1);
//            icon4_1.setBackground(pink_circle);
//
//            innerIsExpanded = true;
//        }
//        else if(expand && icon == 5) {
//            // Expand inner menu if requirements are met
//            // Create a scale animation to enlarge the outer menu
//            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//
//            expandOuter.setDuration(200);
//            expandOuter.setFillAfter(true);
//
//            outerMenu.startAnimation(expandOuter);
//
//            // Make the inner menu visible
//            innerMenu.setVisibility(View.VISIBLE);
//            icon5Visible ();
//            // Change the position of the menu icons
//            icon5_1.setX(icon1.getX());
//            icon5_1.setY(icon1.getY() - 200f);
//            icon5_2.setX(icon2.getX() + 150f);
//            icon5_2.setY(icon2.getY() - 100f);
//            icon5_3.setX(icon3.getX() + 150f);
//            icon5_3.setY(icon3.getY() + 100f);
//            icon5_4.setX(icon4.getX());
//            icon5_4.setY(icon4.getY() + 175f);
//            icon5_5.setX(icon5.getX() - 150f);
//            icon5_5.setY(icon5.getY() + 100f);
//            icon5_6.setX(icon6.getX() - 150f);
//            icon5_6.setY(icon6.getY() - 100f);
//
//            deselectInnerIcons();
//            showHeadings(icon5);
//            showSubHeadings(icon5_1);
//            icon5_1.setBackground(pink_circle);
//
//            innerIsExpanded = true;
//        }
//        else if(expand && icon == 6) {
//            // Expand inner menu if requirements are met
//            // Create a scale animation to enlarge the outer menu
//            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//
//            expandOuter.setDuration(200);
//            expandOuter.setFillAfter(true);
//
//            outerMenu.startAnimation(expandOuter);
//
//            // Make the inner menu visible
//            innerMenu.setVisibility(View.VISIBLE);
//            icon6Visible ();
//            // Change the position of the menu icons
//            icon6_1.setX(icon1.getX());
//            icon6_1.setY(icon1.getY() - 200f);
//            icon6_2.setX(icon2.getX() + 150f);
//            icon6_2.setY(icon2.getY() - 100f);
//            icon6_3.setX(icon3.getX() + 150f);
//            icon6_3.setY(icon3.getY() + 100f);
//            icon6_4.setX(icon4.getX());
//            icon6_4.setY(icon4.getY() + 175f);
//            icon6_5.setX(icon5.getX() - 150f);
//            icon6_5.setY(icon5.getY() + 100f);
//            icon6_6.setX(icon6.getX() - 150f);
//            icon6_6.setY(icon6.getY() - 100f);
//
//            deselectInnerIcons();
//            showHeadings(icon6);
//            showSubHeadings(icon6_1);
//            icon6_1.setBackground(pink_circle);
//
//            innerIsExpanded = true;
//        }
        else if(expand && icon > 1) {
            // Expand inner menu if requirements are met
            // Create a scale animation to enlarge the outer menu
            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            expandOuter.setDuration(200);
            expandOuter.setFillAfter(true);

            outerMenu.startAnimation(expandOuter);

            // Make the inner menu visible
            innerMenu.setVisibility(View.VISIBLE);

            // Change the position of the outer menu icons
//            iconX_1.setX(icon1.getX());
//            iconX_1.setY(icon1.getY() - 200f);
//            iconX_2.setX(icon2.getX() + 150f);
//            iconX_2.setY(icon2.getY() - 100f);
//            iconX_3.setX(icon3.getX() + 150f);
//            iconX_3.setY(icon3.getY() + 100f);
//            iconX_4.setX(icon4.getX());
//            iconX_4.setY(icon4.getY() + 175f);
//            iconX_5.setX(icon5.getX() - 150f);
//            iconX_5.setY(icon5.getY() + 100f);
//            iconX_6.setX(icon6.getX() - 150f);
//            iconX_6.setY(icon6.getY() - 100f);

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
        icon1.setBackground(null);
        icon2.setBackground(null);
        icon3.setBackground(null);
        icon4.setBackground(null);
        icon5.setBackground(null);
        icon6.setBackground(null);

        icon1Selected = false;
        icon2Selected = false;
        icon3Selected = false;
        icon4Selected = false;
        icon5Selected = false;
        icon6Selected = false;

        icon1_1.setBackground(null);
//        icon1_2.setBackground(null);
//        icon1_3.setBackground(null);
//        icon1_4.setBackground(null);
//        icon1_5.setBackground(null);
//        icon1_6.setBackground(null);

//        icon2_1.setBackground(null);
//        icon2_2.setBackground(null);
//        icon2_3.setBackground(null);
//        icon2_4.setBackground(null);
//        icon2_5.setBackground(null);
//        icon2_6.setBackground(null);

//        icon3_1.setBackground(null);
//        icon3_2.setBackground(null);
//        icon3_3.setBackground(null);
//        icon3_4.setBackground(null);
//        icon3_5.setBackground(null);
//        icon3_6.setBackground(null);

//        icon4_1.setBackground(null);
//        icon4_2.setBackground(null);
//        icon4_3.setBackground(null);
//        icon4_4.setBackground(null);
//        icon4_5.setBackground(null);
//        icon4_6.setBackground(null);

//        icon5_1.setBackground(null);
//        icon5_2.setBackground(null);
//        icon5_3.setBackground(null);
//        icon5_4.setBackground(null);
//        icon5_5.setBackground(null);
//        icon5_6.setBackground(null);

//        icon6_1.setBackground(null);
//        icon6_2.setBackground(null);
//        icon6_3.setBackground(null);
//        icon6_4.setBackground(null);
//        icon6_5.setBackground(null);
//        icon6_6.setBackground(null);

        icon1_1Selected = false;
//        icon1_2Selected = false;
//        icon1_3Selected = false;
//        icon1_4Selected = false;
//        icon1_5Selected = false;
//        icon1_6Selected = false;
//
//        icon2_1Selected = false;
//        icon2_2Selected = false;
//        icon2_3Selected = false;
//        icon2_4Selected = false;
//        icon2_5Selected = false;
//        icon2_6Selected = false;
//
//        icon3_1Selected = false;
//        icon3_2Selected = false;
//        icon3_3Selected = false;
//        icon3_4Selected = false;
//        icon3_5Selected = false;
//        icon3_6Selected = false;
//
//        icon4_1Selected = false;
//        icon4_2Selected = false;
//        icon4_3Selected = false;
//        icon4_4Selected = false;
//        icon4_5Selected = false;
//        icon4_6Selected = false;
//
//        icon5_1Selected = false;
//        icon5_2Selected = false;
//        icon5_3Selected = false;
//        icon5_4Selected = false;
//        icon5_5Selected = false;
//        icon5_6Selected = false;
//
//        icon6_1Selected = false;
//        icon6_2Selected = false;
//        icon6_3Selected = false;
//        icon6_4Selected = false;
//        icon6_5Selected = false;
//        icon6_6Selected = false;
    }

    public void showHeadings(ImageView icon) {
        if(icon == icon1) {findViewById(R.id.icon1Heading).setVisibility(View.VISIBLE);}
        else if (icon == icon2) {findViewById(R.id.icon2Heading).setVisibility(View.VISIBLE);}
        else if (icon == icon3) {findViewById(R.id.icon3Heading).setVisibility(View.VISIBLE);}
        else if (icon == icon4) {findViewById(R.id.icon4Heading).setVisibility(View.VISIBLE);}
        else if (icon == icon5) {findViewById(R.id.icon5Heading).setVisibility(View.VISIBLE);}
        else if (icon == icon6) {findViewById(R.id.icon6Heading).setVisibility(View.VISIBLE);}
    }
    public void showSubHeadings(ImageView icon) {
        if(icon == icon1_1) {findViewById(R.id.icon1_1Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon1_2) {findViewById(R.id.icon1_2Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon1_3) {findViewById(R.id.icon1_3Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon1_4) {findViewById(R.id.icon1_4Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon1_5) {findViewById(R.id.icon1_5Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon1_6) {findViewById(R.id.icon1_6Heading).setVisibility(View.VISIBLE);}

//        if(icon == icon2_1) {findViewById(R.id.icon2_1Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon2_2) {findViewById(R.id.icon2_2Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon2_3) {findViewById(R.id.icon2_3Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon2_4) {findViewById(R.id.icon2_4Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon2_5) {findViewById(R.id.icon2_5Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon2_6) {findViewById(R.id.icon2_6Heading).setVisibility(View.VISIBLE);}

//        if(icon == icon3_1) {findViewById(R.id.icon3_1Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon3_2) {findViewById(R.id.icon3_2Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon3_3) {findViewById(R.id.icon3_3Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon3_4) {findViewById(R.id.icon3_4Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon3_5) {findViewById(R.id.icon3_5Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon3_6) {findViewById(R.id.icon3_6Heading).setVisibility(View.VISIBLE);}

//        if(icon == icon4_1) {findViewById(R.id.icon4_1Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon4_2) {findViewById(R.id.icon4_2Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon4_3) {findViewById(R.id.icon4_3Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon4_4) {findViewById(R.id.icon4_4Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon4_5) {findViewById(R.id.icon4_5Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon4_6) {findViewById(R.id.icon4_6Heading).setVisibility(View.VISIBLE);}

//        if(icon == icon5_1) {findViewById(R.id.icon5_1Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon5_2) {findViewById(R.id.icon5_2Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon5_3) {findViewById(R.id.icon5_3Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon5_4) {findViewById(R.id.icon5_4Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon5_5) {findViewById(R.id.icon5_5Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon5_6) {findViewById(R.id.icon5_6Heading).setVisibility(View.VISIBLE);}

//        if(icon == icon6_1) {findViewById(R.id.icon6_1Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon6_2) {findViewById(R.id.icon6_2Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon6_3) {findViewById(R.id.icon6_3Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon6_4) {findViewById(R.id.icon6_4Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon6_5) {findViewById(R.id.icon6_5Heading).setVisibility(View.VISIBLE);}
//        if(icon == icon6_6) {findViewById(R.id.icon6_6Heading).setVisibility(View.VISIBLE);}

    }
    public void hideSubHeadings(){
        findViewById(R.id.icon1_1Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon1_2Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon1_3Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon1_4Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon1_5Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon1_6Heading).setVisibility(View.INVISIBLE);
//
//        findViewById(R.id.icon2_1Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon2_2Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon2_3Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon2_4Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon2_5Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon2_6Heading).setVisibility(View.INVISIBLE);
//
//        findViewById(R.id.icon3_1Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon3_2Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon3_3Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon3_4Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon3_5Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon3_6Heading).setVisibility(View.INVISIBLE);
//
//        findViewById(R.id.icon4_1Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon4_2Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon4_3Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon4_4Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon4_5Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon4_6Heading).setVisibility(View.INVISIBLE);
//
//        findViewById(R.id.icon5_1Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon5_2Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon5_3Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon5_4Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon5_5Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon5_6Heading).setVisibility(View.INVISIBLE);
//
//        findViewById(R.id.icon6_1Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon6_2Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon6_3Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon6_4Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon6_5Heading).setVisibility(View.INVISIBLE);
//        findViewById(R.id.icon6_6Heading).setVisibility(View.INVISIBLE);
    }
    public void hideHeadings() {
        findViewById(R.id.icon1Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon2Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon3Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon4Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon5Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon6Heading).setVisibility(View.INVISIBLE);
    }
    public void icon1Visible(){
        icon1_1.setScaleX(1);
        icon1_1.setScaleY(1);
        icon1_2.setScaleX(1);
        icon1_2.setScaleY(1);
        icon1_3.setScaleX(1);
        icon1_3.setScaleY(1);
        icon1_4.setScaleX(1);
        icon1_4.setScaleY(1);
        icon1_5.setScaleX(1);
        icon1_5.setScaleY(1);
        icon1_6.setScaleX(1);
        icon1_6.setScaleY(1);
    }
//    public void icon2Visible(){
//        icon2_1.setScaleX(1);
//        icon2_1.setScaleY(1);
//        icon2_2.setScaleX(1);
//        icon2_2.setScaleY(1);
//        icon2_3.setScaleX(1);
//        icon2_3.setScaleY(1);
//        icon2_4.setScaleX(1);
//        icon2_4.setScaleY(1);
//        icon2_5.setScaleX(1);
//        icon2_5.setScaleY(1);
//        icon2_6.setScaleX(1);
//        icon2_6.setScaleY(1);
//    }
//    public void icon3Visible(){
//        icon3_1.setScaleX(1);
//        icon3_1.setScaleY(1);
//        icon3_2.setScaleX(1);
//        icon3_2.setScaleY(1);
//        icon3_3.setScaleX(1);
//        icon3_3.setScaleY(1);
//        icon3_4.setScaleX(1);
//        icon3_4.setScaleY(1);
//        icon3_5.setScaleX(1);
//        icon3_5.setScaleY(1);
//        icon3_6.setScaleX(1);
//        icon3_6.setScaleY(1);
//    }
//    public void icon4Visible(){
//        icon4_1.setScaleX(1);
//        icon4_1.setScaleY(1);
//        icon4_2.setScaleX(1);
//        icon4_2.setScaleY(1);
//        icon4_3.setScaleX(1);
//        icon4_3.setScaleY(1);
//        icon4_4.setScaleX(1);
//        icon4_4.setScaleY(1);
//        icon4_5.setScaleX(1);
//        icon4_5.setScaleY(1);
//        icon4_6.setScaleX(1);
//        icon4_6.setScaleY(1);
//    }
//    public void icon5Visible(){
//        icon5_1.setScaleX(1);
//        icon5_1.setScaleY(1);
//        icon5_2.setScaleX(1);
//        icon5_2.setScaleY(1);
//        icon5_3.setScaleX(1);
//        icon5_3.setScaleY(1);
//        icon5_4.setScaleX(1);
//        icon5_4.setScaleY(1);
//        icon5_5.setScaleX(1);
//        icon5_5.setScaleY(1);
//        icon5_6.setScaleX(1);
//        icon5_6.setScaleY(1);
//    }
//    public void icon6Visible(){
//        icon6_1.setScaleX(1);
//        icon6_1.setScaleY(1);
//        icon6_2.setScaleX(1);
//        icon6_2.setScaleY(1);
//        icon6_3.setScaleX(1);
//        icon6_3.setScaleY(1);
//        icon6_4.setScaleX(1);
//        icon6_4.setScaleY(1);
//        icon6_5.setScaleX(1);
//        icon6_5.setScaleY(1);
//        icon6_6.setScaleX(1);
//        icon6_6.setScaleY(1);
//    }

    private void deselectInnerIcons() {
        icon1.setX(icon1.getX());
        icon1.setY(icon1.getY() - 1000f);
        icon2.setX(icon2.getX() + 1500f);
        icon2.setY(icon2.getY() - 1000f);
        icon3.setX(icon3.getX() + 1500f);
        icon3.setY(icon3.getY() + 1000f);
        icon4.setX(icon4.getX());
        icon4.setY(icon4.getY() + 1750f);
        icon5.setX(icon5.getX() - 1500f);
        icon5.setY(icon5.getY() + 1000f);
        icon6.setX(icon6.getX() - 1500f);
        icon6.setY(icon6.getY() - 1000f);
    }
    private void reselectInnerIcons() {
        icon1.setX(icon1.getX());
        icon1.setY(icon1.getY() + 1000f);
        icon2.setX(icon2.getX() - 1500f);
        icon2.setY(icon2.getY() + 1000f);
        icon3.setX(icon3.getX() - 1500f);
        icon3.setY(icon3.getY() - 1000f);
        icon4.setX(icon4.getX());
        icon4.setY(icon4.getY() - 1750f);
        icon5.setX(icon5.getX() + 1500f);
        icon5.setY(icon5.getY() - 1000f);
        icon6.setX(icon6.getX() + 1500f);
        icon6.setY(icon6.getY() + 1000f);
    }
    
}