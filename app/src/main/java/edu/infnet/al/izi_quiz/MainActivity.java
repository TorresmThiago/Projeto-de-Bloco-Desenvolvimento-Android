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
    Fragment splashMenuFragment = new SplashMenuFragment();
    Fragment optionsFragment = new OptionsFragment();
    Fragment leaveGameConfirmation = new LeaveGameFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuBackgroundImage = findViewById(R.id.menuBackground);

        animateBackground();
        replaceFragment(splashMenuFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        replaceFragment(splashMenuFragment);
    }

    public void goToMainMenuFragment(View view) {
        replaceFragment(splashMenuFragment);
    }

    public void goToOptionsFragment(View view) {
        replaceFragment(optionsFragment);
    }

    public void leaveApplication(View view) {finish();}

    public void startMatch(View view) {
        Intent intent = new Intent(this, MatchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        if (fragment.equals(optionsFragment)) {
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.menuFragmentsContainer, fragment);
        } else if(fragment.equals(splashMenuFragment)) {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.replace(R.id.menuFragmentsContainer, fragment);
        } else if(fragment.equals(leaveGameConfirmation)){
            fragmentTransaction.add(R.id.menuFragmentsContainer, fragment);
        }

        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        if (leaveGameConfirmation.isAdded() || optionsFragment.isAdded()){
            replaceFragment(splashMenuFragment);
        } else {
            replaceFragment(leaveGameConfirmation);
        }
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
