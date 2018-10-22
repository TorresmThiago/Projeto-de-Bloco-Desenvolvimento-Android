package edu.infnet.al.izi_quiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PartidaActivity extends FragmentActivity {

    Fragment pergunta = new PartidaPergunta();
    Fragment powerUp = new PartidaPowerUp();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        replaceFragment(powerUp);
    }

    public void goToPergunta(View view) {
        replaceFragment(pergunta);
    }

//    public void goToPowerUp(View view) {
//        replaceFragment(powerUp);
//    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.partidaFragments, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
