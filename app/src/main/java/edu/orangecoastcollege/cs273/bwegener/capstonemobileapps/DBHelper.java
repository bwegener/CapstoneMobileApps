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


public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static final String TAG = "DBHelper";

    static final String DATABASE_NAME = "ChordScaleLibrary";
    private static final int DATABASE_VERSION = 1;

    // Table of intervals (a <code>ChordScale</code> with only two chord members)
    private static final String INTERVALS_TABLE = "Intervals";
    private static final String INTERVALS_KEY_FIELD_ID = "_id";
    private static final String FIELD_INTERVAL_NAME = "interval_name";
    private static final String FIELD_INTERVAL_RATIO = "interval_ratio";
    private static final String FIELD_INTERVAL_CENTS = "interval_cents";
    private static final String FIELD_INTERVAL_DESCRIPTION = "interval_description";

    // Table of scales
    private static final String SCALES_TABLE = "Scales";
    private static final String SCALES_KEY_FIELD_ID = "_id";
    private static final String FIELD_SCALE_NAME = "scale_name";
    private static final String FIELD_SCALE_SIZE = "scale_size";
    private static final String FIELD_SCALE_DESCRIPTION = "scale_description";
    private static final String FIELD_SCALE_SCL_FILE_NAME = "scl_file_name";

    // Table of chords
    private static final String CHORDS_TABLE = "Chords";
    private static final String CHORDS_KEY_FIELD_ID = "_id";
    private static final String FIELD_CHORD_NAME = "chord_name";
    private static final String FIELD_CHORD_SIZE = "chord_size";
    // TODO: figure out how to add chord members
    private static final String FIELD_CHORD_DESCRIPTION = "chord_description";

    // Table of users
    private static final String USERS_TABLE = "Users";
    private static final String USERS_KEY_FIELD_ID = "+_id";
    private static final String FIELD_USER_NAME = "user_name";
    private static final String FIELD_USER_FIRST_NAME = "user_first_name";
    private static final String FIELD_USER_LAST_NAME = "user_last_name";
    private static final String FIELD_USER_EMAIL = "user_email";
    private static final String FIELD_USER_PASSWORD = "user_password";
    private static final String FIELD_USER_LATITUDE = "user_latitude";
    private static final String FIELD_USER_LONGITUDE = "user_longitude";


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
                + FIELD_INTERVAL_CENTS + " REAL, "
                + FIELD_INTERVAL_DESCRIPTION + ")";
        sqLiteDatabase.execSQL(createQuery);

        // Create Scales table
        createQuery = "CREATE TABLE " + SCALES_TABLE + "("
                + SCALES_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_SCALE_NAME + " TEXT, "
                + FIELD_SCALE_SIZE + " INTEGER, "
                + FIELD_SCALE_DESCRIPTION + " TEXT, "
                + FIELD_SCALE_SCL_FILE_NAME + " TEXT" + ")";
        sqLiteDatabase.execSQL(createQuery);

        // Create Chords table
        createQuery = "CREATE TABLE " + CHORDS_TABLE + "("
                + CHORDS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_CHORD_NAME + " TEXT, "
                + FIELD_CHORD_SIZE + " INTEGER" + ")";
        sqLiteDatabase.execSQL(createQuery);

        // Create Users table
        createQuery = "CREATE TABLE " + USERS_TABLE + "("
                + USERS_KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_USER_NAME + " TEXT, "
                + FIELD_USER_FIRST_NAME + " TEXT, "
                + FIELD_USER_LAST_NAME + " TEXT, "
                + FIELD_USER_EMAIL + " TEXT, "
                + FIELD_USER_PASSWORD + " TEXT, "
                + FIELD_USER_LATITUDE + " REAL, "
                + FIELD_USER_LONGITUDE + " REAL" + ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + INTERVALS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SCALES_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CHORDS_TABLE);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);

        onCreate(sqLiteDatabase);
    }

    // TODO: FINISH ADDING USER TABLE INFO

    public void addInterval(ChordScale interval) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_INTERVAL_NAME, interval.getName());
        values.put(FIELD_INTERVAL_RATIO, interval.getIntervalRatio(0, 1));
        values.put(FIELD_INTERVAL_CENTS, interval.getIntervalDistanceInCents(0,1));
        values.put(FIELD_INTERVAL_DESCRIPTION, interval.getDescription());

        db.insert(INTERVALS_TABLE, null, values);
        db.close();
    }

    public void addScale(ChordScale scale) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_SCALE_NAME, scale.getName());
        values.put(FIELD_SCALE_SIZE, scale.getSize());
        values.put(FIELD_SCALE_DESCRIPTION, scale.getDescription());
        // values.put(FIELD_SCALE_SCL_FILE_NAME, scale.getSCLfileName());

        db.insert(SCALES_TABLE, null, values);
        db.close();
    }

    public void addChord(ChordScale chord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_CHORD_NAME, chord.getName());
        values.put(FIELD_CHORD_SIZE, chord.getSize());
        // TODO: figure out how to add chord members
        values.put(FIELD_CHORD_DESCRIPTION, chord.getDescription());

        db.insert(CHORDS_TABLE, null, values);
        db.close();
    }

    public List<ChordScale> getAllIntervals() {
        List<ChordScale> allIntervalsList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                INTERVALS_TABLE,
                new String[]{
                        INTERVALS_KEY_FIELD_ID,
                        FIELD_INTERVAL_NAME,
                        FIELD_INTERVAL_RATIO,
                        FIELD_INTERVAL_CENTS,
                        FIELD_INTERVAL_DESCRIPTION
                },
                null,
                null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                // build interval
                ChordScale interval =
                        new ChordScale(cursor.getInt(0), cursor.getString(1), 2);
                interval.addChordMember(new Note("Fundamental"));
                interval.addChordMember(new Note(cursor.getString(1),
                        interval.getChordMemberAtPos(0).getPitchFrequency()
                                * IntervalHandler.convertRatioToDecimal(cursor.getString(2)),
                        cursor.getString(2)));
                interval.setDescription(cursor.getString(4));

                // add to list
                allIntervalsList.add(interval);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return allIntervalsList;
    }


    // TODO: Check this out
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
                //Log.e(TAG, "Line Num->" + lineNum++ + ", " + line + "\n");

                //int id = Integer.parseInt(fields[0].trim()); // TODO: fix this
                String name = fields[1].trim();
                String ratio = fields[2].trim();
                double cents = Double.parseDouble(fields[3].trim());


                ChordScale interval = new ChordScale(name);
                interval.addChordMember(new Note("Fundamental"));
                interval.addChordMember(new Note(name,
                        interval.getChordMemberAtPos(0).getPitchFrequency()
                                * IntervalHandler.convertRatioToDecimal(ratio), ratio));
                interval.setDescription("Size in cents: " + cents);

                // add to DB
                addInterval(interval);

            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
    public List<ChordScale> importScalesFromSCL() {
        // create list with at least 4k initial capacity
        List<ChordScale> allScalesList = new ArrayList<>(5000);

        // create local object
        ChordScale scale;

        // member variables
        String name;
        int size;
        String description;
        String sclFileName;

        AssetManager manager = mContext.getAssets();

        try {
            String fileList[] = manager.list("scl");

            // loop through each .scl file in the scl directory
            for (String file : fileList) {
                // file is one .scl file
                InputStream inputStream = manager.open("scl/" + file);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                // Get basic information parameters
                // Line 1
                name = br.readLine().replace("! ", "");
                sclFileName = name;
                Log.e(TAG, "Name->" + name + "\n");
                // Format name
                name.replace("_", " ");
                name.replace(".scl", "");
                // TODO: format capitalization

                // Line 2
                br.readLine(); // SKIP (scala comment)

                // Line 3
                description = br.readLine();
                Log.e(TAG, "Description->" + description + "\n");

                // Line 4
                size = Integer.parseInt(br.readLine().replace(" ", ""));
                Log.e(TAG, "Size->" + size + "\n");

                // Line 5
                br.readLine(); // SKIP (scala comment)

                // Build scale
                scale = new ChordScale(name, size, description, sclFileName);
                scale.addChordMember(new Note("Tonic"));

                String intervalLine; // chord member interval
                double interval; // decimal used for multiplication
                while ((intervalLine = br.readLine()) != null) {
                    // parse intervals, add to scale
                    if (intervalLine.contains(".")) { // interval is in CENTS
                        interval =
                                IntervalHandler.convertCentsToDecimal(Double.parseDouble(intervalLine));
                        scale.addChordMember(new Note(
                                scale.getChordMemberAtPos(0).getPitchFrequency() * interval));
                    }
                    else if (intervalLine.contains("/")) { // interval is a RATIO
                        interval = IntervalHandler.convertRatioToDecimal(intervalLine);
                        scale.addChordMember(new Note(
                                scale.getChordMemberAtPos(0).getPitchFrequency() * interval));
                    }
                }
                allScalesList.add(scale);
                addScale(scale);
                br.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return allScalesList;
    }
    */
}