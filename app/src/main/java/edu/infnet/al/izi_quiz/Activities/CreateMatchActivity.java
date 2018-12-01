package edu.infnet.al.izi_quiz.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.infnet.al.izi_quiz.Fragments.CreateRoomFragment;
import edu.infnet.al.izi_quiz.Fragments.JoinRoomFragment;
import edu.infnet.al.izi_quiz.R;

public class CreateMatchActivity extends FragmentActivity {


    Fragment createRoom = new CreateRoomFragment();
    Fragment joinRoom = new JoinRoomFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);
    }

    public void createNewRoom(View view) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.roomContainer, createRoom);

        fragmentTransaction.commit();
    }

    public void joinRoom(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.roomContainer, joinRoom);

        fragmentTransaction.commit();
    }
}
