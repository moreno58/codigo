package mx.edu.utng.jqueryv1.ppt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mx.edu.utng.jqueryv1.R;

public class Multiplayer  extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multijugador_layout);
	}
	
	public void lanzarBluetooth(View view)
	{
		Intent i = new Intent(this, Bluetooth.class);
		startActivity(i);
	}
	
	public void lanzarPorturnos(View view)
	{
		Intent i = new Intent(this, JuegoPorTurnos.class);
		startActivity(i);
	}
}
