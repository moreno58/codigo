package mx.edu.utng.jqueryv1.recetario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

public class RecetasSQL extends SQLiteOpenHelper implements AlmacenRecetas {
	
	private static final String DATABASE_CREATE = "CREATE TABLE recetas (" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"dificultad TEXT, " +
			"titulo TEXT, " +
			"ingredientes TEXT, " +
			"elaboracion TEXT)";
	
	public RecetasSQL(Context context) {
		super(context, "BBDD_recetas", null, 6);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);	// Crea la BBDD
	}
	
	@Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("DROP TABLE IF EXISTS recetas");	//Se elimina la version anterior de la tabla
        db.execSQL(DATABASE_CREATE); //Se crea la nueva version de la tabla
    }
	
	@Override
    public void guardarReceta(String nombre) {
    	
    }
	
	public Vector<ObjetoReceta> listaRecetas() {
		Vector<ObjetoReceta> result = new Vector<ObjetoReceta>();
		SQLiteDatabase db = getWritableDatabase();
		String[] CAMPOS = new String[] {"id", "dificultad", "titulo"};
	    Cursor cursor = db.query("recetas", CAMPOS, null, null, null, null, "id ASC");
		while (cursor.moveToNext()) {
			ObjetoReceta o = new ObjetoReceta(cursor.getInt(0), cursor.getString(1), cursor.getString(2), "", "");
			result.add(o);
		}
		cursor.close();
		return result;
	}
	
	public ObjetoReceta getReceta(Integer id) {
		SQLiteDatabase db = getReadableDatabase();
		String[] CAMPOS = new String[] {"dificultad", "titulo", "ingredientes", "elaboracion"};
	    Cursor cursor = db.query("recetas", CAMPOS, "id="+id, null, null, null, null);
	    cursor.moveToFirst();
	    ObjetoReceta o = new ObjetoReceta(id, cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
	    cursor.close();
		return o;
	}
}
