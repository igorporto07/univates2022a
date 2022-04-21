package br.univates.meuapp.foradeuso;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.univates.meuapp.R;
import br.univates.meuapp.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Marcadores
        LatLng univates = new LatLng(-29.44438457423918, -51.95651341850893);
        mMap.addMarker(new MarkerOptions().position(univates).title("UNIVATES"));

        LatLng casa = new LatLng(-29.466947701294117, -51.97562653734662);
        mMap.addMarker(new MarkerOptions().position(casa).title("CASA"));

        LatLng trabalho = new LatLng(-29.463918172121517, -51.96487825929225);
        mMap.addMarker(new MarkerOptions().position(trabalho).title("TRABALHO"));


        //Ferramentas
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(univates,14));//abre mapa com zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);//Adiciona botão de zomm no mapa
        mMap.getUiSettings().setCompassEnabled(true);//Adiciona uma bussola no mapa


    }


}