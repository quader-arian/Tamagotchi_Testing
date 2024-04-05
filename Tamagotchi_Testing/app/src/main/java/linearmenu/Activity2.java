package linearmenu;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.waveletmenu.R;

public class Activity2 extends AppCompatActivity {
    final static int THRESHOLD = 50000;

    // Six icons are placed evenly around the outer menu
    // All the others are placed in the inner menu
    ImageView icon1, icon2, icon3, icon4, icon5,
            icon1_1, icon1_2, icon1_3, icon1_4, icon1_5, icon1_6,
            icon2_1, icon2_2, icon2_3, icon2_4, icon2_5, icon2_6,
            icon3_1, icon3_2, icon3_3, icon3_4, icon3_5,
            icon4_1, icon4_2, icon4_3, icon4_4,
            icon5_1, icon5_2, icon5_3, icon5_4, icon5_5, icon5_6;

    // The central button of the wavelet menu
    ImageButton mainItem1, mainItem2, mainItem3, mainItem4, mainItem5,
                subItem1, subItem2, subItem3, subItem4, subItem5, subItem6;

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
    boolean icon1Selected, icon2Selected, icon3Selected, icon4Selected, icon5Selected,
            icon1_1Selected, icon1_2Selected, icon1_3Selected, icon1_4Selected, icon1_5Selected, icon1_6Selected,
            icon2_1Selected, icon2_2Selected, icon2_3Selected, icon2_4Selected, icon2_5Selected, icon2_6Selected,
            icon3_1Selected, icon3_2Selected, icon3_3Selected, icon3_4Selected, icon3_5Selected,
            icon4_1Selected, icon4_2Selected, icon4_3Selected, icon4_4Selected,
            icon5_1Selected, icon5_2Selected, icon5_3Selected, icon5_4Selected, icon5_5Selected, icon5_6Selected;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);
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
    }
    protected void showSubMenu2(){
        subItem1.setForeground(getDrawable(R.drawable.icon2_1));
        subItem2.setForeground(getDrawable(R.drawable.icon2_2));
        subItem3.setForeground(getDrawable(R.drawable.icon2_3));
        subItem4.setForeground(getDrawable(R.drawable.icon2_4));
        subItem5.setForeground(getDrawable(R.drawable.icon2_5));
        subItem6.setForeground(getDrawable(R.drawable.icon2_6));
    }
    protected void showSubMenu3(){
        subItem1.setForeground(getDrawable(R.drawable.icon3_1));
        subItem2.setForeground(getDrawable(R.drawable.icon3_2));
        subItem3.setForeground(getDrawable(R.drawable.icon3_3));
        subItem4.setForeground(getDrawable(R.drawable.icon3_4));
        subItem5.setForeground(getDrawable(R.drawable.icon3_5));
        subItem6.setForeground(null);
    }
    protected void showSubMenu4(){
        subItem1.setForeground(getDrawable(R.drawable.icon4_1));
        subItem2.setForeground(getDrawable(R.drawable.icon4_2));
        subItem3.setForeground(getDrawable(R.drawable.icon4_3));
        subItem4.setForeground(getDrawable(R.drawable.icon4_4));
        subItem5.setForeground(null);
        subItem6.setForeground(null);
    }
    protected void showSubMenu5(){
        subItem1.setForeground(getDrawable(R.drawable.icon5_1));
        subItem2.setForeground(getDrawable(R.drawable.icon5_2));
        subItem3.setForeground(getDrawable(R.drawable.icon5_3));
        subItem4.setForeground(getDrawable(R.drawable.icon5_4));
        subItem5.setForeground(getDrawable(R.drawable.icon5_5));
        subItem6.setForeground(getDrawable(R.drawable.icon5_6));
    }

    protected void showSubMenu(){

    }
}
