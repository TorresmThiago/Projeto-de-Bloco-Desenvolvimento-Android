package edu.infnet.al.izi_quiz;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

    ImageView menuBackgroundImage;
    Fragment menuFragment = new MenuFragment();
    Fragment optionsFragment = new OptionsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuBackgroundImage = findViewById(R.id.menuBackground);

        animateBackground();
        replaceFragment(menuFragment);
    }

    public void goToMainMenuFragment(View view) {
        replaceFragment(menuFragment);
    }

    public void goToOptionsFragment(View view) {
        replaceFragment(optionsFragment);
    }

    public void startMatch(View view) {
        Intent intent = new Intent(this, MatchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        if (fragment.equals(optionsFragment)) {
            fragmentTransaction.replace(R.id.menuFragments, fragment);
            fragmentTransaction.addToBackStack(null);
        } else {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.replace(R.id.menuFragments, fragment);
        }

        fragmentTransaction.commit();

    }

    public void animateBackground() {
        ValueAnimator animator = ValueAnimator.ofFloat(-1, 1);
        animator.setDuration(10000);
        animator.setRepeatCount(-1);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(null);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) (animation.getAnimatedValue());
                menuBackgroundImage.setTranslationX((float)(200.0 * Math.sin(value*Math.PI)));
                menuBackgroundImage.setTranslationY((float)(200.0 * Math.cos(value*Math.PI)));
            }
        });

        animator.start();
    }



}
