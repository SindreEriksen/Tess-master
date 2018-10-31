package com.hfad.tess;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView aktivtetsliste = findViewById(R.id.aktivtetsliste);


        //Database
        SQLiteOpenHelper dbHelper = new DBHelper(this);
        //Prøver å opprette database og hente ut data. Sender feilmelding hvis det ikke går

        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query("aktivitet", new String[] {"_id", "navn", "beskrivelse"},null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"navn"}, new int[]{android.R.id.text1},0);
            aktivtetsliste.setAdapter(listAdapter);
        } catch(SQLiteException e) {
            Toast dbToast = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT);
            dbToast.show();
        }
    } // end onCreate()

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item ) {
        switch (item.getItemId()) {
            case R.id.action_se_kart:
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}

