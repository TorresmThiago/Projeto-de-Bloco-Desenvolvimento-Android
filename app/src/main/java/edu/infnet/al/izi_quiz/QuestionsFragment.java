package edu.infnet.al.izi_quiz;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

public class QuestionsFragment extends Fragment {

    ProgressBar mProgressBar;
    Boolean gameOn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_questions, container,false);

        mProgressBar = view.findViewById(R.id.progressbar);
        mProgressBar.setProgress(0);
        gameOn = true;

        //ProgressBar Animation
        ObjectAnimator animation = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 100);
        animation.setDuration(5000);
        animation.setInterpolator(new LinearInterpolator());

        //Countdown to handle right or wrong answers
        CountDownTimer mCountDownTimer = new CountDownTimer(8000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished == 3000){
                    gameOn = false;
                    //TODO: Bring onClick function to this fragment, and make its functions depend on the gameOn boolean
                    //TODO: Implement function that makes the selected answer background green or red depending on right or wrong
                }
            }

            @Override
            public void onFinish() {
                goToResults();
            }
        };

        setTypeface(view);
        mCountDownTimer.start();
        animation.start();
        return view;
    }

    public void goToResults() {
        Intent intent = new Intent(this.getContext(), ResultsActivity.class);
        startActivity(intent);
    }

    public void setTypeface(View view){
        Button questionQuestion = view.findViewById(R.id.questionBox);
        Button questionOption_1 = view.findViewById(R.id.questionOption_1);
        Button questionOption_2 = view.findViewById(R.id.questionOption_2);
        Button questionOption_3 = view.findViewById(R.id.questionOption_3);
        Button questionOption_4 = view.findViewById(R.id.questionOption_4);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.neutra_text_bold);
        questionQuestion.setTypeface(typeface);
        questionOption_1.setTypeface(typeface);
        questionOption_2.setTypeface(typeface);
        questionOption_3.setTypeface(typeface);
        questionOption_4.setTypeface(typeface);
    }

}
