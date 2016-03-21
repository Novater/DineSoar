package cis350.upenn.edu.dinesoar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ProfileHomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_home_page);
        String username = getIntent().getStringExtra("username");
        SharedPreferences logInInfo = getSharedPreferences(MainActivity.mydb.sharedPrefName(), 0);
        if (logInInfo.getString("username", null) == null) {
            SharedPreferences.Editor edit = logInInfo.edit();
            edit.putString("username", username);
            edit.commit();
        }
    }

    public void onLogOut(View v) {
        SharedPreferences logInInfo = getSharedPreferences(MainActivity.mydb.sharedPrefName(), 0);
        SharedPreferences.Editor edit = logInInfo.edit();
        edit.remove("username");
        edit.commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivityForResult(i, 0);
        finish();
    }
}
