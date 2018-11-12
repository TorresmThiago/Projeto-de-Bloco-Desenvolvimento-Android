package edu.infnet.al.izi_quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class ResultsActivity extends AppCompatActivity {

    private static final String MATCH_DATE = "matchDate";
    private static final String MATCH_POSITION = "matchPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String date = getIntent().getStringExtra(MATCH_DATE);
        int position = getIntent().getIntExtra(MATCH_POSITION, 0);

        System.out.println(date);
        System.out.println(position);
    }

    @Override
    public void setContentView(View view)  {
        super.setContentView(view);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));
    }

    @Override
    public void onBackPressed() {
        returnToMenu();
    }

    public void returnToMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void returnToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
