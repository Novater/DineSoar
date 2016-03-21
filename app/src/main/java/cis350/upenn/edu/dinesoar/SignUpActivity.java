package cis350.upenn.edu.dinesoar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.database.sqlite.*;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    public void onSignUp(View V) {
        //CURRENTLY IMPLEMENTING
        /*
        EditText edit_username = findViewById(R.id.username);
        String username = edit_username.toString();
        EditText edit_password1 = findViewById(R.id.password1);
        String password1 = edit_password1.toString();
        EditText edit_password2 = findViewById(R.id.password2);
        String password2 = edit_password2.toString();

        Intent i = new Intent(this, FailedSignUp.class);
        if (containsUser(username)) {

        } if (!(password1.length() > 6 && password1.equals(password2)) {

        } */
    }

}