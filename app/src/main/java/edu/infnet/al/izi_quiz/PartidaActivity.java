package edu.infnet.al.izi_quiz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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

    public void goToResults(View view) {
        Intent intent = new Intent(this, ResultadoActivity.class);
        startActivity(intent);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.partidaFragments, fragment);

        fragmentTransaction.commit();
    }
}
