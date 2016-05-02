package mx.edu.utng.jqueryv1.recetario;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import mx.edu.utng.jqueryv1.R;

public class Main extends Activity {
	
	// Variables
	private Button btVerRecetas, btPublicarReceta, btListaCompra, btSalir;
	public static AlmacenRecetas almacen;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Botones del menu principal
		btVerRecetas = (Button) findViewById(R.id.bt_recetas);
		btPublicarReceta = (Button) findViewById(R.id.bt_publicar);
		btListaCompra = (Button) findViewById(R.id.bt_lista);
		btSalir = (Button) findViewById(R.id.bt_salir);
		
		// Accion cuando se pulsa el boton "Ver Recetas" de la APP
		btVerRecetas.setOnClickListener(new OnClickListener() {
            public void onClick(View view) { 
            	mostrarRecetas(null);
           }
        });
		
		// Accion cuando se pulsa el boton "Publicar Receta" de la APP
		btPublicarReceta.setOnClickListener(new OnClickListener() {
            public void onClick(View view) { 
            	publicarReceta(null);
           }
        });
		
		// Accion cuando se pulsa el boton "Lista de la compra" de la APP
		btListaCompra.setOnClickListener(new OnClickListener() {
            public void onClick(View view) { 
            	verListaCompra(null);
           }
        });
		
		// Accion cuando se pulsa el boton "Salir" de la APP
		btSalir.setOnClickListener(new OnClickListener() {
            public void onClick(View view) { 
            	finish();
           }
        });
	}
	
	// Lanza la pantalla para ver las recetas
	public void mostrarRecetas(View view) {
		almacen = new RecetasSQL(this);
		Intent i = new Intent(this, Recetas.class);
		startActivity(i);
    }
	
	// Lanza la pantalla para publicar una receta
	public void publicarReceta(View view) {
		//almacen = new RecetasSQL(this);
		Intent i = new Intent(this, PublicarReceta.class);
		startActivity(i);
    }
	
	// Lanza la pantalla para ver la lista de la compra
	public void verListaCompra(View view){
		Intent i = new Intent(this, ListaCompra.class);
		startActivity(i);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main_receta, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.perfil:
			verMisDatos(null);
			break;
		default:
			break;
		}
		return true;
	}
	
	// Lanza la pantalla para ver los datos de perfil
	public void verMisDatos(View view) {
		Intent i = new Intent(this, PerfilUsuario.class);
		startActivity(i);
    }

}
