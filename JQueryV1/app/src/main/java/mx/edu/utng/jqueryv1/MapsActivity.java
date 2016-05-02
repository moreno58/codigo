package mx.edu.utng.jqueryv1;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng utng = new LatLng(21.167911, -100.930672);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(utng)
                .zoom(19)
                .bearing(45)
                .build();
        CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.animateCamera(camUpd3);
        mMap.addMarker(new MarkerOptions().position(utng).title("UTNG"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(utng));
    }
}
