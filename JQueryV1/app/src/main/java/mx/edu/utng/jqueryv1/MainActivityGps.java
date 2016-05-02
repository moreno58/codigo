package mx.edu.utng.jqueryv1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivityGps extends Activity implements View.OnClickListener {

    TextView messageTextView;
    TextView messageTextView2;
    Button button;
    TextView time;
    private final int TIEMPO_ESPERA = 60000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gps);
        messageTextView = (TextView) findViewById(R.id.message_id);
        messageTextView2 = (TextView) findViewById(R.id.message_id2);
        time = (TextView) findViewById(R.id.message_time);
        //esperar();
        /* Use the LocationManager class to obtain GPS locations */
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener mlocListener = new MyLocationListener();
        mlocListener.setMainActivity(this);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                (LocationListener) mlocListener);

        button = (Button) findViewById(R.id.btn_ver_mapa);
    }

    public void esperar() {
        new CountDownTimer(TIEMPO_ESPERA, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Esperar...: " + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                time.setText("Listo");
                finish();
                startActivity(new Intent(MainActivityGps.this, MapsActivity.class));
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    public void setLocation(Location loc) {
        //Obtener la direcci�n de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address address = list.get(0);
                    messageTextView2.setText("Mi direcci�n es: \n"
                            + address.getAddressLine(0));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String lat = "", lon = "";

    @Override
    public void onClick(View v) {

    }

    /* Class My Location Listener */
    public class MyLocationListener implements LocationListener {
        MainActivityGps mainActivity;

        public MainActivityGps getMainActivity() {
            return mainActivity;
        }

        public void setMainActivity(MainActivityGps mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este m�todo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la detecci�n de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();
            String Text = "Mi ubicaci�n actual es: " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();

            lat = String.valueOf(loc.getLatitude());
            lon = String.valueOf(loc.getLongitude());
            apagar();
            finish();
            startActivity(new Intent(MainActivityGps.this, MapsActivity.class));
        }

        private void apagar() {
            String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if (!provider.contains("gps")) {
                final Intent poke = new Intent();
                poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
                poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
                poke.setData(Uri.parse("3"));
                sendBroadcast(poke);
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este m�todo se ejecuta cuando el GPS es desactivado
            messageTextView.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este m�todo se ejecuta cuando el GPS es activado
            messageTextView.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Este m�todo se ejecuta cada vez que se detecta un cambio en el
            // status del proveedor de localizaci�n (GPS)
            // Los diferentes Status son:
            // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
            // TEMPORARILY_UNAVAILABLE -> Temp�ralmente no disponible pero se
            // espera que este disponible en breve
            // AVAILABLE -> Disponible
        }

    }/* End of Class MyLocationListener */

}
