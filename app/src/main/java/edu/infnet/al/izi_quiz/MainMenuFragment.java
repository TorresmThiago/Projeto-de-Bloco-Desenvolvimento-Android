package edu.infnet.al.izi_quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class MainMenuFragment extends Fragment {

    private ArrayList<MatchPlayed> matchesPlayed = new ArrayList<>();
    private HistoricAdapter historicAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_main, container,false);

        provisoryFillHistory(matchesPlayed, 10);

        mRecyclerView = view.findViewById(R.id.matchHistoryRecycleView);
        Log.d("MatchList", String.valueOf(mRecyclerView));
        historicAdapter = new HistoricAdapter(this.getContext(), matchesPlayed);
        mRecyclerView.setAdapter(historicAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        Button playButton = view.findViewById(R.id.MainMenuPlayButton);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.neutra_text_bold);
        playButton.setTypeface(typeface);

        return view;
    }

    public void provisoryFillHistory(ArrayList<MatchPlayed> matchesPlayed, int itens){
        for (int i = 0; i < itens; i++){
            MatchPlayed newMatch = new MatchPlayed("16, fev. 2018", 1);
            matchesPlayed.add(newMatch);
        }
    }


}
