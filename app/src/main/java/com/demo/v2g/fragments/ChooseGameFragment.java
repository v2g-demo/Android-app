package com.demo.v2g.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.v2g.App;
import com.demo.v2g.R;
import com.demo.v2g.model.MapsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseGameFragment extends Fragment {

    private static String TAG = "ChooseGameFragment";

/*    private void initMainFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,new GameFragment());
        fragmentTransaction.commit();
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.choose_fragment, container, false);
        getAllGames();
        return v;
    }

    private void getAllGames() {
        App.getV2gApi().getAllMaps().enqueue(new Callback<MapsResponse>() {
            @Override
            public void onResponse(Call<MapsResponse> call, Response<MapsResponse> response) {
                Log.d(TAG,"getAllGames() => onResponse");

                if(response.body().getContent().size() != 0) {

                }
            }

            @Override
            public void onFailure(Call<MapsResponse> call, Throwable t) {

            }
        });
    }
}
