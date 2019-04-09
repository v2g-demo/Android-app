package com.demo.v2g;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.demo.v2g.fragments.ChooseGameFragment;
import com.demo.v2g.fragments.GameFragment;
import com.demo.v2g.model.MapsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends FragmentActivity {

    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testRequest();
        initMainFragment();
    }

    private void testRequest() {
        Log.d(TAG,"testRequest");
        Call<MapsResponse> maps = App.getV2gApi().getAllMaps();

        maps.enqueue(new Callback<MapsResponse>() {

            @Override
            public void onResponse(Call<MapsResponse> call, Response<MapsResponse> response) {
                Log.d(TAG,"testRequest");
                Log.d(TAG,"response.body().getContent().get(0).getCenter() => " + response.body().getContent().get(0).getCenter());
            }

            @Override
            public void onFailure(Call<MapsResponse> call, Throwable t) {

            }
        });
    }

    private void initMainFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,new ChooseGameFragment());
        fragmentTransaction.commit();
    }


/*    private void initMainFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,new GameFragment());
        fragmentTransaction.commit();
    }*/

}
