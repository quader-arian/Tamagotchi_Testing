package com.example.waveletmenu;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class DragDropActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    boolean isOnPage1, isOnPage2, isOnPage3, isOnPage4, isOnPage5;
    private float x1, x2, y1, y2;
    private static final int MIN_DISTANCE = 150;
    private GestureDetector gestureDetector;

    ImageView page1Icon1, page1Icon2, page1Icon3,
            page1Icon4, page1Icon5, page1Icon6,
            bunny, arrowLeft, arrowRight,
            heading1, heading2, heading3, heading4, heading5;

    ImageView page2Icon1, page2Icon2, page2Icon3, page2Icon4, page2Icon5, page2Icon6;
    ImageView page3Icon1, page3Icon2, page3Icon3, page3Icon4, page3Icon5;
    ImageView page4Icon1, page4Icon2, page4Icon3, page4Icon4, page5Icon5, page5Icon6;
    ImageView page5Icon1, page5Icon2, page5Icon3, page5Icon4;
    ImageView subOption, mainOption;
    ImageView testImage1, testImage2;
    private long touchStartTime = 0;
    int test1;
    int test2;
    int current;
    int count = 11; //exclusive
    ResultsTextMaker results;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                float valueX = x2 - x1;

                if(Math.abs(valueX) > MIN_DISTANCE) {
                    if(x2>x1) {
                        // Swiped Right
                        prevPage();
                        makeVisible();
                    }
                    else {
                        // Swiped Left
                        nextPage();
                        makeVisible();
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void nextTest(){
        Random rand = new Random();
        test1 = rand.nextInt(5)+1;
        if(test1 == 4){
            test2 = rand.nextInt(3)+1;
        }else if(test1 == 3){
            test2 = rand.nextInt(5)+1;
        }else{
            test2 = rand.nextInt(6)+1;
        }


        String iconCode1 = "icon"+test1+"_"+"heading";
        String iconCode2 = "icon"+test1+"_"+test2;

        if((test1 ==4 && test2==5)|| test1 ==3 && test2==6){
            iconCode2 = "icon"+test1;
        }

        count--;
        Log.v("HELP", iconCode2);
        int id1 = getResources().getIdentifier(iconCode1, "drawable", getPackageName());
        int id2 = getResources().getIdentifier(iconCode2, "drawable", getPackageName());
        testImage1.setImageResource(id1);
        testImage2.setImageResource(id2);

        if((-1) * (count - 10) != 0){
            results.WriteToFile("DragNDrop", String.format("%d",(count-10)*-1), String.format("%d",System.currentTimeMillis() - touchStartTime));
        }

        touchStartTime = System.currentTimeMillis();
        if(count < 0){
            try {
                results.PublishFile(getApplicationContext());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            startActivity(new Intent(DragDropActivity.this, HomeActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragdrop);

        String name = getIntent().getStringExtra("Name");
        File path = getApplicationContext().getFilesDir();
        String pa = path.getPath();
        results = new ResultsTextMaker("DragNDrop",name, path);
        results.WriteToFile("Linear", name, "...");

        bunny = findViewById(R.id.bunny);

        page1Icon1 = findViewById(R.id.page1Icon1);
        page1Icon2 = findViewById(R.id.page1Icon2);
        page1Icon3 = findViewById(R.id.page1Icon3);
        page1Icon4 = findViewById(R.id.page1Icon4);
        page1Icon5 = findViewById(R.id.page1Icon5);
        page1Icon6 = findViewById(R.id.page1Icon6);

        page2Icon1 = findViewById(R.id.page2Icon1);
        page2Icon2 = findViewById(R.id.page2Icon2);
        page2Icon3 = findViewById(R.id.page2Icon3);
        page2Icon4 = findViewById(R.id.page2Icon4);
        page2Icon5 = findViewById(R.id.page2Icon5);
        page2Icon6 = findViewById(R.id.page2Icon6);

        page3Icon1 = findViewById(R.id.page3Icon1);
        page3Icon2 = findViewById(R.id.page3Icon2);
        page3Icon3 = findViewById(R.id.page3Icon3);
        page3Icon4 = findViewById(R.id.page3Icon4);
        page3Icon5 = findViewById(R.id.page3Icon5);

        page4Icon1 = findViewById(R.id.page4Icon1);
        page4Icon2 = findViewById(R.id.page4Icon2);
        page4Icon3 = findViewById(R.id.page4Icon3);
        page4Icon4 = findViewById(R.id.page4Icon4);

        page5Icon1 = findViewById(R.id.page5Icon1);
        page5Icon2 = findViewById(R.id.page5Icon2);
        page5Icon3 = findViewById(R.id.page5Icon3);
        page5Icon4 = findViewById(R.id.page5Icon4);
        page5Icon5 = findViewById(R.id.page5Icon5);
        page5Icon6 = findViewById(R.id.page5Icon6);

        heading1 = findViewById(R.id.page1Heading);
        heading2 = findViewById(R.id.page2Heading);
        heading3 = findViewById(R.id.page3Heading);
        heading4 = findViewById(R.id.page4Heading);
        heading5 = findViewById(R.id.page5Heading);

        testImage1 = findViewById(R.id.from);
        testImage2 = findViewById(R.id.to);
        current = 0;
        touchStartTime = System.currentTimeMillis();
        nextTest();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        arrowLeft = findViewById(R.id.arrowLeft);
        arrowRight = findViewById(R.id.arrowRight);

        subOption = findViewById(R.id.subOption);
        mainOption = findViewById(R.id.mainOption);

        this.gestureDetector = new GestureDetector(DragDropActivity.this, this);

        final MediaPlayer ping = MediaPlayer.create(this, R.raw.click);

        isOnPage1 = true;
        isOnPage2 = false;
        isOnPage3 = false;
        isOnPage4 = false;
        isOnPage5 = false;

        bunny.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (event.getAction() == DragEvent.ACTION_DRAG_ENTERED) {
                    ping.start();
                    //in = true;
                }
                if (event.getAction() == DragEvent.ACTION_DRAG_EXITED) {
                    subOption.setVisibility(View.INVISIBLE);
                    //in = false;
                }
                if (event.getAction() == DragEvent.ACTION_DROP) {
                    subOption.setVisibility(View.INVISIBLE);
                    if(current == test1*10 + test2){
                        nextTest();
                    }
                }
                return true;
            }
        });

        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevPage();
                makeVisible();
            }
        });

        arrowRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                nextPage();
                makeVisible();
            }
        });

        page1Icon1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page1Icon1);
                    page1Icon1.startDrag(data, shadowBuilder, page1Icon1, 0);
                    getSubOption(page1Icon1);
                    Log.v("HELP", "ok so here?");
                }
                return true;
            }
        });
        page1Icon1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page1Icon1);
                v.startDrag(dragData, null, null, 0);
                Log.v("HELP", "finish??");
                return false;
            }
        });

        page1Icon2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page1Icon2);
                    page1Icon2.startDrag(data, shadowBuilder, page1Icon2, 0);
                    getSubOption(page1Icon2);
                }
                return true;
            }
        });
        page1Icon2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page1Icon2);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page1Icon3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page1Icon3);
                    page1Icon3.startDrag(data, shadowBuilder, page1Icon3, 0);
                    getSubOption(page1Icon3);
                }

                return true;
            }
        });
        page1Icon3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page1Icon3);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page1Icon4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page1Icon4);
                    page1Icon4.startDrag(data, shadowBuilder, page1Icon4, 0);
                    getSubOption(page1Icon4);
                }
                return true;
            }
        });
        page1Icon4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page1Icon4);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page1Icon5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page1Icon5);
                    page1Icon5.startDrag(data, shadowBuilder, page1Icon5, 0);
                    getSubOption(page1Icon5);
                }

                return true;
            }
        });
        page1Icon5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page1Icon5);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page1Icon6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page1Icon6);
                    page1Icon6.startDrag(data, shadowBuilder, page1Icon6, 0);
                    getSubOption(page1Icon6);
                }

                return true;
            }
        });
        page1Icon6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page1Icon6);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        // ---------------------------------------------------------------------------------------------------

        page2Icon1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page2Icon1);
                    page2Icon1.startDrag(data, shadowBuilder, page2Icon1, 0);
                    getSubOption(page2Icon1);
                }

                return true;
            }
        });
        page2Icon1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page2Icon1);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page2Icon2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page2Icon2);
                    page2Icon2.startDrag(data, shadowBuilder, page2Icon2, 0);
                    getSubOption(page2Icon2);
                }

                return true;
            }
        });
        page2Icon2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page2Icon2);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page2Icon3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page2Icon3);
                    page2Icon3.startDrag(data, shadowBuilder, page2Icon3, 0);
                    getSubOption(page2Icon3);
                }

                return true;
            }
        });
        page2Icon3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page2Icon3);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page2Icon4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page2Icon4);
                    page2Icon4.startDrag(data, shadowBuilder, page2Icon4, 0);
                    getSubOption(page2Icon4);
                }

                return true;
            }
        });
        page2Icon4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page2Icon4);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page2Icon5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page2Icon5);
                    page2Icon5.startDrag(data, shadowBuilder, page2Icon5, 0);
                    getSubOption(page2Icon5);
                }

                return true;
            }
        });
        page2Icon5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page2Icon5);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page2Icon6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page2Icon6);
                    page2Icon6.startDrag(data, shadowBuilder, page2Icon6, 0);
                    getSubOption(page2Icon6);
                }

                return true;
            }
        });
        page2Icon6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page2Icon6);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        // -------------------------------------------------------------------------------------------

        page3Icon1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page3Icon1);
                    page3Icon1.startDrag(data, shadowBuilder, page3Icon1, 0);
                    getSubOption(page3Icon1);
                }

                return true;
            }
        });
        page3Icon1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page3Icon1);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page3Icon2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page3Icon2);
                    page3Icon2.startDrag(data, shadowBuilder, page3Icon2, 0);
                    getSubOption(page3Icon2);
                }

                return true;
            }
        });
        page3Icon2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page3Icon2);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page3Icon3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page3Icon3);
                    page3Icon3.startDrag(data, shadowBuilder, page3Icon3, 0);
                    getSubOption(page3Icon3);
                }

                return true;
            }
        });
        page3Icon3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page3Icon3);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page3Icon4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page3Icon4);
                    page3Icon4.startDrag(data, shadowBuilder, page3Icon4, 0);
                    getSubOption(page3Icon4);
                }

                return true;
            }
        });
        page3Icon4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page3Icon4);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page3Icon5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page3Icon5);
                    page3Icon5.startDrag(data, shadowBuilder, page3Icon5, 0);
                    getSubOption(page3Icon5);
                }

                return true;
            }
        });
        page3Icon5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page3Icon5);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        // --------------------------------------------------------------------------------

        page4Icon1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page4Icon1);
                    page4Icon1.startDrag(data, shadowBuilder, page4Icon1, 0);
                    getSubOption(page4Icon1);
                }

                return true;
            }
        });
        page4Icon1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page4Icon1);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page4Icon2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page4Icon2);
                    page4Icon2.startDrag(data, shadowBuilder, page4Icon2, 0);
                    getSubOption(page4Icon2);
                }

                return true;
            }
        });
        page4Icon2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page4Icon2);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page4Icon3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page4Icon3);
                    page4Icon3.startDrag(data, shadowBuilder, page4Icon3, 0);
                    getSubOption(page4Icon3);
                }
                return true;
            }
        });
        page4Icon3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page4Icon3);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page4Icon4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page4Icon4);
                    page4Icon4.startDrag(data, shadowBuilder, page4Icon4, 0);
                    getSubOption(page4Icon4);
                }

                return true;
            }
        });
        page4Icon4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page4Icon4);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });



        // -----------------------------------------------------------------------------------------

        page5Icon1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page5Icon1);
                    page5Icon1.startDrag(data, shadowBuilder, page5Icon1, 0);
                    getSubOption(page5Icon1);
                }

                return true;
            }
        });
        page5Icon1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page5Icon1);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page5Icon2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page5Icon2);
                    page5Icon2.startDrag(data, shadowBuilder, page5Icon2, 0);
                    getSubOption(page5Icon2);
                }

                return true;
            }
        });
        page5Icon2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page5Icon2);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page5Icon3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page5Icon3);
                    page5Icon3.startDrag(data, shadowBuilder, page5Icon3, 0);
                    getSubOption(page5Icon3);
                }

                return true;
            }
        });
        page5Icon3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page5Icon3);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });

        page5Icon4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page5Icon4);
                    page5Icon4.startDrag(data, shadowBuilder, page5Icon4, 0);
                    getSubOption(page5Icon4);
                }

                return true;
            }
        });
        page5Icon4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page5Icon4);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });
        page5Icon5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page5Icon5);
                    page5Icon5.startDrag(data, shadowBuilder, page5Icon5, 0);
                    getSubOption(page5Icon5);
                }

                return true;
            }
        });
        page5Icon5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page5Icon5);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });
        page5Icon6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    @SuppressLint("ClickableViewAccessibility") ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(page5Icon6);
                    page5Icon6.startDrag(data, shadowBuilder, page5Icon6, 0);
                    getSubOption(page5Icon6);
                }

                return true;
            }
        });
        page5Icon6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(page5Icon6);
                v.startDrag(dragData, null, null, 0);

                return false;
            }
        });
    }

    private void getSubOption(View v) {
        if (v.getId() == R.id.page1Icon1) {
            subOption.setImageResource(R.drawable.icon1_1heading);
            current = 11;
        } else if (v.getId() == R.id.page1Icon2) {
            subOption.setImageResource(R.drawable.icon1_2heading);
            current = 12;
        } else if (v.getId() == R.id.page1Icon3) {
            subOption.setImageResource(R.drawable.icon1_3heading);
            current = 13;
        } else if (v.getId() == R.id.page1Icon4) {
            subOption.setImageResource(R.drawable.icon1_4heading);
            current = 14;
        } else if (v.getId() == R.id.page1Icon5) {
            subOption.setImageResource(R.drawable.icon1_5heading);
            current = 15;
        } else if (v.getId() == R.id.page1Icon6) {
            subOption.setImageResource(R.drawable.icon1_6heading);
            current = 16;
        } else if (v.getId() == R.id.page2Icon1) {
            subOption.setImageResource(R.drawable.icon2_1heading);
            current = 21;
        } else if (v.getId() == R.id.page2Icon2) {
            subOption.setImageResource(R.drawable.icon2_2heading);
            current = 22;
        } else if (v.getId() == R.id.page2Icon3) {
            subOption.setImageResource(R.drawable.icon2_3heading);
            current = 23;
        } else if (v.getId() == R.id.page2Icon4) {
            subOption.setImageResource(R.drawable.icon2_4heading);
            current = 24;
        } else if (v.getId() == R.id.page2Icon5) {
            subOption.setImageResource(R.drawable.icon2_5heading);
            current = 25;
        } else if (v.getId() == R.id.page2Icon6) {
            subOption.setImageResource(R.drawable.icon2_6heading);
            current = 26;
        } else if (v.getId() == R.id.page3Icon1) {
            subOption.setImageResource(R.drawable.icon3_1heading);
            current = 31;
        } else if (v.getId() == R.id.page3Icon2) {
            subOption.setImageResource(R.drawable.icon3_2heading);
            current = 32;
        } else if (v.getId() == R.id.page3Icon3) {
            subOption.setImageResource(R.drawable.icon3_3heading);
            current = 33;
        } else if (v.getId() == R.id.page3Icon4) {
            subOption.setImageResource(R.drawable.icon3_4heading);
            current = 34;
        } else if (v.getId() == R.id.page3Icon5) {
            subOption.setImageResource(R.drawable.icon3_5heading);
            current = 35;
        } else if (v.getId() == R.id.page4Icon1) {
            subOption.setImageResource(R.drawable.icon4_1heading);
            current = 41;
        } else if (v.getId() == R.id.page4Icon2) {
            subOption.setImageResource(R.drawable.icon4_2heading);
            current = 42;
        } else if (v.getId() == R.id.page4Icon3) {
            subOption.setImageResource(R.drawable.icon4_3heading);
            current = 43;
        } else if (v.getId() == R.id.page4Icon4) {
            subOption.setImageResource(R.drawable.icon4_4heading);
            current = 44;
        } else if (v.getId() == R.id.page5Icon1) {
            subOption.setImageResource(R.drawable.icon5_1heading);
            current = 51;
        } else if (v.getId() == R.id.page5Icon2) {
            subOption.setImageResource(R.drawable.icon5_2heading);
            current = 52;
        } else if (v.getId() == R.id.page5Icon3) {
            subOption.setImageResource(R.drawable.icon5_3heading);
            current = 53;
        } else if (v.getId() == R.id.page5Icon4) {
            subOption.setImageResource(R.drawable.icon5_4heading);
            current = 54;
        } else if (v.getId() == R.id.page5Icon5) {
            subOption.setImageResource(R.drawable.icon5_5heading);
            current = 55;
        } else if (v.getId() == R.id.page5Icon6) {
            subOption.setImageResource(R.drawable.icon5_6heading);
            current = 56;
    }

        subOption.setVisibility(View.VISIBLE);

    }

    public void nextPage() {
        if(isOnPage1) {
            isOnPage1 = false;
            isOnPage2 = true;
        }
        else if(isOnPage2) {
            isOnPage2 = false;
            isOnPage3 = true;
        }
        else if(isOnPage3) {
            isOnPage3 = false;
            isOnPage4 = true;
        }
        else if(isOnPage4) {
            isOnPage4 = false;
            isOnPage5 = true;
        }
        else if(isOnPage5) {
            isOnPage5 = false;
            isOnPage1 = true;
        }
    }

    public void prevPage() {
        if(isOnPage1) {
            isOnPage1 = false;
            isOnPage5 = true;
        }
        else if(isOnPage2) {
            isOnPage2 = false;
            isOnPage1 = true;
        }
        else if(isOnPage3) {
            isOnPage3 = false;
            isOnPage2 = true;
        }
        else if(isOnPage4) {
            isOnPage4 = false;
            isOnPage3 = true;
        }
        else if(isOnPage5) {
            isOnPage5 = false;
            isOnPage4 = true;
        }
    }

    public void makeVisible() {
        if(isOnPage1) {
            page1Icon1.setVisibility(View.VISIBLE);
            page1Icon2.setVisibility(View.VISIBLE);
            page1Icon3.setVisibility(View.VISIBLE);
            page1Icon4.setVisibility(View.VISIBLE);
            page1Icon5.setVisibility(View.VISIBLE);
            page1Icon6.setVisibility(View.VISIBLE);

            page5Icon1.setVisibility(View.INVISIBLE);
            page5Icon2.setVisibility(View.INVISIBLE);
            page5Icon3.setVisibility(View.INVISIBLE);
            page5Icon4.setVisibility(View.INVISIBLE);
            page5Icon5.setVisibility(View.INVISIBLE);
            page5Icon6.setVisibility(View.INVISIBLE);

            page2Icon1.setVisibility(View.INVISIBLE);
            page2Icon2.setVisibility(View.INVISIBLE);
            page2Icon3.setVisibility(View.INVISIBLE);
            page2Icon4.setVisibility(View.INVISIBLE);
            page2Icon5.setVisibility(View.INVISIBLE);
            page2Icon6.setVisibility(View.INVISIBLE);

            heading1.setVisibility(View.VISIBLE);
            heading5.setVisibility(View.INVISIBLE);
            heading2.setVisibility(View.INVISIBLE);

        }
        else if(isOnPage2) {
            page1Icon1.setVisibility(View.INVISIBLE);
            page1Icon2.setVisibility(View.INVISIBLE);
            page1Icon3.setVisibility(View.INVISIBLE);
            page1Icon4.setVisibility(View.INVISIBLE);
            page1Icon5.setVisibility(View.INVISIBLE);
            page1Icon6.setVisibility(View.INVISIBLE);

            page3Icon1.setVisibility(View.INVISIBLE);
            page3Icon2.setVisibility(View.INVISIBLE);
            page3Icon3.setVisibility(View.INVISIBLE);
            page3Icon4.setVisibility(View.INVISIBLE);
            page3Icon5.setVisibility(View.INVISIBLE);

            page2Icon1.setVisibility(View.VISIBLE);
            page2Icon2.setVisibility(View.VISIBLE);
            page2Icon3.setVisibility(View.VISIBLE);
            page2Icon4.setVisibility(View.VISIBLE);
            page2Icon5.setVisibility(View.VISIBLE);
            page2Icon6.setVisibility(View.VISIBLE);

            heading2.setVisibility(View.VISIBLE);
            heading3.setVisibility(View.INVISIBLE);
            heading1.setVisibility(View.INVISIBLE);

        }
        else if(isOnPage3) {
            page3Icon1.setVisibility(View.VISIBLE);
            page3Icon2.setVisibility(View.VISIBLE);
            page3Icon3.setVisibility(View.VISIBLE);
            page3Icon4.setVisibility(View.VISIBLE);
            page3Icon5.setVisibility(View.VISIBLE);


            page2Icon1.setVisibility(View.INVISIBLE);
            page2Icon2.setVisibility(View.INVISIBLE);
            page2Icon3.setVisibility(View.INVISIBLE);
            page2Icon4.setVisibility(View.INVISIBLE);
            page2Icon5.setVisibility(View.INVISIBLE);
            page2Icon6.setVisibility(View.INVISIBLE);

            page4Icon1.setVisibility(View.INVISIBLE);
            page4Icon2.setVisibility(View.INVISIBLE);
            page4Icon3.setVisibility(View.INVISIBLE);
            page4Icon4.setVisibility(View.INVISIBLE);

            heading3.setVisibility(View.VISIBLE);
            heading4.setVisibility(View.INVISIBLE);
            heading2.setVisibility(View.INVISIBLE);

        }
        else if(isOnPage4) {

            page5Icon1.setVisibility(View.INVISIBLE);
            page5Icon2.setVisibility(View.INVISIBLE);
            page5Icon3.setVisibility(View.INVISIBLE);
            page5Icon4.setVisibility(View.INVISIBLE);
            page5Icon5.setVisibility(View.INVISIBLE);
            page5Icon6.setVisibility(View.INVISIBLE);

            page3Icon1.setVisibility(View.INVISIBLE);
            page3Icon2.setVisibility(View.INVISIBLE);
            page3Icon3.setVisibility(View.INVISIBLE);
            page3Icon4.setVisibility(View.INVISIBLE);
            page3Icon5.setVisibility(View.INVISIBLE);

            page4Icon1.setVisibility(View.VISIBLE);
            page4Icon2.setVisibility(View.VISIBLE);
            page4Icon3.setVisibility(View.VISIBLE);
            page4Icon4.setVisibility(View.VISIBLE);


            heading4.setVisibility(View.VISIBLE);
            heading3.setVisibility(View.INVISIBLE);
            heading5.setVisibility(View.INVISIBLE);
        }
        else if(isOnPage5) {
            page1Icon1.setVisibility(View.INVISIBLE);
            page1Icon2.setVisibility(View.INVISIBLE);
            page1Icon3.setVisibility(View.INVISIBLE);
            page1Icon4.setVisibility(View.INVISIBLE);
            page1Icon5.setVisibility(View.INVISIBLE);
            page1Icon6.setVisibility(View.INVISIBLE);

            page4Icon1.setVisibility(View.INVISIBLE);
            page4Icon2.setVisibility(View.INVISIBLE);
            page4Icon3.setVisibility(View.INVISIBLE);
            page4Icon4.setVisibility(View.INVISIBLE);


            page5Icon1.setVisibility(View.VISIBLE);
            page5Icon2.setVisibility(View.VISIBLE);
            page5Icon3.setVisibility(View.VISIBLE);
            page5Icon4.setVisibility(View.VISIBLE);
            page5Icon5.setVisibility(View.VISIBLE);
            page5Icon6.setVisibility(View.VISIBLE);

            heading5.setVisibility(View.VISIBLE);
            heading1.setVisibility(View.INVISIBLE);
            heading4.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}