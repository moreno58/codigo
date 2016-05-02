package mx.edu.utng.jqueryv1.ppt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mx.edu.utng.jqueryv1.R;

public class JuegoPorTurnos extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juegoporturnos_layout);
	}
	
	
	public void lanzarClasicoPorTurnos(View view)
	{
		Intent i = new Intent(this, JuegoClasicoPorTurnos.class);
		startActivity(i);
	}
	
	public void lanzarLagartoPorTurnos(View view)
	{
		Intent i = new Intent(this, JuegoLagartoPorTurnos.class);
		startActivity(i);
	}
	
	public void lanzarElementosPorTurnos(View view)
	{
		Intent i = new Intent(this, JuegoElementosPorTurnos.class);
		startActivity(i);
	}
	
	

}

