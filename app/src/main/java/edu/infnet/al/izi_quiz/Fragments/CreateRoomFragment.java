package edu.infnet.al.izi_quiz.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.Assets.PlayersList.Player;
import edu.infnet.al.izi_quiz.Assets.PlayersList.PlayerListAdapter;
import edu.infnet.al.izi_quiz.Assets.PowerUps.Shuffle;
import edu.infnet.al.izi_quiz.R;

public class CreateRoomFragment extends Fragment {

    private String PLAYERS_ROOT_KEY = "players";
    private String PLAYER_KEY;
    private String ROOM_KEY;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRootReference;
    private DatabaseReference matches;


    private ArrayList<Player> playerList = new ArrayList<>();
    private PlayerListAdapter playerListAdapter;
    private RecyclerView mRecyclerView;

    private TextView roomKeyTextView;

    private String joinedRoomKey;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_room, container,false);

        roomKeyTextView = view.findViewById(R.id.createRoomKey);

        if (getArguments() != null) {
            joinedRoomKey = getArguments().getString("key");
        }

        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRootReference = firebaseDatabase.getReference();
        matches = mRootReference.child("Matches");

        //RecycleView
        mRecyclerView = view.findViewById(R.id.createRoomPlayers);
        playerListAdapter = new PlayerListAdapter(this.getContext(), playerList);
        mRecyclerView.setAdapter(playerListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        if (joinedRoomKey == null) {
            createRoom(matches);
        } else {
            joinRoom(joinedRoomKey);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getContext().getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup) this.getView());

    }

    @Override
    public void onPause() {
        super.onPause();

        if (joinedRoomKey != null) {
            matches.child(joinedRoomKey).child(PLAYERS_ROOT_KEY).child(PLAYER_KEY).setValue(null);
        } else {
            matches.child(ROOM_KEY).setValue(null); //LTGZr2t
        }

    }

    private void joinRoom(String joinedRoomKey){
        Player player = new Player("Saulo", 0  ,0 ,0);
        playerList.add(player);

        PLAYER_KEY = mRootReference.push().getKey();

        matches.child(joinedRoomKey).child(PLAYERS_ROOT_KEY).child(PLAYER_KEY).setValue(player);
        roomKeyTextView.setText(joinedRoomKey);
    }

    private void createRoom(DatabaseReference mDatabase) {
        String databaseKey = mDatabase.push().getKey();
        char[] letters = databaseKey.toCharArray();
        StringBuilder compressedKey = new StringBuilder();

        for (int i = 1; i < letters.length; i += 3){
            compressedKey.append(letters[i]);
        }

        Player player = new Player("Thiago", 0  ,0 ,0);
        playerList.add(player);
        PLAYER_KEY = mRootReference.push().getKey();

        ROOM_KEY = compressedKey.toString();

        mDatabase.child(ROOM_KEY).child(PLAYERS_ROOT_KEY).child(PLAYER_KEY).setValue(player);
        roomKeyTextView.setText(ROOM_KEY);
    }

}
