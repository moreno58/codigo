package mx.edu.utng.jqueryv1.ppt;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import mx.edu.utng.jqueryv1.R;


public class Crearjuego extends Activity {

	// DEL SERVER
	private BluetoothAdapter mBluetoothAdapter;
	public UUID MyUUID;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public serverBluetooth serverBluetooth;
	// DEL JUEGO
	private Message mensaje;
	private int turno, opcion1, opcion2;
	private ImageView fondo1, fondo2;
	private TextView texto, texto2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crearjuego_layout);

		// SERVER
		MyUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		serverBluetooth = new serverBluetooth(this, MyUUID,
				this.mBluetoothAdapter, mHandler);
		serverBluetooth.start();
		// JUEGO
		turno = 1;
		fondo1 = (ImageView) findViewById(R.id.opcion1);
		fondo2 = (ImageView) findViewById(R.id.opcion2);
		texto = (TextView) findViewById(R.id.mensaje);
		texto2 = (TextView) findViewById(R.id.mensaje2);

	}

	private final Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_WRITE:
				byte[] writeBuf = (byte[]) msg.obj;
				String writeMessage = new String(writeBuf);
				// mensaje que se envia
				// mConversationArrayAdapter.add("Me:  " + writeMessage);
				break;
			case MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;
				// construct a string from the valid bytes in the buffer
				String readMessage = new String(readBuf, 0, msg.arg1);
				// mensaje que se recibe
				
				texto2.setText(R.string.esperandosegundojugador);
				break;
			}
		}
	};

	public void lanzarTijeras(View view) {

		if (turno == 1) {
			texto.setText(R.string.mensaje);
			fondo1.setImageResource(R.drawable.tijeras);
			opcion1 = 1;
			turno = 2;
			texto.setText(R.string.mensaje);
			fondo2.setImageResource(R.drawable.fondopciones);
			Toast.makeText(this, "antes de enviar mensaje a cliente x", Toast.LENGTH_LONG)
			.show();
			mHandler.sendEmptyMessage(1);
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
			mensaje.arg1=2;
			Toast.makeText(this, "antes de enviar mensaje a cliente x", Toast.LENGTH_LONG)
			.show();
			mHandler.sendEmptyMessage(2);
			Toast.makeText(this, "enviado mensaje a cliente x", Toast.LENGTH_LONG)
			.show();
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
			mensaje.arg1=3;
			mHandler.sendEmptyMessage(3);
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

	public void jugar() {
		
		switch (opcion2) {
		case 1:
			parpadeo();
			if (opcion1 == 3)
				texto.setText(R.string.pierdeuno);
			else if (opcion1 == 2)
				texto.setText(R.string.ganauno);
			else
				texto.setText(R.string.empate);
			break;
		case 2:
			parpadeo();
			if (opcion1 == 1)
				texto.setText(R.string.pierdeuno);
			else if (opcion1 == 3)
				texto.setText(R.string.ganauno);
			else
				texto.setText(R.string.empate);
			break;
		case 3:
			parpadeo();
			if (opcion1 == 2)
				texto.setText(R.string.pierdeuno);
			else if (opcion1 == 1)
				texto.setText(R.string.ganauno);
			else
				texto.setText(R.string.empate);
			break;
		}

	}

}
