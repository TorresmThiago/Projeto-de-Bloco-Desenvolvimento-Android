package edu.infnet.al.izi_quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class QuestionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partida_pergunta, container,false);

        Button questionQuestion = view.findViewById(R.id.questionBox);
        Button questionOption_1 = view.findViewById(R.id.questionOption_1);
        Button questionOption_2 = view.findViewById(R.id.questionOption_2);
        Button questionOption_3 = view.findViewById(R.id.questionOption_3);
        Button questionOption_4 = view.findViewById(R.id.questionOption_4);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.neutra_text_bold);
        questionQuestion.setTypeface(typeface);
        questionOption_1.setTypeface(typeface);
        questionOption_2.setTypeface(typeface);
        questionOption_3.setTypeface(typeface);
        questionOption_4.setTypeface(typeface);


        return view;
    }

}
