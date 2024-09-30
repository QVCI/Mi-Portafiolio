package com.example.mitransporteonline;
import android.app.Activity;
import android.util.Log;

import com.google.android.gms.ads.interstitial.InterstitialAd;

public class AdManager {
    private static AdManager instance;
    private InterstitialAd mInterstitialAd;

    private AdManager() {}

    public static synchronized AdManager getInstance() {
        if (instance == null) {
            instance = new AdManager();
        }
        return instance;
    }

    public void setInterstitialAd(InterstitialAd interstitialAd) {
        mInterstitialAd = interstitialAd;
    }
    public void MostrarAnuncio(Activity activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
        } else {
            Log.e("AdManager", "El anuncio intersticial no está cargado.");
        }
    }

}
