package edu.infnet.al.izi_quiz.Assets.PowerUps;

import android.text.TextUtils;
import android.widget.TextView;

import java.util.Random;

public class Scramble {

    public void scrambleTextView(TextView textView) {

        String text = textView.getText().toString();
        String[] words = text.split(" ");

        for (int i = 0; i < words.length; i++) {
            words[i] = scrambleWord(words[i]);
        }

        text =  TextUtils.join(" ", words);
        textView.setText(text);
    }

    public String scrambleWord(String word) {
        Random rnd = new Random();
        char[] letters = word.toCharArray();

        for (int i = letters.length - 2; i > 0; i--) {

            int index = 1 + rnd.nextInt(i);

            char selectedLetter = letters[index];
            letters[index] = letters[i];
            letters[i] = selectedLetter;
        }

        String str = new String(letters);
        return str;
    }

}
