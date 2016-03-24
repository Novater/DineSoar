package cis350.upenn.edu.dinesoar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ProfileHomePageActivity extends AppCompatActivity {
    public static final int FindRestaurantID = 0;
    public static final int LogOutID = 1;
    public static final int NavigationID = 2;
    private Toolbar toolbar;

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_home_page);
        initToolbar();


        /*
        String fontPath = "fonts/Helvetica_Light-Normal.ttf";
        Toolbar txtGhost = (Toolbar) findViewById(R.id.my_toolbar);
        txtGhost.setTitle("Toolbar Stub");
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        //txtGhost.setTypeface(tf); */

        String username = getIntent().getStringExtra("username");
        SharedPreferences logInInfo = getSharedPreferences(MainActivity.mydb.sharedPrefName(), 0);
        if (logInInfo.getString("username", null) == null) {
            SharedPreferences.Editor edit = logInInfo.edit();
            edit.putString("username", username);
            edit.commit();
        }
    }

    public void findRestaurant(View v) {
        Intent i = new Intent(this, FindRestaurantActivity.class);
        startActivityForResult(i, FindRestaurantID);
    }

    public void onLogOut(View v) {
        SharedPreferences logInInfo = getSharedPreferences(MainActivity.mydb.sharedPrefName(), 0);
        SharedPreferences.Editor edit = logInInfo.edit();
        edit.remove("username");
        edit.commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivityForResult(i, LogOutID);
        finish();
    }
}
