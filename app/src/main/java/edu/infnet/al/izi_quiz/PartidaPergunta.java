package edu.infnet.al.izi_quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PartidaPergunta extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partida_pergunta, container,false);

        Button perguntaPergunta = view.findViewById(R.id.perguntaPergunta);
        Button perguntaOpcao1 = view.findViewById(R.id.perguntaOpcao1);
        Button perguntaOpcao2 = view.findViewById(R.id.perguntaOpcao2);
        Button perguntaOpcao3 = view.findViewById(R.id.perguntaOpcao3);
        Button perguntaOpcao4 = view.findViewById(R.id.perguntaOpcao4);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.neutra_text_bold);
        perguntaPergunta.setTypeface(typeface);
        perguntaOpcao1.setTypeface(typeface);
        perguntaOpcao2.setTypeface(typeface);
        perguntaOpcao3.setTypeface(typeface);
        perguntaOpcao4.setTypeface(typeface);


        return view;
    }

}
