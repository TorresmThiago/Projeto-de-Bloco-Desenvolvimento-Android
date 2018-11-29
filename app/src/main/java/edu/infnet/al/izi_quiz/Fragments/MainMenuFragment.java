package edu.infnet.al.izi_quiz.Fragments;

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

import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.Assets.MatchPlayed.HistoricAdapter;
import edu.infnet.al.izi_quiz.Assets.MatchPlayed.MatchPlayed;
import edu.infnet.al.izi_quiz.Assets.MatchPlayed.MatchPlayedItemClick;
import edu.infnet.al.izi_quiz.R;

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

            //Need to receive: Date of the match, Name of the players in match, Position of each player in the match

            MatchPlayed newMatch = new MatchPlayed(i + ", fev. 2018",  1 + (int) (Math.random() * 4));
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
