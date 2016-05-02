package mx.edu.utng.jqueryv1.ppt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mx.edu.utng.jqueryv1.R;

public class Unjugador extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unjugador_layout);
	}
	
	
	public void lanzarClasico(View view)
	{
		Intent i = new Intent(this, JuegoClasico.class);
		startActivity(i);
	}
	
	public void lanzarLagarto(View view)
	{
		Intent i = new Intent(this, JuegoLagarto.class);
		startActivity(i);
	}
	
	public void lanzarElementos(View view)
	{
		Intent i = new Intent(this, juegoElementos.class);
		startActivity(i);
	}
	
	

}
