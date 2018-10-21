package edu.infnet.al.izi_quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuPrincipal extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_principal, container,false);

        Button jogarButton = view.findViewById(R.id.menuJogarButton);
        Button opcoesButton = view.findViewById(R.id.menuOpcoesButton);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.neutra_text_bold);
        jogarButton.setTypeface(typeface);
        opcoesButton.setTypeface(typeface);

        return view;
    }

}
