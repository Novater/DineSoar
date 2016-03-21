package cis350.upenn.edu.dinesoar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final int ProfileHomePageID = 1;
    public static final int FailedSignInID = 2;
    public static final int SignUpID = 3;

    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

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

        txtGhost5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignUp(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }

    public void onSignIn (View V) {
        //EditText edit_username = findViewById(R.id.username);
        //String username = edit_text.toString();
        //EditText edit_password = findViewById(R.id.password);
        //String password = edit_text.toString();
        String username = "test username";
        String password = "test password";
        Intent i;
        if (verifyAccount(username, password)) {
            i = new Intent(this, ProfileHomePageActivity.class);
            i.putExtra("username", username);
            startActivityForResult(i, ProfileHomePageID);
        } else {
            i = new Intent(this, FailedSignIn.class);
            startActivityForResult(i, FailedSignInID);
        }
    }

    public void onSignUp (View V) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivityForResult(i, SignUpID);
    }

}
