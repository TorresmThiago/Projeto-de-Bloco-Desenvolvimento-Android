package edu.infnet.al.izi_quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PowerUpFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partida_powerup, container,false);

        Button powerupQuestion = view.findViewById(R.id.powerUpBox);
        Button powerupOption_1 = view.findViewById(R.id.powerUpOption_1);
        Button powerupOption_2 = view.findViewById(R.id.powerUpOption_2);
        Button powerupOption_3 = view.findViewById(R.id.powerUpOption_3);
        Button powerupOption_4 = view.findViewById(R.id.powerUpChoiceButton);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.neutra_text_bold);
        powerupQuestion.setTypeface(typeface);
        powerupOption_1.setTypeface(typeface);
        powerupOption_2.setTypeface(typeface);
        powerupOption_3.setTypeface(typeface);
        powerupOption_4.setTypeface(typeface);

        return view;
    }

}
