package edu.infnet.al.izi_quiz;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class SplashMenuFragment extends Fragment {

    private AdView mAdView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_splash, container,false);

        //Ads related variables
        MobileAds.initialize(view.getContext(), "ca-app-pub-3940256099942544~3347511713");

        //Banner Ads related
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getContext().getAssets(), "fonts/neutra_text_bold.OTF");
        fontChanger.replaceFonts((ViewGroup) this.getView());

    }

}
