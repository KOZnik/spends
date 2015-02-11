package pl.koznik.spends.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SpendsSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Spends.db";

    public SpendsSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO remove
        System.out.println("onCreate called");
        db.execSQL(SpendsTableConstants.TABLE_CATEGORY_CREATE);
        db.execSQL(SpendsTableConstants.TABLE_CATEGORY_POSITION_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO check when upgrade occurs
        System.out.println("Upgrade occurs!");
    }

    public static class SpendsTableConstants implements BaseColumns {

        //Tables
        public static final String TABLE_CATEGORY = "category";
        public static final String TABLE_CATEGORY_POSITION = "category_position";

        //Columns
        public static final String CATEGORY_COLUMN_NAME = "name";
        public static final String CATEGORY_COLUMN_CREATE_DATE = "create_date";
        public static final String CATEGORY_POSITION_COLUMN_NAME = "name";
        public static final String CATEGORY_POSITION_COLUMN_ID = "id";
        public static final String CATEGORY_POSITION_COLUMN_CREATE_DATE = "create_date";
        public static final String CATEGORY_POSITION_COLUMN_CATEGORY_ID = "category_id";

        //Creates
        public static final String TABLE_CATEGORY_CREATE = "CREATE TABLE " + TABLE_CATEGORY + " (" + CATEGORY_COLUMN_NAME
                + " TEXT PRIMARY KEY, " + CATEGORY_COLUMN_CREATE_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP);";

        public static final String TABLE_CATEGORY_POSITION_CREATE = "CREATE TABLE " + TABLE_CATEGORY_POSITION + " ("
                + CATEGORY_POSITION_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CATEGORY_POSITION_COLUMN_NAME + " TEXT, "
                + CATEGORY_POSITION_COLUMN_CREATE_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + CATEGORY_POSITION_COLUMN_CATEGORY_ID + " TEXT,"
                + "UNIQUE (" + CATEGORY_POSITION_COLUMN_NAME + ", " + CATEGORY_POSITION_COLUMN_CATEGORY_ID + "), "
                + " FOREIGN KEY (" + CATEGORY_POSITION_COLUMN_CATEGORY_ID + ") REFERENCES " + TABLE_CATEGORY + " (" + CATEGORY_COLUMN_NAME + "));";

    }

}
