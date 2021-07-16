package sg.edu.rp.c346.id20007649.l09problemstatement;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "national_parade.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG= "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONG_TITLE = "song_title";
    private static final String COLUMN_SONG_YEAR = "song_year";
    private static final String COLUMN_SONG_SINGERS = "song_singers";
    private static final String COLUMN_SONG_STARS = "song_stars";





    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_SONG + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SONG_TITLE  + "TEXT" + COLUMN_SONG_SINGERS + "TEXT" +  COLUMN_SONG_YEAR  + "INTEGER" + COLUMN_SONG_STARS + " INTEGER ) ";
        db.execSQL(createNoteTableSql);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);

    }


    public long insertSong(String title, String singers, int year, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, title);
        values.put(COLUMN_SONG_SINGERS, singers);
        values.put(COLUMN_SONG_YEAR, year);
        values.put(COLUMN_SONG_STARS, stars);

        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;

    }


    public ArrayList<Song> getAllSongs() {

        ArrayList<Song> songs = new ArrayList<Song>();

        String selectQuery = "SELECT " + COLUMN_ID + "," + COLUMN_SONG_TITLE + "," + COLUMN_SONG_SINGERS +  "," + COLUMN_SONG_YEAR +  "," + COLUMN_SONG_STARS + " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(title, singers, year, stars );
                songs.add(song);
            }

            while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return songs;

    }

    public ArrayList<Song> getAllSong() {

        ArrayList<Song> songs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_SONG_TITLE, COLUMN_SONG_SINGERS, COLUMN_SONG_YEAR, COLUMN_SONG_STARS};
        String condition = COLUMN_SONG_STARS + " = 5 ";

        Cursor cursor = db.query(TABLE_SONG, columns, condition,  null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song newSong = new Song(title, singers, year, stars );
                songs.add(newSong);

            }

            while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return songs;
    }
}