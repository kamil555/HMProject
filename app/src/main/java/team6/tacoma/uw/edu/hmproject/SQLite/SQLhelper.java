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

    public SQLhelper (Context ctx){
        super(ctx,DatabaseName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table SearchHistoryTable " + "(KeyWord TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }
    public boolean insertData(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KeyWordColum, key);
        db.insert(TableName, null, contentValues);

        return true;
    }
}
