package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Brian Wegener on 11/16/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    static final String DATABASE_NAME = "ChordScaleLibrary";
    private static final int DATABASE_VERSION = 1;

    // Table of intervals (a <code>ChordScale</code> with only two chord members)
    private static final String INTERVALS_TABLE = "Intervals";
    private static final String INTERVALS_KEY_FIELD_ID = "_id";
    private static final String FIELD_INTERVAL_NAME = "interval_name";
    private static final String FIELD_INTERVAL_RATIO = "interval_ratio";
    private static final String FIELD_INTERVAL_CENTS = "interval_cents";

    //

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create Intervals table
        String createQuery = "CREATE TABLE " + INTERVALS_TABLE + "("
                + INTERVALS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_INTERVAL_NAME + " TEXT, "
                + FIELD_INTERVAL_RATIO + " TEXT, "
                + FIELD_INTERVAL_CENTS + " REAL" + ")";
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + INTERVALS_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addInterval(ChordScale chordScale) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_INTERVAL_NAME, chordScale.getName());
        values.put(FIELD_INTERVAL_RATIO, chordScale.getIntervalRatio(0, 1));
        values.put(FIELD_INTERVAL_CENTS, chordScale.getIntervalDistanceInCents(0,1));

        db.insert(INTERVALS_TABLE, null, values);
        db.close();
    }

    public List<ChordScale> getAllIntervals() {
        List<ChordScale> chordScaleList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                INTERVALS_TABLE,
                new String[]{
                        INTERVALS_KEY_FIELD_ID,
                        FIELD_INTERVAL_NAME,
                        FIELD_INTERVAL_RATIO,
                        FIELD_INTERVAL_CENTS
                },
                null,
                null,
                null, null, null, null);

        /*
        if (cursor.moveToFirst()) {
            do {
                ChordScale chordScale =
                        new ChordScale(cursor.getString(1), cursor.getInt(0));
                chordScale.addChordMember(new Note("Fundamental"));
                chordScale.addChordMember(new Note(cursor.getString(1),
                        chordScale.getChordMemberAtPos(0).getPitchFrequency()
                                * IntervalHandler.parseRatio(cursor.getString(2)),
                        cursor.getString(2)));
                chordScale.setDescription("Size in cents " + cursor.getString(3));
                chordScaleList.add(chordScale);
            } while (cursor.moveToNext());
        }
        */
        cursor.close();
        sqLiteDatabase.close();
        return chordScaleList;
    }

    public boolean importIntervalsFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inputStream;
        try {
            inputStream = manager.open(csvFileName);
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line; int lineNum = 1;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length !=4) {
                    Log.d("Library", "Skipping Bad CSV Row" + Arrays.toString(fields));
                    continue;
                }
                /*
                Log.e("DBHelper", "Line Num->" + lineNum++ + ", " + line + "\n");
                //int id = Integer.parseInt(fields[0].trim()); // TODO: fix this
                String name = fields[1].trim();
                String ratio = fields[2].trim();
                double cents = Double.parseDouble(fields[3].trim());
                ChordScale interval = new ChordScale(name);
                interval.addChordMember(new Note("Fundamental"));
                interval.addChordMember(new Note(name,
                        interval.getChordMemberAtPos(0).getPitchFrequency()
                                * IntervalHandler.parseRatio(ratio), ratio));
                interval.setDescription("Size in cents: " + cents);
                addInterval(interval);
                */
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
