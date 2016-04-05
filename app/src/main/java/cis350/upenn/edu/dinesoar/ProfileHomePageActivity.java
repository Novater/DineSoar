package cis350.upenn.edu.dinesoar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileHomePageActivity extends AppCompatActivity {
    public static final int FindRestaurantID = 0;
    public static final int LogOutID = 1;
    public static final int NavigationID = 2;
    private Toolbar toolbar;
    private String activityTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter<String> mAdapter;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;

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

    private void addDrawerItems() {
        String[] testArray = { "Log Out", "test2", "test3"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    onLogOut(view);
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_closed) {
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(activityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_home_page);
        initToolbar();

        activityTitle = getTitle().toString();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerList = (ListView) findViewById(R.id.nav_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        addDrawerItems();
        setupDrawer();

        String fontPath = "fonts/PFAgoraSansPro-Reg.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        TextView findMenuText = (TextView) findViewById(R.id.menu_text);
        findMenuText.setTypeface(tf);

        switchFragmentDining(findViewById(android.R.id.content));

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void switchFragmentDining(View view) {

        Fragment myFragment = (Fragment)getFragmentManager().findFragmentByTag("TRANSACTION_FRAGMENT");
        if (myFragment != null && myFragment.isVisible()) {
            Button currentFragButt = (Button) findViewById(R.id.history_fragment_button);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                // If we're running on Honeycomb or newer, then we can use the Theme's
                // selectableItemBackground to ensure that the View has a pressed state
                TypedValue outValue = new TypedValue();
                getApplicationContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                currentFragButt.setBackgroundResource(outValue.resourceId);
            }

        }

        Fragment myFragment2 = (Fragment)getFragmentManager().findFragmentByTag("PROFILE_FRAGMENT");
        if (myFragment2 != null && myFragment2.isVisible()) {
            Button currentFragButt = (Button) findViewById(R.id.profile_button);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                // If we're running on Honeycomb or newer, then we can use the Theme's
                // selectableItemBackground to ensure that the View has a pressed state
                TypedValue outValue = new TypedValue();
                getApplicationContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                currentFragButt.setBackgroundResource(outValue.resourceId);
            }
        }
        Fragment myFragment3 = (Fragment)getFragmentManager().findFragmentByTag("DINING_FRAGMENT");
        if (myFragment3 == null) {
            Button clickedButton = (Button) findViewById(R.id.dining_group_button);
            clickedButton.setBackgroundResource(R.drawable.tab_button);

            Fragment diningFragment = new FragmentDiningGroup();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, diningFragment, "DINING_FRAGMENT");
            fragmentTransaction.commit();
        }
    }

    public void switchFragmentTransaction(View view) {

        Fragment myFragment = (Fragment)getFragmentManager().findFragmentByTag("DINING_FRAGMENT");
        if (myFragment != null && myFragment.isVisible()) {
            Button currentFragButt = (Button) findViewById(R.id.dining_group_button);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                // If we're running on Honeycomb or newer, then we can use the Theme's
                // selectableItemBackground to ensure that the View has a pressed state
                TypedValue outValue = new TypedValue();
                getApplicationContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                currentFragButt.setBackgroundResource(outValue.resourceId);
            }
        }

        Fragment myFragment2 = (Fragment)getFragmentManager().findFragmentByTag("PROFILE_FRAGMENT");
        if (myFragment2 != null && myFragment2.isVisible()) {
            Button currentFragButt = (Button) findViewById(R.id.profile_button);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                // If we're running on Honeycomb or newer, then we can use the Theme's
                // selectableItemBackground to ensure that the View has a pressed state
                TypedValue outValue = new TypedValue();
                getApplicationContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                currentFragButt.setBackgroundResource(outValue.resourceId);
            }
        }

        Fragment myFragment3 = (Fragment)getFragmentManager().findFragmentByTag("TRANSACTION_FRAGMENT");
        if (myFragment3 == null) {
            Button clickedButton = (Button) findViewById(R.id.history_fragment_button);
            clickedButton.setBackgroundResource(R.drawable.tab_button);
            Fragment transactionFragment = new FragmentTransactionHistory();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, transactionFragment, "TRANSACTION_FRAGMENT");
            fragmentTransaction.commit();
        }
    }

    public void switchFragmentProfile(View view) {

        Fragment myFragment = (Fragment)getFragmentManager().findFragmentByTag("DINING_FRAGMENT");
        if (myFragment != null && myFragment.isVisible()) {
            Button currentFragButt = (Button) findViewById(R.id.dining_group_button);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                // If we're running on Honeycomb or newer, then we can use the Theme's
                // selectableItemBackground to ensure that the View has a pressed state
                TypedValue outValue = new TypedValue();
                getApplicationContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                currentFragButt.setBackgroundResource(outValue.resourceId);
            }
        }

        Fragment myFragment2 = (Fragment)getFragmentManager().findFragmentByTag("TRANSACTION_FRAGMENT");
        if (myFragment2 != null && myFragment2.isVisible()) {
            Button currentFragButt = (Button) findViewById(R.id.history_fragment_button);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                // If we're running on Honeycomb or newer, then we can use the Theme's
                // selectableItemBackground to ensure that the View has a pressed state
                TypedValue outValue = new TypedValue();
                getApplicationContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                currentFragButt.setBackgroundResource(outValue.resourceId);
            }
        }
        Fragment myFragment3 = (Fragment)getFragmentManager().findFragmentByTag("PROFILE_FRAGMENT");
        if (myFragment3 == null) {
            Button clickedButton = (Button) findViewById(R.id.profile_button);
            clickedButton.setBackgroundResource(R.drawable.tab_button);

            Fragment profileFragment = new FragmentProfile();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, profileFragment, "PROFILE_FRAGMENT");
            fragmentTransaction.commit();
        }
    }
}
