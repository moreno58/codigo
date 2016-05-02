/**
 * 
 */
package mx.edu.utng.jqueryv1.juego1;

import android.app.Activity;
import android.os.Bundle;

import mx.edu.utng.jqueryv1.R;


public class juegoActivity extends Activity {
	
	private VistaJuego vistaJuego;
	public static AlmacenPuntuacionesFichero almacen;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juego);
		vistaJuego = (VistaJuego) findViewById(R.id.vistaJuego);
		vistaJuego.setPadre(this);
	}
}
