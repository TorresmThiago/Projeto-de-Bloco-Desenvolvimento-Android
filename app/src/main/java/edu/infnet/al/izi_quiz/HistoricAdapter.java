package edu.infnet.al.izi_quiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class HistoricAdapter extends RecyclerView.Adapter<HistoricAdapter.MatchPlayedHolder> {

    private final ArrayList<MatchPlayed> matchesPlayed;
    private LayoutInflater mInflater;

    private static final String MATCH_DATE = "matchDate";
    private static final String MATCH_POSITION = "matchPosition";

    Context context;
    Intent intent;



    public HistoricAdapter(Context context, ArrayList<MatchPlayed> matchesPlayed) {
        mInflater = LayoutInflater.from(context);
        this.matchesPlayed = matchesPlayed;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoricAdapter.MatchPlayedHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.asset_historic_matchplayed, viewGroup, false);

        FontChangeCrawler fontChanger = new FontChangeCrawler(context.getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup) mItemView);

        return new MatchPlayedHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricAdapter.MatchPlayedHolder matchPlayedHolder, int i) {
        matchPlayedHolder.matchInfo.setText("16, fev. 2018 " + i);
        matchPlayedHolder.matchInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                System.out.println("Called button");

            }
        });
    }

    @Override
    public int getItemCount() {
        return matchesPlayed.size();
    }

    class MatchPlayedHolder extends RecyclerView.ViewHolder {
        public final Button playerPosition;
        public final Button matchInfo;

        public MatchPlayedHolder(@NonNull View itemView) {
            super(itemView);

            this.playerPosition = itemView.findViewById(R.id.matchPlayedPosition);
            this.matchInfo = itemView.findViewById(R.id.matchPlayedBackground);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    intent = new Intent(context, ResultsActivity.class);
                    intent.putExtra(MATCH_DATE, matchesPlayed.get(getLayoutPosition()).getMatchDate());
                    intent.putExtra(MATCH_POSITION, matchesPlayed.get(getLayoutPosition()).getMatchPosition());
                    context.startActivity(intent);
                }
            });
        }
    }
}
