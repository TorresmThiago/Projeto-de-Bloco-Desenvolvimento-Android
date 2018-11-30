package edu.infnet.al.izi_quiz.Assets.PowerUps;

import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class FadeIn {

    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);

    public void fadeInTextView (TextView textView) {
        textView.startAnimation(fadeIn);
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);
    }
}
