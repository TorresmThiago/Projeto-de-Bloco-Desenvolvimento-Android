package edu.infnet.al.izi_quiz.Fragments;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.util.Objects;

import edu.infnet.al.izi_quiz.Activities.MainActivity;
import edu.infnet.al.izi_quiz.Activities.MatchActivity;
import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.R;

public class PowerUpFragment extends Fragment{

    ProgressBar mProgressBar;
    String[] questionThemes = {"world", "tv", "animal"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_match_powerup, container,false);

        mProgressBar = view.findViewById(R.id.questionsProgressbar);
        mProgressBar.setProgress(0);

        //Set all buttons to selectButton function onClick
        for (int i = 0; i < questionThemes.length; i++) {
            ImageButton themeButton = view.findViewById(getResources().getIdentifier("questionTheme" + questionThemes[i] + "Icon", "id", this.getContext().getPackageName()));
            themeButton.setOnClickListener(new View.OnClickListener() {
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
        CountDownTimer mCountDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                ((MatchActivity) Objects.requireNonNull(getActivity())).goToQuestionsFragment();
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
        for (int i = 0; i < questionThemes.length; i++) {
            ImageButton themeButton = view.findViewById(getResources().getIdentifier("questionTheme" + questionThemes[i] + "Icon", "id", this.getContext().getPackageName()));
            Drawable drawable;
            if (themeButton.equals(button)){
                drawable = getResources().getDrawable(getResources().getIdentifier("ic_button_theme_" + questionThemes[i] + "_selected", "drawable", this.getContext().getPackageName()));
                themeButton.setBackground(drawable);
            } else {
                drawable = getResources().getDrawable(getResources().getIdentifier("ic_button_theme_" + questionThemes[i], "drawable", this.getContext().getPackageName()));
                themeButton.setBackground(drawable);
            }
        }
    }

}
