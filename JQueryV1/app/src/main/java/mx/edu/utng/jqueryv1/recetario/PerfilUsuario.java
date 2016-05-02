package mx.edu.utng.jqueryv1.recetario;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import mx.edu.utng.jqueryv1.R;

public class PerfilUsuario extends Activity {
	
	private TextView recPub;

	@Override
	public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.perfil_usuario);
         
         recPub = (TextView) findViewById(R.id.textView1);
         RecetasSQL recetasSQL = new RecetasSQL(this);
         SQLiteDatabase db = recetasSQL.getReadableDatabase();
         Cursor cursor = db.query("recetas", null, null, null, null, null, null);
         Integer numRecetas = cursor.getCount();
         cursor.close();
         recPub.setText("Recetas publicadas: "+numRecetas);
	}
	
}
