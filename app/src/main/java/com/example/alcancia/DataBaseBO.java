package com.example.alcancia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.util.Const;
import com.example.util.Util;

import java.io.File;

public class DataBaseBO {
    public static boolean existDataBase() {
        File dbFile = new File(Util.dirApp(), Const.NAMEDATABASE);

        return dbFile.exists();
    }

    public static boolean createDataBase() {
        SQLiteDatabase db = null;
        try {
            File dbFile = new File(Util.dirApp(), Const.NAMEDATABASE);
            db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
            String tableCoin = "CREATE TABLE IF NOT EXISTS Coin(id varchar(50), denominacion varchar(50), value FLOAT)";
            db.execSQL(tableCoin);

            return true;
        } catch (Exception e) {

            return false;
        } finally {
            if (db != null)
                db.close();
        }
    }

    public static boolean saveInsertCoin(String coinselected) {
        long rows = 0;
        SQLiteDatabase db = null;
        try {
            File dbFile = new File(Util.dirApp(), Const.NAMEDATABASE);
            if (existDataBase()) {
                db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
                ContentValues values = new ContentValues();
                values.put("id", (getCount() + 1));
                values.put("denominacion", coinselected);
                values.put("value", coinselected.replace("$",""));
                rows = db.insertOrThrow("Coin", null, values);

                return true;
            } else {

                return false;
            }
        } catch (Exception e) {

            return false;
        } finally {
            if (db != null)
                db.close();
        }
    }

    public static int getCount() {
        String mensaje = "";
        int total = 0;
        SQLiteDatabase db = null;
        try {
            File dbFile = new File(Util.dirApp(), Const.NAMEDATABASE);
            if (existDataBase()) {
                db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
                String query = "SELECT count(*) AS total FROM (SELECT * FROM Coin) ";
                Cursor cursor = db.rawQuery(query, null);
                if (cursor.moveToFirst()) {
                    total = cursor.getInt(cursor.getColumnIndex("total"));
                }
                cursor.close();
            } else {
                mensaje = "No existe la base de datos.";
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        } finally {
            closeDataBase(db);
        }

        return total;
    }

    public static int getCountByDenominacion(String coinselected) {
        String mensaje = "";
        int total = 0;
        SQLiteDatabase db = null;
        try {
            File dbFile = new File(Util.dirApp(), Const.NAMEDATABASE);
            if (existDataBase()) {
                db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
                String query = "SELECT count(*) AS total FROM (SELECT * FROM Coin WHERE denominacion LIKE '" + coinselected + "') ";
                Cursor cursor = db.rawQuery(query, null);
                if (cursor.moveToFirst()) {
                    total = cursor.getInt(cursor.getColumnIndex("total"));
                }
                cursor.close();
            } else {
                mensaje = "No existe la base de datos.";
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        } finally {
            closeDataBase(db);
        }

        return total;
    }

    public static float getSum() {
        String mensaje = "";
        float total = 0;
        SQLiteDatabase db = null;
        try {
            File dbFile = new File(Util.dirApp(), Const.NAMEDATABASE);
            if (existDataBase()) {
                db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
                String query = "SELECT sum(value) AS total FROM (SELECT * FROM Coin) ";
                Cursor cursor = db.rawQuery(query, null);
                if (cursor.moveToFirst()) {
                    total = cursor.getInt(cursor.getColumnIndex("total"));
                }
                cursor.close();
            } else {
                mensaje = "No existe la base de datos.";
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        } finally {
            closeDataBase(db);
        }

        return total;
    }

    public static float getSumByDenominacion(String coinselected) {
        String mensaje = "";
        float total = 0;
        SQLiteDatabase db = null;
        try {
            File dbFile = new File(Util.dirApp(), Const.NAMEDATABASE);
            if (existDataBase()) {
                db = SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
                String query = "SELECT sum(value) AS total FROM (SELECT * FROM Coin WHERE denominacion LIKE '" + coinselected + "') ";
                Cursor cursor = db.rawQuery(query, null);
                if (cursor.moveToFirst()) {
                    total = cursor.getInt(cursor.getColumnIndex("total"));
                }
                cursor.close();
            } else {
                mensaje = "No existe la base de datos.";
            }
        } catch (Exception e) {
            mensaje = e.getMessage();
        } finally {
            closeDataBase(db);
        }

        return total;
    }

    public static void closeDataBase(SQLiteDatabase db) {
        if (db != null) {
            if (db.inTransaction()) db.endTransaction();
            db.close();
        }
    }
}
