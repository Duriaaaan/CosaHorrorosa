package com.example.cosahorrosa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Información de la base de datos
    private static final String DATABASE_NAME = "UserDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Información de la tabla
    private static final String TABLE_NAME = "users";
    private static final String COL_ID = "ID";
    private static final String COL_USERNAME = "USERNAME";
    private static final String COL_PASSWORD = "PASSWORD";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT UNIQUE, " +  // Agregué UNIQUE para evitar usuarios duplicados
                COL_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    // Manejo de actualización de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Método para insertar un usuario (CREATE)
    public boolean insertUser(String username, String password) {
        if (isUserExists(username)) {
            Log.e("DatabaseError", "Usuario ya existe");
            return false;
        }
        // Procede con la inserción si el usuario no existe
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_USERNAME, username);
            contentValues.put(COL_PASSWORD, password);
            long result = db.insert(TABLE_NAME, null, contentValues);
            return result != -1;
        } catch (Exception e) {
            Log.e("DatabaseError", "Error al insertar usuario", e);
            return false;
        } finally {
            db.close();
        }
    }

    public boolean isUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Método para verificar las credenciales de usuario (READ)
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }

    // Método para actualizar un usuario (UPDATE)
    public boolean updateUser(String oldUsername, String newUsername, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, newUsername);
        contentValues.put(COL_PASSWORD, newPassword);
        int result = db.update(TABLE_NAME, contentValues, COL_USERNAME + " = ?", new String[]{oldUsername});
        return result > 0; // Si el resultado es mayor a 0, la actualización fue exitosa
    }

    // Método para eliminar un usuario (DELETE)
    public boolean deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COL_USERNAME + " = ?", new String[]{username});
        return result > 0; // Si el resultado es mayor a 0, la eliminación fue exitosa
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_USERNAME + " FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                users.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }

    public void insertUserAsync(String username, String password, OnDatabaseOperationComplete listener) {
        new Thread(() -> {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_USERNAME, username);
            contentValues.put(COL_PASSWORD, password);
            long result = db.insert(TABLE_NAME, null, contentValues);

            if (result != -1) {
                listener.onSuccess();
            } else {
                listener.onFailure("Error al insertar usuario");
            }

            db.close();
        }).start();
    }
    public String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            Log.e("EncryptionError", "Error en cifrado de la contraseña", e);
            return null;
        }
    }

    public void checkUserAsync(String username, String password, OnDatabaseOperationComplete listener) {
        new Thread(() -> {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?";
            Cursor cursor = db.rawQuery(query, new String[]{username, password});

            boolean isValid = cursor.getCount() > 0;
            cursor.close();
            db.close();

            if (isValid) {
                listener.onSuccess();
            } else {
                listener.onFailure("Usuario o contraseña incorrectos");
            }
        }).start();
    }
}
