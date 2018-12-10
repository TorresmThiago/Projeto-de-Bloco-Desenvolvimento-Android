package edu.infnet.al.izi_quiz.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.Assets.PlayersList.Player;
import edu.infnet.al.izi_quiz.R;

public class ResultsActivity extends AppCompatActivity {

    private ArrayList<Player> playerList;
    private AdView mAdView;
    String ROOM_KEY;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRootReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        ROOM_KEY = intent.getStringExtra("ROOM_KEY");

        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRootReference = firebaseDatabase.getReference();

        DatabaseReference playersRootReference = mRootReference.child("Matches").child(ROOM_KEY).child("players");
        playersRootReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    getPlayers((Map<String,Object>) dataSnapshot.getValue());
                }
            }

            private void getPlayers(Map<String, Object> users) {
                playerList = new ArrayList<>();
                for(Map.Entry<String, Object> entry : users.entrySet()) {
                    Map singleUser = (Map) entry.getValue();
                    Player newPlayer = new Player((String) singleUser.get("name"), (long) singleUser.get("points"),(long) singleUser.get("pwrUpScramble"),(long) singleUser.get("pwrUpFadeIn"), (String) singleUser.get("key"));
                    playerList.add(newPlayer);
                }

                comparePoints();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(ROOM_KEY, "Erro ao receber jogadores vencedores");
            }
        });


        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void setContentView(View view)  {
        super.setContentView(view);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));
    }

    @Override
    public void onBackPressed() {
        returnToMenu();
    }

    private void comparePoints() {
        int players = playerList.size();
        ArrayList<TextView> textViews = new ArrayList<>();
        for (int i = 1; i <= players; i ++) {
            TextView textView = findViewById(getResources().getIdentifier("resultsPlayer_" + i, "id", this.getPackageName()));
            textViews.add(textView);
        }

        for (int i = 0; i < textViews.size(); i++){
            int currentPoints = 0;
            int chosen = 0;
            for (int j = 0; j < playerList.size(); j++){
                if (playerList.get(j).getPoints() > currentPoints){
                    chosen = j;
                    currentPoints = (int) playerList.get(j).getPoints();
                }
            }
            textViews.get(i).setText(playerList.get(chosen).getName());
            playerList.remove(chosen);
        }

    }

    public void returnToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void returnToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
