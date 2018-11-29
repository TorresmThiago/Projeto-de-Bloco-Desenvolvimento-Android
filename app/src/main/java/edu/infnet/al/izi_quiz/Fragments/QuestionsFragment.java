package edu.infnet.al.izi_quiz.Fragments;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

import edu.infnet.al.izi_quiz.Activities.ResultsActivity;
import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.R;

public class QuestionsFragment extends Fragment {

    ProgressBar mProgressBar;
    Boolean gameOn;
    int correct;
    String idButtonSelected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_match_questions, container, false);

        mProgressBar = view.findViewById(R.id.progressbar);
        mProgressBar.setProgress(0);
        gameOn = true;
        correct =  1 + (int) (Math.random() * 4);


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
                    gameOn = false;
                    validateOption(view);
                }
            }

            @Override
            public void onFinish() {
                goToResults();
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
        if (gameOn){
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
