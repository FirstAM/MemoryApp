package com.example.memoryapp;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edExercise, edFirst, edSecond;
    Button btnAdd, btnRead, btnDelete;
    TextView ViewEx, View1, View2;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edExercise = (EditText) findViewById(R.id.Exercise);
        edFirst = (EditText) findViewById(R.id.First);
        edSecond = (EditText) findViewById(R.id.Second);

        btnAdd = (Button) findViewById(R.id.Add);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.Read);
        btnRead.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.Delete);
        btnDelete.setOnClickListener(this);

        ViewEx = (TextView) findViewById(R.id.ViewEx);
        View1 = (TextView) findViewById(R.id.View1);
        View2 = (TextView) findViewById(R.id.View2);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View view) {

        String exercise = edExercise.getText().toString();
        String bunchF = edFirst.getText().toString();
        String bunchS = edSecond.getText().toString();
        int bunchInt1 = Integer.parseInt(bunchF);
        int bunchInt2 = Integer.parseInt(bunchS);

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();




        switch (view.getId()) {
            case R.id.Add:
                contentValues.put (DBHelper.KEY_Exercise, exercise);
                contentValues.put (DBHelper.KEY_First, bunchInt1);
                contentValues.put (DBHelper.KEY_Second, bunchInt2);

                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;


            case R.id.Read:
                 Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                 if (cursor.moveToFirst()) {
                     int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                     int exerciseIndex = cursor.getColumnIndex(DBHelper.KEY_Exercise);
                     int bunch1Index = cursor.getColumnIndex(DBHelper.KEY_First);
                     int bunch2Index = cursor.getColumnIndex(DBHelper.KEY_Second);
                     do {
                         Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                 ", exercise = " + cursor.getString(exerciseIndex) +
                                 ", first = " + cursor.getInt(bunch1Index) +
                                 ", second = " + cursor.getInt(bunch2Index));

                         ViewEx.setText(cursor.getString(exerciseIndex));
                         View1.setText("Первый подход " + cursor.getInt(bunch1Index) + " раз");
                         View2.setText("Второй подход " + cursor.getInt(bunch2Index) + " раз");

                     } while (cursor.moveToNext());
                 } else
                     Log.d("mLog", "0 rows");

                 cursor.close();
                break;

            case R.id.Delete:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;
        }

        dbHelper.close();
    }

}