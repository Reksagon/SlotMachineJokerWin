package com.jokeeeeerwin.in.app.win_reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.WebView;

public class WinInReciever extends BroadcastReceiver {
    String winUrl;
    WebView webView;

    public WinInReciever(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager winManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo winNetworkInfo = winManager.getActiveNetworkInfo();

        if(winNetworkInfo != null && winNetworkInfo.isConnectedOrConnecting())
        {
            if(winUrl != null) webView.loadUrl(winUrl);
        }
        else
        {
            winUrl = webView.getUrl();
            webView.loadUrl("file:///android_asset/index.html");
        }
    }
}

