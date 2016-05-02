package mx.edu.utng.jqueryv1.ppt;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import mx.edu.utng.jqueryv1.R;

public class MainActivityPPT extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_ppt);
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
		return true;
	}
	
	public void lanzarUnjugador(View view)
	{
		Intent i = new Intent(this, Unjugador.class);
		startActivity(i);
	}
	
	public void lanzarMultiJugador(View view){
		Intent i = new Intent(this, Multiplayer.class);
		startActivity(i);
	}
	
	
	public void lanzarSalida(View view) {
		finish();
	}

}
