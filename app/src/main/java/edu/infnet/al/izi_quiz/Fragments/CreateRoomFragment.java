package edu.infnet.al.izi_quiz.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.Assets.PlayersList.Player;
import edu.infnet.al.izi_quiz.Assets.PlayersList.PlayerListAdapter;
import edu.infnet.al.izi_quiz.R;

public class CreateRoomFragment extends Fragment {

    private DatabaseReference mDatabase;

    private ArrayList<Player> playerList = new ArrayList<>();
    private PlayerListAdapter playerListAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_room, container,false);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Matches").child("Room").setValue("ABC123");

        Player player = new Player("Thiago");
        playerList.add(player);

        mRecyclerView = view.findViewById(R.id.createRoomPlayers);
        playerListAdapter = new PlayerListAdapter(this.getContext(), playerList);
        mRecyclerView.setAdapter(playerListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getContext().getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup) this.getView());

    }

}
