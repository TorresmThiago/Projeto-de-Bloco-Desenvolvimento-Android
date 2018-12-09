package edu.infnet.al.izi_quiz.Fragments;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.infnet.al.izi_quiz.Activities.MatchActivity;
import edu.infnet.al.izi_quiz.Assets.FirebaseData.Votes;
import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.R;

public class PowerUpFragment extends Fragment{

    public MatchActivity matchActivity;

    int SELECTED_THEME = -1;
    int SELECTED_POWERUP = -1;
    String MATCHES_ROOT_KEY = "Matches";

    String ROOM_KEY;
    String PLAYER_KEY;
    String CURRENT_ROUND;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRootReference;
    private DatabaseReference roomRootReference;
    private DatabaseReference votesRootReference;
//    private DatabaseReference powerUpsRootReference;

    ProgressBar mProgressBar;
    String[] questionThemes = {"world", "tv", "animal"};
    String[] powerUps = {"scramble", "fadein"};

    TextView fadeInChargesTextView;
    TextView scrambleChargesTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_match_powerup, container,false);

        matchActivity = (MatchActivity) getActivity();

        fadeInChargesTextView = view.findViewById(R.id.powerUpChoiceFadeInCharges);
        scrambleChargesTextView = view.findViewById(R.id.powerUpChoiceScrambleCharges);

        mProgressBar = view.findViewById(R.id.questionsProgressbar);
        mProgressBar.setProgress(0);

        if (getArguments() != null) {
            ROOM_KEY = getArguments().getString("ROOM_KEY");
            PLAYER_KEY = getArguments().getString("PLAYER_KEY");
            CURRENT_ROUND = "Round_" + getArguments().getInt("CURRENT_ROUND");
        }

        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRootReference = firebaseDatabase.getReference();
        roomRootReference = mRootReference.child(MATCHES_ROOT_KEY).child(ROOM_KEY);
        votesRootReference = roomRootReference.child(CURRENT_ROUND).child("votes");
//        powerUpsRootReference = roomRootReference.child(CURRENT_ROUND).child("powerUps");

        Votes votes = new Votes(0,0,0);
        votesRootReference.setValue(votes);

        //Set all buttons to selectButton function onClick
        for (int i = 0; i < questionThemes.length; i++) {
            ImageButton themeButton = view.findViewById(getResources().getIdentifier("questionTheme" + questionThemes[i] + "Icon", "id", this.getContext().getPackageName()));
            themeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View button) {
                    selectTheme(button, view);
                }
            });
        }

        for (int i = 0; i < powerUps.length; i++) {
            ImageButton themeButton = view.findViewById(getResources().getIdentifier("powerUpChoice" + powerUps[i] + "Icon", "id", this.getContext().getPackageName()));
            themeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View button) {
                    selectPowerUp(button, view);
                }
            });
        }

        //Updates how many charges each power up has
        updateSelectedPowerUp();

        //ProgressBar Animation
        ObjectAnimator animation = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 100);
        animation.setDuration(5000);
        animation.setInterpolator(new LinearInterpolator());

        //Countdown to handle right or wrong answers
        CountDownTimer mCountDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                //matchActivity.goToQuestionsFragment();
            }
        };

        mCountDownTimer.start();
        animation.start();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getContext().getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup) this.getView());
    }

    public void selectTheme(View button, View view) {
        for (int i = 0; i < questionThemes.length; i++) {
            ImageButton themeButton = view.findViewById(getResources().getIdentifier("questionTheme" + questionThemes[i] + "Icon", "id", this.getContext().getPackageName()));
            Drawable drawable;
            if (themeButton.equals(button)){
                drawable = getResources().getDrawable(getResources().getIdentifier("ic_button_theme_" + questionThemes[i] + "_selected", "drawable", this.getContext().getPackageName()));
                SELECTED_THEME = i;
            } else {
                drawable = getResources().getDrawable(getResources().getIdentifier("ic_button_theme_" + questionThemes[i], "drawable", this.getContext().getPackageName()));
            }
            themeButton.setBackground(drawable);
        }
    }

    private void registerThemeVote() {
        DatabaseReference registerVote = votesRootReference.child(questionThemes[SELECTED_THEME]);
        registerVote.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    register(dataSnapshot);
                }
            }

            private void register(DataSnapshot dataSnapshot) {
                long votes = (long) dataSnapshot.getValue();
                votesRootReference.child(questionThemes[SELECTED_THEME]).setValue(votes + 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(ROOM_KEY, "Problemas ao registrar o voto");
            }
        });
    }

    public void selectPowerUp(View button, View view) {
        for (int i = 0; i < powerUps.length; i++) {
            ImageButton powerUpButton = view.findViewById(getResources().getIdentifier("powerUpChoice" + powerUps[i] + "Icon", "id", this.getContext().getPackageName()));
            Drawable drawable;
            if (powerUpButton.equals(button)){
                drawable = getResources().getDrawable(getResources().getIdentifier("ic_button_powerup_" + powerUps[i] + "_selected", "drawable", this.getContext().getPackageName()));
                SELECTED_POWERUP = i;
            } else {
                drawable = getResources().getDrawable(getResources().getIdentifier("ic_button_powerup_" + powerUps[i], "drawable", this.getContext().getPackageName()));
            }
            powerUpButton.setBackground(drawable);
        }
    }

    private void updateSelectedPowerUp() {
        final DatabaseReference playerRoot = roomRootReference.child("players").child(PLAYER_KEY);
        playerRoot.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    register(dataSnapshot);
                    updateTextView(dataSnapshot);
                }
            }

            private void updateTextView(DataSnapshot dataSnapshot) {
                long fadeInCharges = (long) dataSnapshot.child("pwrUpFadeIn").getValue();
                long scrambleCharges = (long) dataSnapshot.child("pwrUpScramble").getValue();
                fadeInChargesTextView.setText("x" + Long.toString(fadeInCharges));
                scrambleChargesTextView.setText("x" + Long.toString(scrambleCharges));
            }

            private void register(DataSnapshot dataSnapshot) {
                if (SELECTED_POWERUP == 1) {
                    long charges = (long) dataSnapshot.child("pwrUpFadeIn").getValue();
                    playerRoot.child("pwrUpFadeIn").setValue(charges - 1);
                } else if (SELECTED_POWERUP == 0) {
                    long charges = (long) dataSnapshot.child("pwrUpScramble").getValue();
                    playerRoot.child("pwrUpScramble").setValue(charges - 1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(ROOM_KEY, "Problemas ao cadastrar o powerUp");
            }
        });
    }
}