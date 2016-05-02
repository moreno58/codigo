package mx.edu.utng.jqueryv1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

import mx.edu.utng.jqueryv1.grafica.SQLControlador;


public class CorreoActivity extends AppCompatActivity  implements View.OnClickListener {

    SQLControlador correo;

    private ImageView imvEnviarEmailAhora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        correo = new SQLControlador(getApplication());
        setContentView(R.layout.correo_layout);
        initComponets();
        correo.abrirBaseDeDatos();
    }

    private void initComponets() {
        imvEnviarEmailAhora=(ImageView)findViewById(R.id.imv_correo);
        imvEnviarEmailAhora.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.imv_correo:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
                //email.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://"+ getPackageName()+ "/" + R.drawable.certificado));
                email.putExtra(Intent.EXTRA_SUBJECT, "jQuery Mobile");
                email.putExtra(Intent.EXTRA_TEXT, "Calificaciones actuales: \n Modulo 1: " + correo.resultado("1")
                        + "\n Modulo 2: " + correo.resultado("2")
                        + "\n Modulo 3: " + correo.resultado("3")
                        + "\n Modulo 4: " + correo.resultado("4")
                        + "\n Modulo 5: " + correo.resultado("5")
                        + "\n Gracias por realizar el curso");
                email.setType("plain/text");


                startActivity(Intent.createChooser(email, ""));


        }
    }
}
