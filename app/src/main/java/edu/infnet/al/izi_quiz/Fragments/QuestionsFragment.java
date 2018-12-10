package edu.infnet.al.izi_quiz.Fragments;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import edu.infnet.al.izi_quiz.Activities.MatchActivity;
import edu.infnet.al.izi_quiz.Activities.ResultsActivity;
import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.Assets.PowerUps.FadeIn;
import edu.infnet.al.izi_quiz.Assets.PowerUps.Scramble;
import edu.infnet.al.izi_quiz.R;

public class QuestionsFragment extends Fragment {

    Scramble scramblePowerUp = new Scramble();
    FadeIn fadeInPowerUp = new FadeIn();
    TextView question;

    ProgressBar mProgressBar;
    Boolean GAME_ON;
    int correct;
    String idButtonSelected;

    String ROOM_KEY;
    String PLAYER_KEY;
    String CURRENT_ROUND;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mRootReference;
    private DatabaseReference roomRootReference;
    private DatabaseReference votesRootReference;
    private DatabaseReference powerUpsReference;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_match_questions, container, false);

        question = view.findViewById(R.id.questionBox);

        if (getArguments() != null) {
            ROOM_KEY = getArguments().getString("ROOM_KEY");
            PLAYER_KEY = getArguments().getString("PLAYER_KEY");
            CURRENT_ROUND = "Round_" + getArguments().getInt("CURRENT_ROUND");
        }

        //Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        mRootReference = firebaseDatabase.getReference();
        roomRootReference = mRootReference.child("Matches").child(ROOM_KEY);
        votesRootReference = roomRootReference.child(CURRENT_ROUND).child("votes");

        mProgressBar = view.findViewById(R.id.questionsProgressbar);
        mProgressBar.setProgress(0);

        GAME_ON = true;
        correct =  1 + (int) (Math.random() * 4);

        powerUpsReference = roomRootReference.child(CURRENT_ROUND).child("powerUps").child(PLAYER_KEY);
        powerUpsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    applyPowerUps(dataSnapshot);
                }
            }

            private void applyPowerUps(DataSnapshot dataSnapshot) {
                boolean scramble = dataSnapshot.child("scramble").exists() && (boolean) dataSnapshot.child("scramble").getValue();
                boolean fadein = dataSnapshot.child("fadein").exists() && (boolean) dataSnapshot.child("fadein").getValue();

                if (scramble){
                    scramblePowerUp.scrambleTextView(question);
                }
                if (fadein){
                    fadeInPowerUp.fadeInTextView(question);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(ROOM_KEY, "Erro ao executar powerUps");
            }
        });

        //Set all buttons to selectButton function onClick
        for (int i = 1; i <= 4; i++) {
            Button button = view.findViewById(getResources().getIdentifier("questionOption_" + i, "id", this.getContext().getPackageName()));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View button) {
                    selectOption(button, view);
                }
            });
        }

        //ProgressBar Animation
        ObjectAnimator animation = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 100);
        animation.setDuration(5000);
        animation.setInterpolator(new LinearInterpolator());

        //Countdown to handle right or wrong answers
        CountDownTimer mCountDownTimer = new CountDownTimer(8000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 3000) {
                    GAME_ON = false;
                    validateOption(view);
                }
            }

            @Override
            public void onFinish() {
                if (CURRENT_ROUND.equals("Round_10")) {
                    goToResults();
                } else {
                    ((MatchActivity) getActivity()).goToPowerUpFragment();
                }
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

    public void selectOption(View button, View view) {
        if (GAME_ON){
            for (int i = 1; i <= 4; i++) {
                Button otherButtons = view.findViewById(getResources().getIdentifier("questionOption_" + i, "id", this.getContext().getPackageName()));
                otherButtons.setBackgroundResource(R.drawable.ic_button_background_striped);
            }
            idButtonSelected = getResources().getResourceEntryName(button.getId());
            button.setBackgroundResource(R.drawable.ic_button_answer_chosen);
        }
    }

    public void validateOption(View view) {
        for (int i = 1; i <= 4; i++) {
            Button otherButtons = view.findViewById(getResources().getIdentifier("questionOption_" + i, "id", this.getContext().getPackageName()));
            if (i == correct){
                otherButtons.setBackgroundResource(R.drawable.ic_button_answer_right);
            } else if (("questionOption_" + i).equals(idButtonSelected)){
                otherButtons.setBackgroundResource(R.drawable.ic_button_answer_wrong);
            }
        }
    }

    public void goToResults() {
        Intent intent = new Intent(this.getContext(), ResultsActivity.class);
        startActivity(intent);
    }

}
