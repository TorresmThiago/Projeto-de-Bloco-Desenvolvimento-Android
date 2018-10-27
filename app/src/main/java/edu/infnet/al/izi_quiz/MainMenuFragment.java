package edu.infnet.al.izi_quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_main, container,false);

        Button playButton = view.findViewById(R.id.MainMenuPlayButton);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.neutra_text_bold);
        playButton.setTypeface(typeface);

        return view;
    }

}
