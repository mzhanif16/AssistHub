package MyAssistHub.App.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "AssistHubApp";
    private static final int DATABASE_VERSION = 1;

    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db ) {
        db.execSQL(AppDB.BookingTable.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        dropAllTables(db);
        onCreate(db);
    }

    public void dropAllTables( SQLiteDatabase db ) {
        dropTableIfExists(db);
    }

    private void dropTableIfExists( SQLiteDatabase db ) {
        db.execSQL("DROP TABLE IF EXISTS " + AppDB.BookingTable.TABLE_NAME);
    }
}
