package com.jokeeeeerwin.in.app.win_main;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.jokeeeeerwin.in.app.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class WinSplash extends AwesomeSplash {
    @Override
    public void initSplash(ConfigSplash configSplash) {
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
        Intent winIntent = new Intent(WinSplash.this, WinView.class);
        startActivity(winIntent);
        finish();
    }
}
