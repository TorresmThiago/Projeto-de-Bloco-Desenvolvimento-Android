package edu.infnet.al.izi_quiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MatchActivity extends FragmentActivity {

    Fragment questionsFragment = new QuestionsFragment();
    Fragment powerUpFragment = new PowerUpFragment();
    Dialog leaveMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        leaveMatch = new Dialog(this);

        goToPowerUpFragment();
    }

    @Override
    public void onBackPressed() {
        leaveMatchConfirmation();
    }

    public void leaveMatchConfirmation(){
        if (!leaveMatch.isShowing()){
            leaveMatch.setContentView(R.layout.asset_popup_leavematch);
            leaveMatch.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/neutra_text_bold.OTF");
            fontChanger.replaceFonts((ViewGroup)leaveMatch.getWindow().findViewById(R.id.popUpLeaveMatch));

            leaveMatch.show();
        } else {
            leaveMatch.dismiss();
        }
    }

    public void leaveMatchConfirmation(View view){
        if (leaveMatch.isShowing()) {
            leaveMatch.dismiss();
        }
    }

    @Override
    public void setContentView(View view)
    {
        super.setContentView(view);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));
    }

    public void goToQuestionsFragment(View view) {
        replaceFragment(questionsFragment, "replace");
    }

    public void goToPowerUpFragment(/*View view*/) {
        replaceFragment(powerUpFragment, "replace");
    }

    public void returnToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void replaceFragment(Fragment fragment, String action) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        switch (action){
            case "replace":
                fragmentTransaction.replace(R.id.matchFragments, fragment);
                break;
            case "add":
                fragmentTransaction.add(R.id.matchFragments, fragment);
                break;
            case "remove":
                fragmentTransaction.remove(fragment);
                break;
            }

        fragmentTransaction.commit();
    }
}
