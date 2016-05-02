package mx.edu.utng.jqueryv1.ppt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import mx.edu.utng.jqueryv1.R;

public class Bluetooth extends Activity {

	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	int REQUEST_ENABLE_BT = 1;
	BluetoothAdapter mBluetoothAdapter;
	ArrayList<Dispositivo> mArrayAdapter;
	boolean paired;
	ListView listView;
	UUID MyUUID;
	BluetoothSocket mBS;
	BluetoothDevice device;
	InputStream ins;
	OutputStream ons;
	BluetoothServerSocket mmServerSocket;
	boolean server;
	serverBluetooth serverBluetooth;
	AlertDialog.Builder builderEsp;
	PrintWriter print;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetooth_layout);
		paired = false;
		builderEsp = new AlertDialog.Builder(this);
		paired = false;
		server = false;
		serverBluetooth = null;
		print = null;
		mArrayAdapter = new ArrayList<Dispositivo>();
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			Toast.makeText(this, R.string.errorbluetooth, Toast.LENGTH_LONG)
					.show();
			finish();
		}
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent, REQUEST_ENABLE_BT);

			listView = (ListView) findViewById(R.id.listView1);
			listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			OnItemClickListener oICL = new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					builder.setMessage("Se va a conectar a: "
							+ mArrayAdapter.get(position).getName());
					builder.create();
					builder.show();
					conectaDispositivo(mBluetoothAdapter
							.getRemoteDevice(mArrayAdapter.get(position)
									.getMAC()));
				}
			};
			listView.setOnItemClickListener(oICL);
			MyUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

		}
	}

	

	public ArrayList<Dispositivo> preguntaDispositivosCercanos(
			BluetoothAdapter mBluetoothAdapter) {
		ArrayList<Dispositivo> mArrayAdapter = new ArrayList<Dispositivo>();
		Dispositivo d = new Dispositivo();
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
				.getBondedDevices();
		// If there are paired devices
		if (pairedDevices.size() > 0) {
			// Loop through paired devices
			for (BluetoothDevice device : pairedDevices) {
				// Add the name and address to an array adapter to show in a
				// ListView d.setName(device.getName());
				d.setMAC(device.getAddress());
				Log.d("Apareo", "El usuario de este movil es:   " + d.getMAC());
			}
		}
		return mArrayAdapter;
	}

	public void refrescaConexiones(View view) {
		listView.removeViewInLayout(view);
		queringPairedDevices();
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		if (mBluetoothAdapter.startDiscovery())
			;
		this.registerReceiver(mReceiver, filter);
		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
	}

	public void queringPairedDevices() {
		Dispositivo d = new Dispositivo();
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
				.getBondedDevices();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				d.setName(device.getName());
				d.setMAC(device.getAddress());
				mArrayAdapter.add(d);
			}
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				pasaAdaptadorAString(mArrayAdapter));
		listView.setAdapter(adapter);
	}

	void conectaDispositivo(BluetoothDevice device) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		try {
			mBS = device.createRfcommSocketToServiceRecord(MyUUID);
			mBS.connect();
			ons = mBS.getOutputStream();
			ons.write(new String("Esto se envia").getBytes());
			Log.d("app", "Se ha enviado el texto");
			mBS.close();

		} catch (Exception ex) {
			builder.setMessage("Fallo en conectaDispositivo()!!");
			builder.create();
			builder.show();
		}
	}

	public ArrayList<String> pasaAdaptadorAString(ArrayList<Dispositivo> d) {
		ArrayList<String> s = new ArrayList<String>();
		for (int i = 0; i < d.size(); i++) {
			s.add(d.get(i).getName() + "\n" + d.get(i).getMAC());
		}
		return s;

	}

	public void lanzarCrearjuego(View v) {
		if (!server) {
			server = true;

			serverBluetooth = new serverBluetooth(this, MyUUID,
					this.mBluetoothAdapter, mHandler);
			serverBluetooth.start();
			Toast.makeText(this, "Server mode ON", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "Server mode OFF", Toast.LENGTH_LONG).show();
			server = false;
			serverBluetooth.cancel();

		}
	}

	@SuppressLint("HandlerLeak")
	private final Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_WRITE:
				byte[] writeBuf = (byte[]) msg.obj;
				// construct a string from the buffer
				String writeMessage = new String(writeBuf);
				builderEsp.setMessage("Se ha escrito: " + writeMessage);
				builderEsp.create();
				builderEsp.show();

				// mConversationArrayAdapter.add("Me:  " + writeMessage);
				break;
			case MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;
				// construct a string from the valid bytes in the buffer
				String readMessage = new String(readBuf, 0, msg.arg1);
				builderEsp.setMessage(readMessage);
				builderEsp.create();
				builderEsp.show();
				break;
			}
		}
	};

	final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			Dispositivo d = new Dispositivo();
			String action = intent.getAction();
			// When discovery finds a device
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				// Get the BluetoothDevice object from the Intent
				BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				d.setName(device.getName());
				d.setMAC(device.getAddress());
				Log.d("Apareo", "Nombre:  " + d.getMAC());
				// Add the name and address to an array adapter to show in a
				// ListView
				if (!mArrayAdapter.contains(d)) {
					Log.d("Apareo", "Contains da: " + mArrayAdapter.contains(d));
					mArrayAdapter.add(d);
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						context, android.R.layout.simple_list_item_1,
						pasaAdaptadorAString(mArrayAdapter));
				listView.setAdapter(adapter);
			}
		}
	};
}