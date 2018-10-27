package edu.infnet.al.izi_quiz;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

    static final String LOAD_SPLASH = "goToMainMenu";
    ImageView menuBackgroundImage;
    Fragment splashMenuFragment = new SplashMenuFragment();
    Fragment mainMenuFragment = new MainMenuFragment();
    Fragment leaveGameConfirmation = new LeaveGameFragment();
    Fragment optionsFragment= new OptionsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuBackgroundImage = findViewById(R.id.menuBackground);
        menuBackgroundImage.setImageResource(R.drawable.ic_splash_background);

        animateBackground();

        replaceFragment(splashMenuFragment, "replace");

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(LOAD_SPLASH, false);
        super.onSaveInstanceState(outState);
    }

    public void goToMainMenuFragment(View view) {
        menuBackgroundImage.setImageResource(R.drawable.ic_main_background);
        replaceFragment(mainMenuFragment, "replace");
    }

    public void remainOnApplication (View view) {
        replaceFragment(leaveGameConfirmation, "remove");
    }

    public void leaveApplication(View view) {
        finish();
    }

    public void startMatch(View view) {
        Intent intent = new Intent(this, MatchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        finishAffinity();
        startActivity(intent);
    }

    private void replaceFragment(Fragment fragment, String action) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        switch (action){
            case "replace":
                fragmentTransaction.replace(R.id.menuFragmentsContainer, fragment);
                break;
            case "add":
                fragmentTransaction.add(R.id.menuFragmentsContainer, fragment);
                break;
            case "remove":
                fragmentTransaction.remove(fragment);
                break;
        }

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (!leaveGameConfirmation.isAdded()){
            replaceFragment(leaveGameConfirmation, "add");
        } else {
            replaceFragment(leaveGameConfirmation, "remove");
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
