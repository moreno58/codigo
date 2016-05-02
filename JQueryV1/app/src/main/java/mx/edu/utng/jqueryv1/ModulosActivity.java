package mx.edu.utng.jqueryv1;

/**
 * Created by Jeanette Moreno on 24/04/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import mx.edu.utng.jqueryv1.grafica.SQLControlador;
import mx.edu.utng.jqueryv1.tabs.TabsActivity;
import mx.edu.utng.jqueryv1.tabs.TabsActivityCuatro;
import mx.edu.utng.jqueryv1.tabs.TabsActivityDos;
import mx.edu.utng.jqueryv1.tabs.TabsActivityTres;

public class ModulosActivity extends AppCompatActivity implements View.OnClickListener {
    SQLControlador db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuprincipal);

        db = new SQLControlador(getApplication());

        ImageButton m1 = (ImageButton) findViewById(R.id.button);
        ImageButton m2 = (ImageButton) findViewById(R.id.button2);
        ImageButton m3 = (ImageButton) findViewById(R.id.button3);
        ImageButton m4 = (ImageButton) findViewById(R.id.button4);
        ImageButton quizFinal = (ImageButton) findViewById(R.id.button5);


        m1.setOnClickListener(this);
        m2.setOnClickListener(this);
        m3.setOnClickListener(this);
        m4.setOnClickListener(this);
        quizFinal.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                startActivity(new Intent(ModulosActivity.this, TabsActivity.class));
                Toast.makeText(ModulosActivity.this, "modulo 1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button2:
                db.abrirBaseDeDatos();
                if (Integer.parseInt(db.resultado("1")) >= 6) {
                    startActivity(new Intent(ModulosActivity.this, TabsActivityDos.class));
                    Toast.makeText(ModulosActivity.this, "modulo 2", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ModulosActivity.this, "No haz aprobado el módulo 1", Toast.LENGTH_SHORT).show();
                }
                db.cerrar();
                break;

            case R.id.button3:
                db.abrirBaseDeDatos();
                if (Integer.parseInt(db.resultado("2")) >= 6) {
                    startActivity(new Intent(ModulosActivity.this, TabsActivityTres.class));
                    Toast.makeText(ModulosActivity.this, "modulo 3", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ModulosActivity.this, "No haz aprobado el módulo 2", Toast.LENGTH_SHORT).show();
                }
                db.cerrar();
                break;

            case R.id.button4:
                db.abrirBaseDeDatos();
                if (Integer.parseInt(db.resultado("3")) >= 6) {
                    startActivity(new Intent(ModulosActivity.this, TabsActivityTres.class));
                    startActivity(new Intent(ModulosActivity.this, TabsActivityCuatro.class));
                    Toast.makeText(ModulosActivity.this, "modulo 4", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ModulosActivity.this, "No haz aprobado el módulo 3", Toast.LENGTH_SHORT).show();
                }
                db.cerrar();
                break;

            case R.id.button5:
                db.abrirBaseDeDatos();
                if (Integer.parseInt(db.resultado("4")) >= 6) {
                    startActivity(new Intent(ModulosActivity.this, TabsActivityTres.class));
                    startActivity(new Intent(ModulosActivity.this, QuizCincoActivity.class));
                    Toast.makeText(ModulosActivity.this, "Quiz Final", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ModulosActivity.this, "No haz aprobado el módulo 4", Toast.LENGTH_SHORT).show();
                }
                db.cerrar();
                break;
        }
    }
}
