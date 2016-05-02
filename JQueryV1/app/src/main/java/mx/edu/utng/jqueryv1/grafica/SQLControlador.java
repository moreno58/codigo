package mx.edu.utng.jqueryv1.grafica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLControlador {

    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;
    public static String resultado = "";

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public void insertarDatos(String name) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.CALIFICACION_NOMBRE, name);
        database.insert(DBhelper.TABLE_MEMBER, null, cv);
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                DBhelper.CALIFICACION_ID,
                DBhelper.CALIFICACION_NOMBRE
        };
        Cursor c = database.query(DBhelper.TABLE_MEMBER, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    public String resultado(String id){
        try{
            Cursor cursor = database.rawQuery("select calificacion from calificacion where _id = ?",new String[]{id});
            cursor.moveToFirst();
            resultado = cursor.getString(0);
        }catch (Exception e){
            Log.i("El error ", e.toString());
        }
        return resultado;
    }


    public int actualizarDatos(long memberID, String memberName) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.CALIFICACION_NOMBRE, memberName);
        int i = database.update(DBhelper.TABLE_MEMBER, cvActualizar,
                DBhelper.CALIFICACION_ID + " = " + memberID, null);
        return i;
    }

    public void deleteData(long memberID) {
        database.delete(DBhelper.TABLE_MEMBER, DBhelper.CALIFICACION_ID + "="
                + memberID, null);
    }
}