package com.demo.v2g.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demo.v2g.App;
import com.demo.v2g.R;
import com.demo.v2g.adapters.GamesAdapter;
import com.demo.v2g.model.Content;
import com.demo.v2g.model.MapsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseGameFragment extends Fragment {

    private static String TAG = "ChooseGameFragment";
    RecyclerView recyclerView;

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
        recyclerView = v.findViewById(R.id.games_list);
        getAllGames();
        return v;
    }

    private void getAllGames() {
        App.getV2gApi().getAllMaps().enqueue(new Callback<MapsResponse>() {
            @Override
            public void onResponse(Call<MapsResponse> call, Response<MapsResponse> response) {
                Log.d(TAG,"getAllGames() => onResponse");

                if(response.body().getContent().size() != 0) {
                    List<Content>  contents = response.body().getContent();
                    createGameCards(contents);
                } else {
                    Log.d(TAG,"There're no games!");
                    Toast.makeText(getContext(), "There're no games!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MapsResponse> call, Throwable t) {

            }
        });
    }

    private void createGameCards(List<Content> contents) {
        GamesAdapter gamesAdapter = new GamesAdapter(getFragmentManager(),contents);
        recyclerView.setAdapter(gamesAdapter);
    }

}
