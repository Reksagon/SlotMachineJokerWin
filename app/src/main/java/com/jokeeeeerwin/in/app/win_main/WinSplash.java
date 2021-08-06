package com.jokeeeeerwin.in.app.win_main;

import android.content.Intent;
import android.util.Base64;

import com.daimajia.androidanimations.library.Techniques;
import com.jokeeeeerwin.in.app.R;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class WinSplash extends AwesomeSplash {
    @Override
    public void initSplash(ConfigSplash Nz8Nj4a8J5Gn) {
        Nz8Nj4a8J5Gn.setBackgroundColor(R.color.purple_500);
        Nz8Nj4a8J5Gn.setAnimCircularRevealDuration(2000);
        Nz8Nj4a8J5Gn.setRevealFlagX(Flags.REVEAL_LEFT);
        Nz8Nj4a8J5Gn.setRevealFlagY(Flags.REVEAL_TOP);
        Nz8Nj4a8J5Gn.setLogoSplash(R.mipmap.ic_launcher);
        Nz8Nj4a8J5Gn.setAnimLogoSplashDuration(2000);
        Nz8Nj4a8J5Gn.setAnimLogoSplashTechnique(Techniques.Shake);
        Nz8Nj4a8J5Gn.setTitleSplash(new String(Base64.decode("Sm9rZXIgV2lu", Base64.DEFAULT)));
        Nz8Nj4a8J5Gn.setTitleTextColor(R.color.purple_700);
        Nz8Nj4a8J5Gn.setTitleTextSize(30);
        Nz8Nj4a8J5Gn.setAnimTitleDuration(3000);
        Nz8Nj4a8J5Gn.setAnimTitleTechnique(Techniques.BounceInDown);
        Nz8Nj4a8J5Gn.setTitleFont(new String(Base64.decode("Zm9udHMvY2FzaW5vLnR0Zg==", Base64.DEFAULT)));
    }

    @Override
    public void animationsFinished() {
        Intent LZ8dhZH9b5a7 = new Intent(WinSplash.this, WinView.class);
        startActivity(LZ8dhZH9b5a7);
        finish();
    }
}
