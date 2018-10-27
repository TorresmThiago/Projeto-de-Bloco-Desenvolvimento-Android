package edu.infnet.al.izi_quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PopUpFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.asset_popup_leavegame, container,false);

        TextView popUpText = view.findViewById(R.id.popUpLeaveGameContainer);
        Button confirmationButton = view.findViewById(R.id.popUpLeaveGameConfirmation);
        Button negationButton = view.findViewById(R.id.popUpLeaveGameNegation);
        Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.neutra_text_bold);
        popUpText.setTypeface(typeface);
        confirmationButton.setTypeface(typeface);
        negationButton.setTypeface(typeface);

        return view;
    }

}
