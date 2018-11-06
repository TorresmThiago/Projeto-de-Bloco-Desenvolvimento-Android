package edu.infnet.al.izi_quiz;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class HistoricAdapter extends RecyclerView.Adapter<HistoricAdapter.MatchPlayedHolder> {

    private final ArrayList<MatchPlayed> matchesPlayed;
    private LayoutInflater mInflater;

    public HistoricAdapter(Context context, ArrayList<MatchPlayed> matchesPlayed) {
        mInflater = LayoutInflater.from(context);
        this.matchesPlayed = matchesPlayed;
    }

    class MatchPlayedHolder extends RecyclerView.ViewHolder {
        public final Button playerPosition;
        public final Button matchInfo;
        final HistoricAdapter mAdapter;

        public MatchPlayedHolder(@NonNull View itemView, HistoricAdapter mAdapter) {
            super(itemView);
            this.playerPosition = itemView.findViewById(R.id.matchPlayedPosition);
            this.matchInfo = itemView.findViewById(R.id.matchPlayedBackground);
            this.mAdapter = mAdapter;
        }
    }

    @NonNull
    @Override
    public HistoricAdapter.MatchPlayedHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.asset_historic_matchplayed, viewGroup, false);
        return new MatchPlayedHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricAdapter.MatchPlayedHolder matchPlayedHolder, int i) {
        matchPlayedHolder.matchInfo.setText("16, fev. 2018 " + i);
    }



    @Override
    public int getItemCount() {
        return matchesPlayed.size();
    }
}
