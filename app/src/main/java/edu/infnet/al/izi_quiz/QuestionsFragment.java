package edu.infnet.al.izi_quiz;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

public class QuestionsFragment extends Fragment {

    ProgressBar mProgressBar;

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

        mProgressBar = view.findViewById(R.id.progressbar);
        mProgressBar.setProgress(0);

        ObjectAnimator animation = ObjectAnimator.ofInt(mProgressBar, "progress", 0, 100);
        animation.setDuration(5000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) { }

            @Override
            public void onAnimationEnd(Animator animator) {
                //Bring the codes of changing activity to here.
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animation.start();
        return view;
    }

}
