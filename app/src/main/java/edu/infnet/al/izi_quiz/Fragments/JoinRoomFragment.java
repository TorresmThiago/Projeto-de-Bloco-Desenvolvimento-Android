package edu.infnet.al.izi_quiz.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.infnet.al.izi_quiz.Assets.FontChangeCrawler;
import edu.infnet.al.izi_quiz.R;

public class JoinRoomFragment extends Fragment {

    private DatabaseReference mDatabase;
    int i;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join_room, container,false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        joinRoom(view);
        i  = 0;
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getContext().getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup) this.getView());

    }

    private void joinRoom(View view) {
        Button button = view.findViewById(R.id.joinRoomJoinButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                i++;
                mDatabase.child("Matches").child("Room").setValue(i);
            }
        });
    }

}
