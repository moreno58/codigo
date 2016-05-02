package mx.edu.utng.jqueryv1.recetario;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import mx.edu.utng.jqueryv1.R;

public class ListaCompra extends Activity {
	
	// Variables
	private EditText texto;
	private TextView listaCompra;
	private Button btAddToLista, btGuardarLista, btBorrarLista;
	private static String FICHERO = Environment.getExternalStorageDirectory() + "/lista-compra.txt";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.lista_compra);
         
         btAddToLista = (Button) findViewById(R.id.btAddTextListaCompra);
         // Accion cuando se pulsa el boton "Anadir"
         btAddToLista.setOnClickListener(new OnClickListener() {
             public void onClick(View view) { 
             	addToLista(null);
            }
         });
         btGuardarLista = (Button) findViewById(R.id.btSaveLista);
         // Accion cuando se pulsa el boton "Guardar lista"
         btGuardarLista.setOnClickListener(new OnClickListener() {
             public void onClick(View view) { 
             	saveLista(null);
            }
         });
         btBorrarLista = (Button) findViewById(R.id.btDeleteLista);
         // Accion cuando se pulsa el boton "Borrar lista"
         btBorrarLista.setOnClickListener(new OnClickListener() {
             public void onClick(View view) { 
             	deleteLista(null);
            }
         });
         
         // Comprobamos si hay una lista guardada en el fichero
         listaCompra = (TextView) findViewById(R.id.listaCompra);
		 try {
			 String estadoSD = Environment.getExternalStorageState();
			 if (!estadoSD.equals(Environment.MEDIA_MOUNTED) && !estadoSD.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
				 Toast.makeText(this, "No puedo leer en la memoria externa", Toast.LENGTH_LONG).show();
			 } else {
				 FileInputStream f = new FileInputStream(FICHERO);
		         BufferedReader entrada = new BufferedReader(new InputStreamReader(f));
		         int n = 0;
		         String linea;
		         do {
		        	 linea = entrada.readLine();
		        	 if (linea != null) {
		        		 listaCompra.setText(listaCompra.getText().toString() + linea + "\n");
		        		 listaCompra.setMovementMethod(new ScrollingMovementMethod());
		                 n++;
		        	 }
		         } while (linea != null);
		         f.close();
			 }
		 } catch (Exception e) {
			 Log.e("Asteroides", e.getMessage(), e);
		 }
    }
	
	// Anade un texto a la lista
	public void addToLista(View view) {
		listaCompra = (TextView) findViewById(R.id.listaCompra);
		texto = (EditText) findViewById(R.id.inputText);
		if (texto.getText().toString().trim().length() > 0) {
			listaCompra.setText(listaCompra.getText().toString() + texto.getText().toString() + "\n");
			listaCompra.setMovementMethod(new ScrollingMovementMethod());
			texto.setText("");
		}
    }
	
	// Guarda la lista en un fichero externo
	public void saveLista(View view) {
		try {
      	  String estadoSD = Environment.getExternalStorageState();
      	  if (!estadoSD.equals(Environment.MEDIA_MOUNTED)) {
      		  Toast.makeText(this, "No se puede escribir en la memoria externa", Toast.LENGTH_LONG).show();
      	  } else {
      	  	 FileOutputStream f = new FileOutputStream(FICHERO);      	  	 
      	  	 listaCompra = (TextView) findViewById(R.id.listaCompra);
      	  	 String texto = listaCompra.getText().toString();
      	  	 f.write(texto.getBytes());
      	  	 f.close();
      	  	 Toast.makeText(this, R.string.msg_guardado, Toast.LENGTH_LONG).show();
      	  }
        } catch (Exception e) {
           Log.e("Main", e.getMessage(), e);
        }
    }
	
	// Borra la lista
	public void deleteLista(View view) {
		listaCompra = (TextView) findViewById(R.id.listaCompra);
		listaCompra.setText("");
		try {
      	  String estadoSD = Environment.getExternalStorageState();
      	  if (!estadoSD.equals(Environment.MEDIA_MOUNTED)) {
      		  Toast.makeText(this, "No se puede escribir en la memoria externa", Toast.LENGTH_LONG).show();
      		  return;
      	  } else {
      		 FileOutputStream f = new FileOutputStream(FICHERO);
     	  	 String texto = "";
     	  	 f.write(texto.getBytes());
     	  	 f.close();
     	  	 Toast.makeText(this, R.string.msg_borrado, Toast.LENGTH_LONG).show();
      	  }
        } catch (Exception e) {
           Log.e("Main", e.getMessage(), e);
        }
    }

}
