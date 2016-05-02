package mx.edu.utng.jqueryv1.recetario;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import mx.edu.utng.jqueryv1.R;

public class RecetaDetalle extends Activity {
	
	// Variables
	private TextView nombreReceta, ingReceta, elaReceta;

	@Override
	public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.detalle_receta);
         
         nombreReceta = (TextView) findViewById(R.id.nombreReceta);
         ingReceta = (TextView) findViewById(R.id.ingreReceta);
         elaReceta = (TextView) findViewById(R.id.elaboReceta);
         
         Bundle extras = getIntent().getExtras();
         Integer id_receta = extras.getInt("id");
         ObjetoReceta o = Main.almacen.getReceta(id_receta);
         nombreReceta.setText(o.getTitulo());
         ingReceta.setText(o.getIngredientes());
         elaReceta.setText(o.getElaboracion());
	}
	
}
