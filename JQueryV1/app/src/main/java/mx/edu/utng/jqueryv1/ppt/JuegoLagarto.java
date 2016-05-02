package mx.edu.utng.jqueryv1.ppt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mx.edu.utng.jqueryv1.R;


public class JuegoLagarto extends Activity {
    private ImageView fondo1, fondo2;
    private TextView texto;
    private int opcion1,opcion2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juegolagarto_layout);
        fondo1 = (ImageView) findViewById(R.id.opcion1);
        fondo2 = (ImageView) findViewById(R.id.opcion2);
        texto = (TextView) findViewById(R.id.mensaje);
    }

    public void lanzarAyudaLagarto(View view) {

        Intent i = new Intent(this, Ayuda_lagarto.class);
        startActivity(i);
    }

    public void lanzarTijeras(View view) {

        fondo1.setImageResource(R.drawable.tijeras);
        jugar(1);
    }

    public void lanzarPiedras(View view) {

        fondo1.setImageResource(R.drawable.piedra);
        jugar(2);

    }

    public void lanzarPapeles(View view) {

        fondo1.setImageResource(R.drawable.papel);
        jugar(3);
    }
    public void lanzarLagarto(View view) {

        fondo1.setImageResource(R.drawable.lagarto);
        jugar(4);
    }
    public void lanzarSpock(View view) {

        fondo1.setImageResource(R.drawable.spock);
        jugar(5);
    }

    public void parpadeo2(int i) {
        fondo2.setImageResource(R.drawable.vacio);
        ImageView cambioimagen = (ImageView) findViewById(R.id.opcion2);

        if(i==1)
        cambioimagen.setBackgroundResource(R.drawable.parpadeotijera);
        else if(i==2)
            cambioimagen.setBackgroundResource(R.drawable.parpadeopiedra);
        else
            cambioimagen.setBackgroundResource(R.drawable.parpadeopapel);

        Animation transicion = AnimationUtils.loadAnimation(this, R.anim.parpadeoclasico);
         AnimationDrawable animacion = (AnimationDrawable) fondo2.getBackground();
         cambioimagen.setAnimation(transicion);
         animacion.start();

        //while(!animation.isRunning())
        //{fondo2.setImageResource(R.drawable.interrogante);};

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


    public void jugar(int opcion) {
        opcion1=opcion;
        opcion2 = (int) (Math.random() * 5) + 1;
        switch (opcion2) {
        case 1:
            parpadeo2(1);
            //fondo2.setImageResource(R.drawable.tijeras);
            if (opcion1 == 3 || opcion1 == 4 )
                texto.setText(R.string.perdiste);
            else if (opcion1 == 2 || opcion1 == 5 )
                texto.setText(R.string.ganaste);
            else
                texto.setText(R.string.empataste);
            break;
        case 2:
            parpadeo2(2);
            //fondo2.setImageResource(R.drawable.piedra);
            if (opcion1 == 1 || opcion1 == 4 )
                texto.setText(R.string.perdiste);
            else if (opcion1 == 3 ||  opcion1 == 5 )
                texto.setText(R.string.ganaste);
            else
                texto.setText(R.string.empataste);
            break;
        case 3:
            parpadeo2(3);
            //fondo2.setImageResource(R.drawable.papel);
            if (opcion1 == 2 || opcion1 == 5 )
                texto.setText(R.string.perdiste);
            else if (opcion1 == 1 || opcion1 == 4 )
                texto.setText(R.string.ganaste);
            else
                texto.setText(R.string.empataste);
            break;
        case 4:

            parpadeo2(4);
            //fondo2.setImageResource(R.drawable.lagarto);
            if (opcion1 == 1 || opcion1 == 2 )
                texto.setText(R.string.ganaste);
            else if (opcion1 == 5 || opcion1 == 3 )
                texto.setText(R.string.perdiste);
            else
                texto.setText(R.string.empataste);
            break;

       case 5:
           parpadeo2(5);

           //fondo2.setImageResource(R.drawable.spock);
           if (opcion1 == 3 || opcion1 == 4 )
               texto.setText(R.string.ganaste);
           else if (opcion1 == 1 || opcion1 == 2 )
               texto.setText(R.string.perdiste);
           else
               texto.setText(R.string.empataste);
           break;
    }
}
}
