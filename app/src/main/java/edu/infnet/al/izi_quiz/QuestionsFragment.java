package edu.infnet.al.izi_quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

public class QuestionsFragment extends Fragment {

    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_questions, container,false);

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

        i = 0;
        mProgressBar = view.findViewById(R.id.progressbar);
        mProgressBar.setProgress(i);

        mCountDownTimer = new CountDownTimer(5000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                i++;
                mProgressBar.setProgress(i *100/(5000/1000));
            }

            @Override
            public void onFinish() {
                i++;
                mProgressBar.setProgress(100);
            }
        };

        mCountDownTimer.start();

        return view;
    }

}
