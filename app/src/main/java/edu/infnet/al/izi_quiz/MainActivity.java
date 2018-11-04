package edu.infnet.al.izi_quiz;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    ImageView menuBackgroundImage;
    Fragment splashMenuFragment = new SplashMenuFragment();
    Fragment mainMenuFragment = new MainMenuFragment();
    Fragment leaveGameConfirmation = new LeaveGameFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Background Related methods.
        menuBackgroundImage = findViewById(R.id.menuBackground);
        menuBackgroundImage.setImageResource(R.drawable.ic_splash_background);
        animateBackground(menuBackgroundImage);

        replaceFragment(splashMenuFragment, "replace");

    }

    @Override
    public void setContentView(View view)  {
        super.setContentView(view);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));
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

    public void animateBackground(final ImageView background) {
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
                background.setTranslationX((float)(200.0 * Math.sin(value*Math.PI)));
                background.setTranslationY((float)(200.0 * Math.cos(value*Math.PI)));
            }
        });

        animator.start();
    }



}
