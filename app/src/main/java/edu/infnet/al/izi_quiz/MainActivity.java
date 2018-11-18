package edu.infnet.al.izi_quiz;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    ImageView menuBackgroundImage;
    Fragment splashMenuFragment = new SplashMenuFragment();
    Fragment mainMenuFragment = new MainMenuFragment();
    Dialog leaveGame;
    Dialog optionsModal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leaveGame = new Dialog(this);
        optionsModal = new Dialog(this);

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

    @Override
    public void onBackPressed() {
        if (leaveGame.isShowing()){
            leaveGame.dismiss();
        }

        if (optionsModal.isShowing()){
            optionsModal.dismiss();
        }
    }

    public void leaveGameConfirmation(){
        if (!leaveGame.isShowing()){
            leaveGame.setContentView(R.layout.asset_popup_leavegame);
            leaveGame.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/neutra_text_bold.OTF");
            fontChanger.replaceFonts((ViewGroup)leaveGame.getWindow().findViewById(R.id.popUpLeaveGame));

            leaveGame.show();
        } else {
            leaveGame.dismiss();
        }
    }

    public void leaveGameConfirmation(View button){
        if (leaveGame.isShowing()){
            leaveGame.dismiss();
        }
    }

    public void CloseOptions(View button){
        if (optionsModal.isShowing()){
            optionsModal.dismiss();
        }
    }

    public void openOptionsModal (View view) {
        optionsModal.setContentView(R.layout.asset_modal_options);
        optionsModal.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup)optionsModal.getWindow().findViewById(R.id.popUpLeaveGame));

        optionsModal.show();
    }

    public void ToggleSound(View soundButton) {
        if (soundButton.getBackground().getConstantState() == getResources().getDrawable(R.drawable.ic_button_sound_on).getConstantState()){
            soundButton.setBackgroundResource(R.drawable.ic_button_sound_off);
        } else {
            soundButton.setBackgroundResource(R.drawable.ic_button_sound_on);
        }
    }


    public void ToggleMusic(View musicButton) {
        if (musicButton.getBackground().getConstantState() == getResources().getDrawable(R.drawable.ic_button_music_on).getConstantState()){
            musicButton.setBackgroundResource(R.drawable.ic_button_music_off);
        } else {
            musicButton.setBackgroundResource(R.drawable.ic_button_music_on);
        }
    }

    public void leaveApplication(View view) {
        finish();
    }

    public void goToMainMenuFragment(View view) {
        menuBackgroundImage.setImageResource(R.drawable.ic_main_background);
        replaceFragment(mainMenuFragment, "replace");
    }

    public void goToSplashMenu(View view) {
        menuBackgroundImage.setImageResource(R.drawable.ic_splash_background);
        replaceFragment(splashMenuFragment, "replace");
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

    public void startMatch(View view) {
        Intent intent = new Intent(this, MatchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        finishAffinity();
        startActivity(intent);
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
