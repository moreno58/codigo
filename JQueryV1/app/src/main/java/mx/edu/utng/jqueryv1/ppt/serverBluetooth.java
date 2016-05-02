package mx.edu.utng.jqueryv1.ppt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class serverBluetooth extends Thread {
	private final BluetoothServerSocket mmServerSocket;
	private BluetoothAdapter mBluetoothAdapter;
	private UUID MyUUID;
    InputStream ins;
    OutputStream ons;
    private Handler mHandler;
	 
    public serverBluetooth(Context context, UUID MyUUID, BluetoothAdapter mBluetoothAdapter, Handler handler) {
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSocket is final
    	this.mHandler = handler;
    	this.MyUUID = MyUUID;
    	this.mBluetoothAdapter = mBluetoothAdapter;
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code
            tmp = this.mBluetoothAdapter.listenUsingRfcommWithServiceRecord(this.mBluetoothAdapter.getName(), this.MyUUID);
        } catch (IOException e) {
        	Log.e(getName(), "Error Adaptador Bluetooth");
        }
        mmServerSocket = tmp;
    }
    
 
    public void run() {
        BluetoothSocket socket = null;
        byte[] buffer = new byte[1024];
        int bytes;
        // Keep listening until exception occurs or a socket is returned
        while (true) {
            try {
                socket = mmServerSocket.accept();
                mmServerSocket.close(); //para solo una conexion
            } catch (IOException e) {
                break;
            }
            // If a connection was accepted
            if (socket != null) {
            	try {
	            	ins = socket.getInputStream();
                    bytes = ins.read(buffer);
                    // Send the obtained bytes to the UI Activity
                    mHandler.sendMessage(Message.obtain(mHandler,Bluetooth.MESSAGE_READ,bytes,-1,buffer));
                    socket.close();
				} catch (IOException e1) {
		            Log.d("Error Socket","Destruyendo Archivos del sistema");
				}
            }
        }
    }
 
    /** Will cancel the listening socket, and cause the thread to finish */
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) { }
    }
}
