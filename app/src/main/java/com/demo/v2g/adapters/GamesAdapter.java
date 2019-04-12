package com.demo.v2g.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.demo.v2g.fragments.GameFragment;
import com.demo.v2g.model.maps.MapsContentLinks;

import com.demo.v2g.R;
import com.demo.v2g.model.maps.MapsContent;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private List<MapsContent> mapsContentList;
    private Context mContext;
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
        MapsContentLinks mapsContentLinks = new MapsContentLinks();


        holder.mapName.setText(name);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGameFragment(name,zoom,lat,lon);
            }
        });
    }

    private void initGameFragment(String name, int zoom, double lat, double lon, ) {
        FragmentTransaction fragmentTransaction = mFragment.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,GameFragment.newInstance(name,zoom,lat,lon));
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

/*
package com.demo.v2g.adapters;

        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import android.support.v4.app.FragmentActivity;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.util.Log;

        import com.demo.v2g.fragments.ChooseGameFragment;
        import com.demo.v2g.fragments.GameFragment;
        import com.demo.v2g.model.maps.MapsResponse;

        import com.demo.v2g.R;
        import com.demo.v2g.model.maps.Center;
        import com.demo.v2g.model.maps.MapsContent;

        import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private static String TAG = "GamesAdapter";

    private List<MapsContent> mapsContentList;
    private Context mContext;
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
        final MapsContent content = mapsContentList.get(position);
        String name = content.getName();
        final int zoom = content.getZoom();
        final double lat = content.getCenter().getLatitude();
        final double lon = content.getCenter().getLongitude();


        holder.mapName.setText(name);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGameFragment(zoom,lat,lon);
            }
        });
    }

    private void initGameFragment(int zoom, double lat, double lon) {
        Log.d(TAG,"initGameFragment");
        FragmentTransaction fragmentTransaction = mFragment.beginTransaction();
        //GameFragment gameFragment = GameFragment.newInstance(zoom,lat,lon);
        mFragment.beginTransaction().add(R.id.fragment_container,new GameFragment());
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

}*/

