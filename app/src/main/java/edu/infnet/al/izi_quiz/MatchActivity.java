package edu.infnet.al.izi_quiz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MatchActivity extends FragmentActivity {

    Fragment questionsFragment = new QuestionsFragment();
    Fragment powerUpFragment = new PowerUpFragment();
    Fragment leaveMatchConfirmation = new LeaveMatchFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        goToPowerUpFragment();
    }

    public void goToQuestionsFragment(View view) {
        replaceFragment(questionsFragment, "replace");
    }

    public void goToPowerUpFragment(/*View view*/) {
        replaceFragment(powerUpFragment, "replace");
    }

    public void remainOnCurrentMatch(View view) {
        replaceFragment(leaveMatchConfirmation, "remove");
    }

    public void returnToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (!leaveMatchConfirmation.isAdded()){
            replaceFragment(leaveMatchConfirmation, "add");
        }
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
