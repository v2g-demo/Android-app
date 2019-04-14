package com.demo.v2g.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.demo.v2g.App;
import com.demo.v2g.fragments.GameFragment;

import com.demo.v2g.R;
import com.demo.v2g.model.maps.MapsContent;
import com.demo.v2g.model.objectsOnMap.ObjectsContent;
import com.demo.v2g.model.objectsOnMap.ObjectsOnMapResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private final String TAG = "GamesAdapter";

    private List<MapsContent> mapsContentList;
    private FragmentManager mFragment;

    public GamesAdapter(FragmentManager fragment, List<MapsContent> resultEpisodes) {
        mFragment = fragment;
        this.mapsContentList = resultEpisodes;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.game_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MapsContent mapsContent = mapsContentList.get(position);
        final String name = mapsContent.getName();
        final int zoom = mapsContent.getZoom();
        final double lat = mapsContent.getCenter().getLatitude();
        final double lon = mapsContent.getCenter().getLongitude();


        holder.mapName.setText(name);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllObjectForCurrentMap(mapsContent.getId().toString(), new onMapObjectsCallback() {
                    @Override
                    public void objectsContentsResult(List<ObjectsContent> objectsContents) {
                        if(objectsContents != null) {
                            initGameFragment(name,zoom,lat,lon,objectsContents);
                        } else {
                            initGameFragment(name,zoom,lat,lon,null);
                        }
                    }
                });
            }
        });
    }

    private void getAllObjectForCurrentMap(String mapId, final onMapObjectsCallback callback) {
        App.getV2gApi().getAllObject(mapId).enqueue(new Callback<ObjectsOnMapResponse>() {
            @Override
            public void onResponse(Call<ObjectsOnMapResponse> call, Response<ObjectsOnMapResponse> response) {
                Log.d(TAG,"getAllObjectForCurrentMap => onResponse response size => " + response.body().getContent().size());
                callback.objectsContentsResult(response.body().getContent());

            }

            @Override
            public void onFailure(Call<ObjectsOnMapResponse> call, Throwable t) {
                Log.d(TAG,"onFailure");
            }
        });


    }


    private void initGameFragment(String name, int zoom, double lat, double lon, List<ObjectsContent> objectsContents) {
        FragmentTransaction fragmentTransaction = mFragment.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,GameFragment.newInstance(name,zoom,lat,lon, objectsContents));
        fragmentTransaction.commit();
    }


    @Override
    public int getItemCount() {
        return mapsContentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mapName;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mapName = itemView.findViewById(R.id.game_map_name);
            cardView = itemView.findViewById(R.id.game_card_view);
        }
    }

}