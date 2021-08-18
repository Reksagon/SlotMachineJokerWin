package com.jokeeeeerwin.in.app.win_main;

import android.content.Intent;
import android.util.Base64;
import android.webkit.WebView;

import com.daimajia.androidanimations.library.Techniques;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.jokeeeeerwin.in.app.R;
import com.jokeeeeerwin.in.app.win_webs.WinInWebClient;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class WinSplash extends AwesomeSplash {
    public static FirebaseRemoteConfig win_FirebaseRemoteConfig;
    WebView ww;
    @Override
    public void initSplash(ConfigSplash configSplash) {
        setContentView(R.layout.view);
        win_FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings win_FirebaseRometeConfigSettings = new FirebaseRemoteConfigSettings.Builder().build();
        win_FirebaseRemoteConfig.setDefaultsAsync(R.xml.win_url);
        win_FirebaseRemoteConfig.setConfigSettingsAsync(win_FirebaseRometeConfigSettings);
        ww = findViewById(R.id.ww);
        ww.setWebViewClient(new WinInWebClient(this));
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        configSplash.setBackgroundColor(R.color.purple_500);
        configSplash.setAnimCircularRevealDuration(2000);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagY(Flags.REVEAL_TOP);
        configSplash.setLogoSplash(R.mipmap.ic_launcher);
        configSplash.setAnimLogoSplashDuration(2000);
        configSplash.setAnimLogoSplashTechnique(Techniques.Shake);
        configSplash.setTitleSplash("Joker Win");
        configSplash.setTitleTextColor(R.color.purple_700);
        configSplash.setTitleTextSize(30);
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.BounceInDown);
        configSplash.setTitleFont("fonts/casino.ttf");
    }

    @Override
    public void animationsFinished() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ww.loadUrl(win_FirebaseRemoteConfig.getString(new String(Base64.decode("d2luX3VybA==", Base64.DEFAULT))));
            }
        };
        ww.post(runnable);
        runnable.run();
    }
}
