package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class Main12_1ActivityDatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "trabalho_audio";
    private static int VERSAO = 6;

    public Main12_1ActivityDatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE trabalho_audio (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "title TEXT, " +
                "audio BLOB, " +
                "timeOfRecording TEXT, " +
                "latitude TEXT, " +
                "longitude TEXT);");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS trabalho_audio");

        db.execSQL("CREATE TABLE trabalho_audio (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "title TEXT, " +
                "audio BLOB, " +
                "timeOfRecording TEXT, " +
                "latitude TEXT, " +
                "longitude TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS trabalho_audio");

        db.execSQL("CREATE TABLE trabalho_audio (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "title TEXT, " +
                "audio BLOB, " +
                "timeOfRecording TEXT, " +
                "latitude TEXT, " +
                "longitude TEXT);");
    }
}
