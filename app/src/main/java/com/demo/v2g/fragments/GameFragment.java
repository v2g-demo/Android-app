package com.demo.v2g.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.v2g.Constants;
import com.demo.v2g.R;
import com.demo.v2g.model.objectsOnMap.ObjectsContent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;
import java.util.List;


public class GameFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = "GameFragment";

    SupportMapFragment mapFragment;
    private static CustomBottomSheepDialog dialog;
    private GoogleMap mMap;
    private String cityName;
    private int zoom;
    private  double lat;
    private double lon;
    private List<ObjectsContent> objectsContents;

    public GameFragment() {

    }

    public static GameFragment newInstance(String name, int zoom, double lat, double lon, List<ObjectsContent> objectsContents) {
        GameFragment fragment = new GameFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.NAME, name);
        bundle.putSerializable(Constants.ZOOM, zoom);
        bundle.putSerializable(Constants.CENTER_LAT, lat);
        bundle.putSerializable(Constants.CENTER_LON, lon);
        if(objectsContents != null) {
            bundle.putSerializable(Constants.MAP_OBJECTS, (Serializable) objectsContents);
        }
        //bundle.putSerializable(Constants.CENTER_LON, mapsContentLinks);
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.game_fragment, container, false);

        Bundle bundle = this.getArguments();

        if (bundle != null) {

            cityName = bundle.getString(Constants.NAME, "name");
            zoom = bundle.getInt(Constants.ZOOM, 0);
            lat = bundle.getDouble(Constants.CENTER_LAT, 0);
            lon =  bundle.getDouble(Constants.CENTER_LON, 0);
            objectsContents = (List<ObjectsContent>) bundle.getSerializable(Constants.MAP_OBJECTS);
        }


        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            ft.replace(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        Log.d("+++++", "lat => " + lat);
        Log.d("+++++", "lon => " + lon);
        LatLng sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in " + cityName));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoom));

        initMapObjectOnMap(googleMap);

        //Обработчик нажатия на Маркер
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                dialog = new CustomBottomSheepDialog();
                //Выбранный Маркер в центр экрана
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                //Показать BottonSheetDialog
                dialog.show(getFragmentManager(), marker.getTitle());
                return false;
            }
        });
    }

    private void initMapObjectOnMap(GoogleMap googleMap) {
        if(objectsContents == null) return;

        for(ObjectsContent objectOnMap : objectsContents) {
            Log.d(TAG,"objectOnMap !");
            double objLat = objectOnMap.getLocation().getLatitude();
            double objLon = objectOnMap.getLocation().getLongitude();
            String objTitle = objectOnMap.getName() + " " + objectOnMap.getType();

            LatLng newObjectOnMap = new LatLng(objLat, objLon);

            googleMap.addMarker(new MarkerOptions().position(newObjectOnMap).title(objTitle).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }
    }
}