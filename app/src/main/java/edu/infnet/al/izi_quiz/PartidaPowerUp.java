package edu.infnet.al.izi_quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PartidaPowerUp extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partida_powerup, container,false);

        Button powerupPergunta = view.findViewById(R.id.powerupPergunta);
        Button powerupOpcao1 = view.findViewById(R.id.powerupOpcao1);
        Button powerupOpcao2 = view.findViewById(R.id.powerupOpcao2);
        Button powerupOpcao3 = view.findViewById(R.id.powerupOpcao3);
        Button powerupOpcao4 = view.findViewById(R.id.powerupEscolha);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.neutra_text_bold);
        powerupPergunta.setTypeface(typeface);
        powerupOpcao1.setTypeface(typeface);
        powerupOpcao2.setTypeface(typeface);
        powerupOpcao3.setTypeface(typeface);
        powerupOpcao4.setTypeface(typeface);

        return view;
    }

}
