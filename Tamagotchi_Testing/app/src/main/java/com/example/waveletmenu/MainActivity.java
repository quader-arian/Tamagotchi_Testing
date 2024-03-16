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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    /* ----------------------------------------------
     * Declare field variables
     * ---------------------------------------------- */
    // Maximum duration for pressing screen
    final static int THRESHOLD = 2500;

    // Six icons are placed evenly around the outer menu
    ImageView icon1, icon2, icon3, icon4, icon5, icon6;

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
    boolean icon1Selected, icon2Selected, icon3Selected,
            icon4Selected, icon5Selected, icon6Selected;

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

        icon1Selected = false;
        icon2Selected = false;
        icon3Selected = false;
        icon4Selected = false;
        icon5Selected = false;
        icon6Selected = false;

        // Initialize the menus
        outerMenu = findViewById(R.id.outerCircle);
        innerMenu = findViewById(R.id.innerCircle);

        // Initialize the buttons
        button = findViewById(R.id.centralButton);

        // Makes sure everything is set up in the correct initial layout
        expandOuter(false);

        /* ----------------------------------------------
         * Button Listeners
         * ---------------------------------------------- */
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    // Duration for the amount of time the user spends holding onto the screen
                    if(innerIsExpanded) {
                        long touchDuration = System.currentTimeMillis() - touchStartTime;

                        // If the duration is over the threshold, deselect all icons and collapse the inner/outer menus
                        if(touchDuration < THRESHOLD) {
                            deselect();
                            expandInner(false);
                            expandOuter(false);
                        }
                        hideHeadings();
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Get the time the user presses on the screen
                    touchStartTime = System.currentTimeMillis();

                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(button);
                    button.startDrag(data, shadowBuilder, button, 0);
                }

                return true;
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
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
         * Menu Listeners
         * ---------------------------------------------- */
        outerMenu.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        if(!outerIsExpanded) {
                            // If the drag enters the outer menu, expand the outer menu
                            expandOuter(true);
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
                            expandOuter(false);
                            icon1Selected = false;
                            hideHeadings();
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon1Selected && !innerIsExpanded) {
                            expandInner(true);
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
                            expandOuter(false);
                            if(innerIsExpanded) {
                                expandInner(false);
                                hideHeadings();
                            }
                            icon2Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon2Selected && !innerIsExpanded) {
                            expandInner(true);
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
                            expandOuter(false);
                            if(innerIsExpanded) {
                                expandInner(false);
                            }
                            icon3Selected = false;
                            hideHeadings();
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon3Selected && !innerIsExpanded) {
                            expandInner(true);
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
                            expandOuter(false);
                            if(innerIsExpanded) {
                                expandInner(false);
                            }
                            icon4Selected = false;
                            hideHeadings();
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon4Selected && !innerIsExpanded) {
                            expandInner(true);
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
                            expandOuter(false);
                            if(innerIsExpanded) {
                                expandInner(false);
                            }
                            hideHeadings();
                            icon5Selected = false;
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon5Selected && !innerIsExpanded) {
                            expandInner(true);
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
                            expandOuter(false);
                            if(innerIsExpanded) {
                                expandInner(false);
                            }
                            icon6Selected = false;
                            hideHeadings();
                        }
                        break;
                    case DragEvent.ACTION_DROP:
                        if(!icon6Selected && !innerIsExpanded) {
                            expandInner(true);
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
    public void expandOuter(boolean expand) {
        if(expand) {
            // Expand outer menu if requirements are met
            // Set the scale of the outer menu and the outer icons to normal
            outerMenu.setScaleX(1);
            outerMenu.setScaleY(1);
            icon1.setScaleX(1);
            icon1.setScaleY(1);
            icon2.setScaleX(1);
            icon2.setScaleY(1);
            icon3.setScaleX(1);
            icon3.setScaleY(1);
            icon4.setScaleX(1);
            icon4.setScaleY(1);
            icon5.setScaleX(1);
            icon5.setScaleY(1);
            icon5.setScaleX(1);
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

            outerIsExpanded = false;
        }
    }
    public void expandInner(boolean expand) {
        if(expand) {
            // Expand inner menu if requirements are met
            // Create a scale animation to enlarge the outer menu
            ScaleAnimation expandOuter = new ScaleAnimation(1.0f, 1.7f, 1.0f, 1.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            expandOuter.setDuration(200);
            expandOuter.setFillAfter(true);

            outerMenu.startAnimation(expandOuter);

            // Make the inner menu visible
            innerMenu.setVisibility(View.VISIBLE);

            // Change the position of the outer menu icons
            icon1.setX(icon1.getX());
            icon1.setY(icon1.getY() - 200f);
            icon2.setX(icon2.getX() + 150f);
            icon2.setY(icon2.getY() - 100f);
            icon3.setX(icon3.getX() + 150f);
            icon3.setY(icon3.getY() + 100f);
            icon4.setX(icon4.getX());
            icon4.setY(icon4.getY() + 175f);
            icon5.setX(icon5.getX() - 150f);
            icon5.setY(icon5.getY() + 100f);
            icon6.setX(icon6.getX() - 150f);
            icon6.setY(icon6.getY() - 100f);

            innerIsExpanded = true;
        }
        else {
            // Shrink the inner menu if the requirements are met
            // Create a scale animation to collapse the outer menu
            ScaleAnimation shrinkOuter = new ScaleAnimation(1.7f, 1f, 1.7f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            shrinkOuter.setDuration(200);
            shrinkOuter.setFillAfter(true);

            outerMenu.startAnimation(shrinkOuter);

            // Make the inner menu invisible
            innerMenu.setVisibility(View.INVISIBLE);

            // Change the position of the outer menu icons
            icon1.setX(icon1.getX());
            icon1.setY(icon1.getY() + 200f);
            icon2.setX(icon2.getX() - 150f);
            icon2.setY(icon2.getY() + 100f);
            icon3.setX(icon3.getX() - 150f);
            icon3.setY(icon3.getY() - 100f);
            icon4.setX(icon4.getX());
            icon4.setY(icon4.getY() - 175f);
            icon5.setX(icon5.getX() + 150f);
            icon5.setY(icon5.getY() - 100f);
            icon6.setX(icon6.getX() + 150f);
            icon6.setY(icon6.getY() + 100f);

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
    }

    public void showHeadings(ImageView icon) {
        if(icon == icon1) {
            findViewById(R.id.icon1Heading).setVisibility(View.VISIBLE);
        }
        else if (icon == icon2) {
            findViewById(R.id.icon2Heading).setVisibility(View.VISIBLE);
        }
        else if (icon == icon3) {
            findViewById(R.id.icon3Heading).setVisibility(View.VISIBLE);
        }
        else if (icon == icon4) {
            findViewById(R.id.icon4Heading).setVisibility(View.VISIBLE);
        }
        else if (icon == icon5) {
            findViewById(R.id.icon5Heading).setVisibility(View.VISIBLE);
        }
        else if (icon == icon6) {
            findViewById(R.id.icon6Heading).setVisibility(View.VISIBLE);
        }
    }

    public void hideHeadings() {
        findViewById(R.id.icon1Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon2Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon3Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon4Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon5Heading).setVisibility(View.INVISIBLE);
        findViewById(R.id.icon6Heading).setVisibility(View.INVISIBLE);
    }

    private void deselectInnerIcons() {

    }
}