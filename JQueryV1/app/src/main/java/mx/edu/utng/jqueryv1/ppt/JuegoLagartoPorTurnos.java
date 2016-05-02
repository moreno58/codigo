package mx.edu.utng.jqueryv1.ppt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mx.edu.utng.jqueryv1.R;

public class JuegoLagartoPorTurnos extends Activity {
	private int turno, opcion1, opcion2;
	private ImageView fondo1, fondo2;
	private TextView texto, texto2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juegolagartoporturnos_layout);
		turno = 1;
		fondo1 = (ImageView) findViewById(R.id.opcion1);
		fondo2 = (ImageView) findViewById(R.id.opcion2);
		texto = (TextView) findViewById(R.id.mensaje);
		texto2 = (TextView) findViewById(R.id.mensaje2);
		texto.setText(R.string.mensaje);
	}

	public void lanzarAyuda(View view) {

		Intent i = new Intent(this, Ayuda_lagarto.class);
		startActivity(i);
	}
	
	public void lanzarTijeras(View view) {

		if (turno == 1) {
			texto.setText(R.string.mensaje);
			fondo1.setImageResource(R.drawable.tijeras);
			opcion1 = 1;
			turno = 2;
			texto2.setText(R.string.turnosegundojugador);
			fondo2.setImageResource(R.drawable.fondopciones);
		} else {

			fondo2.setImageResource(R.drawable.tijeras);
			opcion2 = 1;
			turno = 1;
			texto2.setText(R.string.turnoprimerjugador);
			jugar();
		}

	}

	public void lanzarPiedras(View view) {

		if (turno == 1) {
			texto.setText(R.string.mensaje);
			fondo1.setImageResource(R.drawable.piedra);
			opcion1 = 2;
			turno = 2;
			texto2.setText(R.string.turnosegundojugador);
			fondo2.setImageResource(R.drawable.fondopciones);
		} else {

			fondo2.setImageResource(R.drawable.piedra);
			opcion2 = 2;
			turno = 1;
			texto2.setText(R.string.turnoprimerjugador);
			jugar();
		}

	}

	public void lanzarPapeles(View view) {

		if (turno == 1) {
			texto.setText(R.string.mensaje);
			fondo1.setImageResource(R.drawable.papel);
			opcion1 = 3;
			turno = 2;
			texto2.setText(R.string.turnosegundojugador);
			fondo2.setImageResource(R.drawable.fondopciones);
		} else {

			fondo2.setImageResource(R.drawable.papel);
			opcion2 = 3;
			turno = 1;
			texto2.setText(R.string.turnoprimerjugador);
			jugar();
		}

	}

	public void lanzarLagarto(View view) {

		if (turno == 1) {
			texto.setText(R.string.mensaje);
			fondo1.setImageResource(R.drawable.lagarto);
			opcion1 = 4;
			turno = 2;
			texto2.setText(R.string.turnosegundojugador);
			fondo2.setImageResource(R.drawable.fondopciones);
		} else {

			fondo2.setImageResource(R.drawable.lagarto);
			opcion2 = 4;
			turno = 1;
			texto2.setText(R.string.turnoprimerjugador);
			jugar();
		}

	}

	public void lanzarSpock(View view) {

		if (turno == 1) {
			texto.setText(R.string.mensaje);
			fondo1.setImageResource(R.drawable.spock);
			opcion1 = 5;
			turno = 2;
			texto2.setText(R.string.turnosegundojugador);
			fondo2.setImageResource(R.drawable.fondopciones);
		} else {

			fondo2.setImageResource(R.drawable.spock);
			opcion2 = 5;
			turno = 1;
			texto2.setText(R.string.turnoprimerjugador);
			jugar();
		}

	}

	public void parpadeo() {

		AnimationDrawable animation = new AnimationDrawable();
		animation.addFrame(getResources().getDrawable(R.drawable.papel), 500);
		animation.addFrame(getResources().getDrawable(R.drawable.piedra), 500);
		animation.addFrame(getResources().getDrawable(R.drawable.tijeras), 500);
		animation.setOneShot(true);

		ImageView imageAnim = (ImageView) findViewById(R.id.opcion2);
		// imageAnim.setBackgroundDrawable(animation);

		// start the animation!
		animation.start();
		// / NOOO VAAA???????
	}
	
	
	public void lanzarToast(){
		if(opcion1==1 && opcion2==2 || opcion1==2 && opcion2==1)
		Toast.makeText(this, R.string.piedratijeras, Toast.LENGTH_LONG)
		.show();
		else if(opcion1==1 && opcion2==3 || opcion1==3 && opcion2==1)
			Toast.makeText(this, R.string.tijeraspapel, Toast.LENGTH_LONG)
			.show();
		else if(opcion1==1 && opcion2==4 || opcion1==4 && opcion2==1)
			Toast.makeText(this, R.string.tijeraslagarto, Toast.LENGTH_LONG)
			.show();
		else if(opcion1==1 && opcion2==5 || opcion1==5 && opcion2==1)
			Toast.makeText(this, R.string.tijerasspock, Toast.LENGTH_LONG)
			.show();			
		else if(opcion1==2 && opcion2==3 || opcion1==3 && opcion2==2)
			Toast.makeText(this, R.string.piedrapapel, Toast.LENGTH_LONG)
			.show();
		else if(opcion1==2 && opcion2==4 || opcion1==4 && opcion2==2)
			Toast.makeText(this, R.string.piedralagarto, Toast.LENGTH_LONG)
			.show();
		else if(opcion1==2 && opcion2==5 || opcion1==5 && opcion2==2)
			Toast.makeText(this, R.string.piedraspock, Toast.LENGTH_LONG)
			.show();
		else if(opcion1==3 && opcion2==4 || opcion1==4 && opcion2==3)
			Toast.makeText(this, R.string.papellagarto, Toast.LENGTH_LONG)
			.show();
		else if(opcion1==3 && opcion2==5 || opcion1==5 && opcion2==3)
			Toast.makeText(this, R.string.papellagarto, Toast.LENGTH_LONG)
			.show();
		else if(opcion1==4 && opcion2==5 || opcion1==5 && opcion2==4)
			Toast.makeText(this, R.string.spocklagarto, Toast.LENGTH_LONG)
			.show();
		
	}
	

	public void jugar() {
		switch (opcion2) {
		case 1:
			parpadeo();

			if (opcion1 == 3 || opcion1 == 4)
				texto.setText(R.string.pierdeuno);
			else if (opcion1 == 2 || opcion1 == 5)
				texto.setText(R.string.ganauno);
			else
				texto.setText(R.string.empate);
			break;

		case 2:
			parpadeo();

			if (opcion1 == 1 || opcion1 == 4)
				texto.setText(R.string.pierdeuno);
			else if (opcion1 == 3 || opcion1 == 5)
				texto.setText(R.string.ganauno);
			else
				texto.setText(R.string.empate);
			break;

		case 3:
			parpadeo();

			if (opcion1 == 2 || opcion1 == 5)
				texto.setText(R.string.pierdeuno);
			else if (opcion1 == 1 || opcion1 == 4)
				texto.setText(R.string.ganauno);
			else
				texto.setText(R.string.empate);
			break;

		case 4:
			parpadeo();

			if (opcion1 == 2 || opcion1 == 1)
				texto.setText(R.string.pierdeuno);
			else if (opcion1 == 3 || opcion1 == 5)
				texto.setText(R.string.ganauno);
			else
				texto.setText(R.string.empate);
			break;

		case 5:
			parpadeo();

			if (opcion1 == 3 || opcion1 == 4)
				texto.setText(R.string.pierdeuno);
			else if (opcion1 == 1 || opcion1 == 2)
				texto.setText(R.string.ganauno);
			else
				texto.setText(R.string.empate);

		}
		
		lanzarToast();

	}

}
