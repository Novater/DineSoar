package cis350.upenn.edu.dinesoar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    public static final int ProfileHomePageID = 1;
    public static final int FailedSignInID = 2;
    public static final int SignUpID = 3;

    public static DBHelper mydb;
    Animation buttonClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("HI");
        mydb = new DBHelper(this);
        checkLogIn();
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            //NOT IMPLEMENTED
            @Override
            public void onCancel() {

            }
            @Override
            public void onSuccess(LoginResult loginResult) {

            }
            @Override
            public void onError(FacebookException facebookException) {

            }
        });

        String fontPath = "fonts/Quicksand-Bold.otf";
        TextView txtGhost = (TextView) findViewById(R.id.Title);
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        txtGhost.setTypeface(tf);

        String fontPath2 = "fonts/Quicksand-Bold.otf";
        TextView txtGhost2 = (TextView) findViewById(R.id.description);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), fontPath2);
        txtGhost2.setTypeface(tf2);

        String fontPath3 = "fonts/Quicksand-Regular.otf";
        Button txtGhost3 = (Button) findViewById(R.id.loginButton);
        Typeface tf3 = Typeface.createFromAsset(getAssets(), fontPath3);
        txtGhost3.setTypeface(tf);

        TextView txtGhost4 = (TextView) findViewById(R.id.register);
        txtGhost4.setTypeface(tf3);

        TextView txtGhost5 = (TextView) findViewById(R.id.registerNow);
        txtGhost5.setTypeface(tf);

        TextView txtGhost6 = (TextView) findViewById(R.id.or);
        txtGhost6.setTypeface(tf3);

        txtGhost5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUp(v);
            }
        });

        //ADD ANIMATIONS TO LOGIN BUTTON AND SIGN UP BUTTON
        Button logIn = (Button) findViewById(R.id.loginButton);

        buttonClick = AnimationUtils.loadAnimation(this, R.anim.button_press);
    }

    public void onSignIn (View v) {

        //CURRENTLY IMPLEMENTING
        //EditText edit_username = findViewById(R.id.username);
        //String username = edit_text.toString();
        //EditText edit_password = findViewById(R.id.password);
        //String password = edit_text.toString();
        String username = "test username";
        String password = "test password";
        Intent i;
        if (mydb.verifyAccount(username, password)) {
            i = new Intent(this, ProfileHomePageActivity.class);
            i.putExtra("username", username);
            startActivityForResult(i, ProfileHomePageID);
            finish();
        } else {
            i = new Intent(this, FailedSignIn.class);
            startActivityForResult(i, FailedSignInID);
        }

    }

    public void onSignUp (View V) {

        Intent i = new Intent(this, SignUpActivity.class);
        startActivityForResult(i, SignUpID);
        finish();
    }

    public boolean checkLogIn() {
        SharedPreferences logInInfo = getSharedPreferences(mydb.sharedPrefName(), 0);
        String username = logInInfo.getString("username", null);
        if (username == null) {
            return false;
        }
        else {
            Intent i = new Intent(this, ProfileHomePageActivity.class);
            i.putExtra("username", username);
            startActivityForResult(i, ProfileHomePageID);
            finish();
        }
        return false;
    }

}
