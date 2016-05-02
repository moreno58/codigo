package mx.edu.utng.jqueryv1;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActividad extends Activity implements View.OnClickListener {
    //implements view.onClickListener

    public static int acceso = 0;
    private Button aceptar;
    private Button registrarse;



    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);


        aceptar = (Button) findViewById(R.id.btn_login);
        aceptar.setOnClickListener(this);
        registrarse = (Button) findViewById(R.id.btn_signup);
        registrarse.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                EditText a = (EditText)findViewById(R.id.edt_Usuario);
                String str = a.getText().toString();

                EditText b = (EditText)findViewById(R.id.edt_Clave);
                String pass = b.getText().toString();

                String password = helper.searchPass(str);
                if(pass.equals(password)){
                    Intent i = new Intent(LoginActividad.this, MainActivity.class);
                    i.putExtra("Username",str);
                    startActivity(i);
acceso = 1;
                }else{
                    Toast temp = Toast.makeText(LoginActividad.this,"Datos Erroneos", Toast.LENGTH_SHORT);
                    temp.show();
                    acceso=0;
                }
                break;
            case R.id.btn_signup:
                Intent i = new Intent(LoginActividad.this, SignUp.class);
                startActivity(i);
                break;

        }
    }
}
