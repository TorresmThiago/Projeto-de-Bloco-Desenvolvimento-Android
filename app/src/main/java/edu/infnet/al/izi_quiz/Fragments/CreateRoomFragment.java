package edu.infnet.al.izi_quiz.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.Assets.PlayersList.Player;
import edu.infnet.al.izi_quiz.Assets.PlayersList.PlayerListAdapter;
import edu.infnet.al.izi_quiz.R;

public class CreateRoomFragment extends Fragment {

    private String PLAYERS_ROOT_KEY = "players";
    private String MATCHES_ROOT_KEY = "Matches";
    private String ROOM_STATE = "RoomState";
    private String PLAYER_KEY;
    private String ROOM_KEY;
    private String PLAYER_NAME;
    private boolean PLAYER_GUEST;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRootReference;
    private DatabaseReference matchesRootReference;
    private DatabaseReference playersRootReference;

    private ArrayList<Player> playerList = new ArrayList<>();
    private PlayerListAdapter playerListAdapter;
    private RecyclerView mRecyclerView;

    private TextView roomKeyTextView;
    private Button startMatchButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_room, container,false);

        roomKeyTextView = view.findViewById(R.id.createRoomKey);
        startMatchButton = view.findViewById(R.id.createRoomStartMatch);

        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRootReference = firebaseDatabase.getReference();
        matchesRootReference = mRootReference.child(MATCHES_ROOT_KEY);

        if (getArguments() != null) {
            ROOM_KEY = getArguments().getString("key");
            PLAYER_NAME = getArguments().getString("name");
            PLAYER_GUEST = getArguments().getBoolean("guest");
        }

        joinRoom(PLAYER_GUEST);

        playersRootReference = matchesRootReference.child(ROOM_KEY).child(PLAYERS_ROOT_KEY);
        playersRootReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    updatePlayerList((Map<String,Object>) dataSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(ROOM_KEY, "Erro ao atualizar a lista de jogadores");
            }
        });

        //RecycleView
        mRecyclerView = view.findViewById(R.id.createRoomPlayers);
        updateRecycleView();
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

        if (PLAYER_GUEST) {
            matchesRootReference.child(ROOM_KEY).child(PLAYERS_ROOT_KEY).child(PLAYER_KEY).setValue(null);
        } else {
            matchesRootReference.child(ROOM_KEY).setValue(null);
        }

    }

    private void joinRoom(boolean playerGuest) {
        if (!playerGuest) {
            String databaseKey = matchesRootReference.push().getKey();
            char[] letters = databaseKey.toCharArray();
            StringBuilder compressedKey = new StringBuilder();

            for (int i = 1; i < letters.length; i += 3){
                compressedKey.append(letters[i]);
            }

            ROOM_KEY = compressedKey.toString();
            matchesRootReference.child(ROOM_KEY).child(ROOM_STATE).setValue("Open");
        }

        Player player = new Player(PLAYER_NAME, 0  ,3 ,3);
        playerList.add(player);
        PLAYER_KEY = mRootReference.push().getKey();

        matchesRootReference.child(ROOM_KEY).child(PLAYERS_ROOT_KEY).child(PLAYER_KEY).setValue(player);
        matchesRootReference.child(ROOM_KEY).child("NumberOfPlayers").setValue(playerList.size() + 1);
        roomKeyTextView.setText(ROOM_KEY);
    }

    private void updatePlayerList(Map<String,Object> users) {
        playerList = new ArrayList<>();

        for(Map.Entry<String, Object> entry : users.entrySet()) {
            Map singleUser = (Map) entry.getValue();
            Player newPlayer = new Player((String) singleUser.get("name"), (long) singleUser.get("points"),(long) singleUser.get("pwrUpShuffle"),(long) singleUser.get("pwrUpFadeIn"));
            playerList.add(newPlayer);
        }

        updateRecycleView();

    }

    private void updateRecycleView() {
        playerListAdapter = new PlayerListAdapter(this.getContext(), playerList);
        mRecyclerView.setAdapter(playerListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        if (!PLAYER_GUEST && playerList.size() >= 2){
            startMatchButton.setVisibility(View.VISIBLE);
        } else {
            startMatchButton.setVisibility(View.INVISIBLE);
        }
    }

}
