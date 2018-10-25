package edu.infnet.al.izi_quiz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MatchActivity extends FragmentActivity {

    Fragment questionsFragment = new QuestionsFragment();
    Fragment powerUpFragment = new PowerUpFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        replaceFragment(powerUpFragment);
    }

    public void goToQuestionsFragment(View view) {
        replaceFragment(questionsFragment);
    }

//    public void goToPowerUpFragment(View view) {
//        replaceFragment(powerUpFragment);
//    }

    public void goToResults(View view) {
        Intent intent = new Intent(this, ResultsActivity.class);
        startActivity(intent);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.matchFragments, fragment);

        fragmentTransaction.commit();
    }
}
