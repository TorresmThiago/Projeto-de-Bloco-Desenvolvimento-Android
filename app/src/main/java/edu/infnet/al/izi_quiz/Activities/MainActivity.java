package edu.infnet.al.izi_quiz.Activities;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.Fragments.CreateRoomFragment;
import edu.infnet.al.izi_quiz.Fragments.JoinRoomFragment;
import edu.infnet.al.izi_quiz.Fragments.MainMenuFragment;
import edu.infnet.al.izi_quiz.Fragments.SplashMenuFragment;
import edu.infnet.al.izi_quiz.Fragments.StartMatchFragment;
import edu.infnet.al.izi_quiz.R;

public class MainActivity extends FragmentActivity {

    ImageView menuBackgroundImage;

    private Fragment splashMenuFragment = new SplashMenuFragment();
    private Fragment mainMenuFragment = new MainMenuFragment();
    private Fragment startMatchFragment = new StartMatchFragment();
    private Fragment createRoomFragment = new CreateRoomFragment();
    private Fragment joinRoomFragment = new JoinRoomFragment();

    Dialog leaveGame;
    Dialog optionsModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leaveGame = new Dialog(this);
        optionsModal = new Dialog(this);

        menuBackgroundImage = findViewById(R.id.menuBackground);
        menuBackgroundImage.setImageResource(R.drawable.ic_splash_background);

        animateBackground(menuBackgroundImage);
        replaceFragment(splashMenuFragment);
    }

    @Override
    public void onBackPressed() {
        leaveGameConfirmation();
    }

    public void leaveGameConfirmation(View button){
        if (leaveGame.isShowing()){
            leaveGame.dismiss();
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

    public void openOptionsModal (View view) {
        optionsModal.setContentView(R.layout.asset_modal_options);
        optionsModal.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup)optionsModal.getWindow().findViewById(R.id.popUpGameOptions));

        optionsModal.show();
    }

    public void CloseOptions(View button){
        if (optionsModal.isShowing()){
            optionsModal.dismiss();
        }
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

    public void goToSplashMenu(View view) {
        menuBackgroundImage.setImageResource(R.drawable.ic_splash_background);
        replaceFragment(splashMenuFragment);
    }

    public void goToMainMenuFragment(View view) {
        menuBackgroundImage.setImageResource(R.drawable.ic_main_background);
        replaceFragment(mainMenuFragment);
    }

    public void goToStartMatchFragment(View view) {
        menuBackgroundImage.setImageResource(R.drawable.ic_main_background);
        replaceFragment(startMatchFragment);
    }

    public void goToCreateRoomFragment(View view) {
        replaceFragment(createRoomFragment);
    }

    public void goToJoinRoomFragment(View view) {
        replaceFragment(joinRoomFragment);
    }

    public void joinRoom (View view) {
        EditText keyInput = findViewById(R.id.joinRoomCodeInput);
        String key = keyInput.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("key", key);

        createRoomFragment.setArguments(bundle);

        replaceFragment(createRoomFragment);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.menuFragmentsContainer, fragment);

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
