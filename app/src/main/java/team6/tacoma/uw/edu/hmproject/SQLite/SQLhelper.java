package team6.tacoma.uw.edu.hmproject.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kamil on 5/30/2016.
 */
public class SQLhelper extends SQLiteOpenHelper{

    public static final String DatabaseName = "FindMyBook.db";
    public static final String TableName = "SearchHistoryTable";
    public static final String KeyWordColum ="KeyWord";

    /**
     * Constructor that creates databse
     * @param ctx
     */
    public SQLhelper (Context ctx){
        super(ctx,DatabaseName, null, 1);

    }

    /**
     * Creates database, and on creation, executes sql code
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table SearchHistoryTable " + "(KeyWord TEXT NOT NULL);");
    }

    /**
     * when database changes this method is called to update changes.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    /**
     * This method is called when data is put into database
     * @param key
     * @return - true/false depending on if successful
     */
    public boolean insertData(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KeyWordColum, key);
        db.insert(TableName, null, contentValues);

        return true;
    }
}
