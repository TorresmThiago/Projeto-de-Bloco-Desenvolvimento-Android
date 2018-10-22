package edu.infnet.al.izi_quiz;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity {

    ImageView image;
    Fragment menuPrincipal = new MenuPrincipal();
    Fragment menuOpcoes = new MenuOpcoes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.menuBackground);

        animateBackground();
        replaceFragment(menuPrincipal);
    }

    public void goToMenuPrincipalFragment(View view) {
        replaceFragment(menuPrincipal);
    }

    public void goToMenuOpcoesFragment(View view) {
        replaceFragment(menuOpcoes);
    }

    public void startPartida(View view) {
        Intent intent = new Intent(this, PartidaActivity.class);
        startActivity(intent);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.menuFragments, fragment);
        transaction.addToBackStack(null);

        transaction.commit();
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
                image.setTranslationX((float)(200.0 * Math.sin(value*Math.PI)));
                image.setTranslationY((float)(200.0 * Math.cos(value*Math.PI)));
            }
        });

        animator.start();
    }



}
