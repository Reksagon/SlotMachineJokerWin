package com.jokeeeeerwin.in.app.win_webs;

import android.util.Base64;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WinInWebClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(!url.startsWith(new String(Base64.decode("aHR0cHM6Ly93d3cudGlrdG9rLmNvbS8=", Base64.DEFAULT)))
                ||! url.startsWith(new String(Base64.decode("aHR0cHM6Ly93d3cubGlua2VkaW4uY29tLw==", Base64.DEFAULT)))
                ||!url.startsWith(new String(Base64.decode("aHR0cHM6Ly93d3cuaW5zdGFncmFtLmNvbS8=", Base64.DEFAULT)))
                ||!url.startsWith(new String(Base64.decode("aHR0cHM6Ly93d3cubGlua2VkaW4uY29tLw==", Base64.DEFAULT)))
                ||!url.startsWith(new String(Base64.decode("aHR0cHM6Ly93d3cuZmFjZWJvb2suY29tLw==", Base64.DEFAULT))))
            view.loadUrl(url);

        return true;
    }
}
