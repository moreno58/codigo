package mx.edu.utng.jqueryv1.recetario;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import mx.edu.utng.jqueryv1.R;

public class PublicarReceta extends Activity {

	// Variables
	private Button btEnviar;
	private Context context = this;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publicar_receta);
        
        btEnviar = (Button) findViewById(R.id.btEnviarReceta);
    	
    	// Accion cuando se pulsa el boton "Enviar"
    	btEnviar.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            	RadioButton r1 = (RadioButton) findViewById(R.id.radioButton1);
            	boolean checked_r1 = r1.isChecked();
            	RadioButton r2 = (RadioButton) findViewById(R.id.radioButton2);
            	boolean checked_r2 = r2.isChecked();
            	RadioButton r3 = (RadioButton) findViewById(R.id.radioButton3);
            	boolean checked_r3 = r3.isChecked();
            	EditText nombreReceta = (EditText) findViewById(R.id.inputTextNombreReceta);
            	String sNombreReceta = nombreReceta.getText().toString();
            	EditText ingReceta = (EditText) findViewById(R.id.inputTextIngReceta);
            	String sIngReceta = ingReceta.getText().toString();
            	EditText elaReceta = (EditText) findViewById(R.id.inputTextElaReceta);
            	String sElaReceta = elaReceta.getText().toString();
            	if (!checked_r1 && !checked_r2 && !checked_r3) {
            		Toast.makeText(context, "Debes elegir una dificultad para la receta", Toast.LENGTH_SHORT).show();
            	} else if (sNombreReceta.trim().length() == 0) {
            		Toast.makeText(context, "Debes escribir un nombre para la receta", Toast.LENGTH_SHORT).show();
            	} else if (sIngReceta.trim().length() == 0) {
            		Toast.makeText(context, "Debes escribir los ingredientes de la receta", Toast.LENGTH_SHORT).show();
            	} else if (sElaReceta.trim().length() == 0) {
            		Toast.makeText(context, "Debes escribir los pasos a seguir para hacer de la receta", Toast.LENGTH_SHORT).show();
            	} else {
            		String dificultad = "";
            		if (checked_r1) {
            			dificultad = getString(R.string.facil);
            		} else if (checked_r2) {
            			dificultad = getString(R.string.medio);
            		} else if (checked_r3) {
            			dificultad = getString(R.string.dificil);
            		}
            		RecetasSQL recetasSQL = new RecetasSQL(context);
                    SQLiteDatabase db = recetasSQL.getWritableDatabase();
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put("dificultad", dificultad);
                    nuevoRegistro.put("titulo", sNombreReceta);
                    nuevoRegistro.put("ingredientes", sIngReceta);
                    nuevoRegistro.put("elaboracion", sElaReceta);
                    db.insert("recetas", null, nuevoRegistro);
                    db.close();
                    Toast.makeText(context, "Receta publicada. Gracias!", Toast.LENGTH_SHORT).show();
                    nombreReceta.setText("");
                    ingReceta.setText("");
                    elaReceta.setText("");
            	}
           }
        });
    	
	}
	
	
	
}
