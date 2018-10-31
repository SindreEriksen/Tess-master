package com.hfad.tess;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_name = "testDB3";
    private static final int DB_version = 1;

    private String createAktivitetTypeQuery = "CREATE TABLE aktivitet_type (_id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT);";
    private String createPrisnivåQuery = "CREATE TABLE prisnivå (_id INTEGER PRIMARY KEY AUTOINCREMENT, prisnivå TEXT);";

    private String createAktivitetQuery = "CREATE TABLE aktivitet (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                            + "navn TEXT,"
                                            + "beskrivelse TEXT,"
                                            + "type INTEGER,"
                                            + "hjemmeside TEXT,"
                                            + "prisnivå INTEGER,"
                                            + "utendørs NUMERIC,"
                                            + "latitude REAL,"
                                            + "longitude REAL,"
                                            + "FOREIGN KEY (type) REFERENCES aktivitet_type(_id),"
                                            + "FOREIGN KEY (prisnivå) REFERENCES prisnivå(_id)"
                                            + ");";

    public DBHelper(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, DB_version);
    }

    private static void insertAktivitetType(SQLiteDatabase db, String type) {
        ContentValues aktivitetValues = new ContentValues();
        aktivitetValues.put("type", type);
        db.insert("aktivitet_type", null, aktivitetValues);
    }

    private static void insertPrisnivå(SQLiteDatabase db, String prisnivå) {
        ContentValues prisnivåValues = new ContentValues();
        prisnivåValues.put("prisnivå", prisnivå);
        db.insert("prisnivå", null, prisnivåValues);
    }

    private static void insertAktivitet(SQLiteDatabase db, String navn, String beskrivelse, int type, String hjemmeside, int prisnivå, boolean utendørs, double latitude, double longitude) {
        ContentValues aktivitetValues = new ContentValues();
        aktivitetValues.put("navn", navn);
        aktivitetValues.put("beskrivelse", beskrivelse);
        aktivitetValues.put("type", type);
        aktivitetValues.put("hjemmeside", hjemmeside);
        aktivitetValues.put("prisnivå", prisnivå);
        aktivitetValues.put("utendørs", utendørs);
        aktivitetValues.put("latitude", latitude);
        aktivitetValues.put("longitude", longitude);
        db.insert("aktivitet", null, aktivitetValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL(createAktivitetTypeQuery);
            db.execSQL(createPrisnivåQuery);
            db.execSQL(createAktivitetQuery);

            insertAktivitetType(db, "Fornøyelsespark");
            insertAktivitetType(db, "Badeland");

            insertPrisnivå(db, "Lav");
            insertPrisnivå(db, "Medium");
            insertPrisnivå(db, "Høy");

            insertAktivitet(db, "Bø Sommarland", "Badeland i Bø, Telemark. Masse vann og moro", 2, "www.sommarland.no", 2, true, 59.4472, 9.0727);
            insertAktivitet(db, "Tusenfryd", "Fornøyelsespark i Ås, Akershus. Karuseller og berg-og-dalbaner pluss mye mer", 1, "www.tusenfryd.no", 3, true, 59.7490, 10.7783);
            insertAktivitet(db, "Kongeparken", "Fornøyelsespark i Stavanger", 1, "https://www.kongeparken.no", 2, true,  58.7775, 5.8414);
            insertAktivitet(db, "Hunderfossen", "Troll og moro for små og store barn", 1, "www.hunderfossen.no", 3, true, 61.2197, 10.4390);
            insertAktivitet(db, "Liseberg", "Fornøyelsespark i Gøteborg", 1, "https://www.liseberg.se", 3, true,  57.6969, 11.9903);
        }
        if (oldVersion < 2) {
        }
        if (oldVersion < 3) {
        }
        if (oldVersion < 4) {
        }
    }

}
