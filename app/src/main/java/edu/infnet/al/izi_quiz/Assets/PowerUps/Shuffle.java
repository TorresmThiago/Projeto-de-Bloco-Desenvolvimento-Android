package edu.infnet.al.izi_quiz.Assets.PowerUps;

import java.util.Random;

public class Shuffle {

    static String shuffleArray(String word) {
        Random rnd = new Random();
        char[] letters = word.toCharArray();

        for (int i = letters.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);

            char selectedLetter = letters[index];
            letters[index] = letters[i];
            letters[i] = selectedLetter;
        }

        String str = new String(letters);
        return str;
    }

}
