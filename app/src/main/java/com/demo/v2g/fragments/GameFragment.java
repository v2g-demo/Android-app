package com.demo.v2g.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.v2g.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class GameFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    private static CustomBottomSheepDialog dialog;
    private GoogleMap mMap;

    public GameFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.game_fragment, container, false);
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
        LatLng sydney = new LatLng(-34, 151);
        LatLng canderra = new LatLng(-35.309723, 149.124693);
        LatLng newcastle = new LatLng(-32.928943, 151.781419);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(canderra).title("Marker in Canderra"));
        mMap.addMarker(new MarkerOptions().position(newcastle).title("Marker in Newcastle"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 5));

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
}