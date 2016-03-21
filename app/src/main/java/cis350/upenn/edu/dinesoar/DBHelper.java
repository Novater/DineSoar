package cis350.upenn.edu.dinesoar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dan on 3/20/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    final static int DB_VERSION = 1;
    private static final String PATH = "don't have database path yet";
    Context context;
    public static final String sharedPrefName = "MySharedPref";

    public DBHelper(Context context) {
        super(context, PATH, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase databse, int oldVersion, int newVersion) {

    }

    public boolean attemptOpen () {
        return true;
    }

    public String sharedPrefName() {
        return sharedPrefName;
    }

    public boolean verifyAccount(String username, String password) {
        //STUB FUNCTION FOR TESTING, NOT IMPLEMENTED
        return true;
    }
}
