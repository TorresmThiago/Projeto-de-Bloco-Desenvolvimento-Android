package edu.infnet.al.izi_quiz;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainMenuFragment extends Fragment implements MatchPlayedItemClick {

    private ArrayList<MatchPlayed> matchesPlayed = new ArrayList<>();
    private HistoricAdapter historicAdapter;
    private RecyclerView mRecyclerView;

    TextView matchPlayedModalDate;
    TextView matchPlayedModalPlayerPosition;

    Dialog selectedMatchPlayed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_main, container,false);

        provisoryFillHistory(matchesPlayed, 10);

        selectedMatchPlayed = new Dialog(this.getContext());

        mRecyclerView = view.findViewById(R.id.matchHistoryRecycleView);
        historicAdapter = new HistoricAdapter(this.getContext(), matchesPlayed, this);
        mRecyclerView.setAdapter(historicAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getContext().getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup) this.getView());
    }

    public void provisoryFillHistory(ArrayList<MatchPlayed> matchesPlayed, int itens){
        for (int i = 0; i < itens; i++){
            MatchPlayed newMatch = new MatchPlayed("16, fev. 2018", i);
            matchesPlayed.add(newMatch);
        }
    }


    @Override
    public void onMatchClick(Object object) {
        MatchPlayed match = (MatchPlayed) object;


        selectedMatchPlayed.setContentView(R.layout.asset_modal_matchplayed);
        selectedMatchPlayed.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        String MatchDate = match.getMatchDate();
        String MatchPosition= Integer.toString(match.getMatchPosition());


        matchPlayedModalDate = selectedMatchPlayed.getWindow().findViewById(R.id.matchPlayedModalDate);
        matchPlayedModalPlayerPosition = selectedMatchPlayed.getWindow().findViewById(R.id.matchPlayedModalPlayerPosition);

        matchPlayedModalDate.setText(MatchDate);
        matchPlayedModalPlayerPosition.setText(MatchPosition);

        selectedMatchPlayed.show();
    }
}
