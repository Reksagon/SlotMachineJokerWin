package com.jokeeeeerwin.in.app.win_app;

import android.app.Application;

import com.onesignal.OneSignal;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class WinInApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        YandexMetricaConfig yaconfig = YandexMetricaConfig.newConfigBuilder("3a29f386-9e7e-43fc-95d3-0b56166e7b12").build();
        OneSignal.initWithContext(getApplicationContext());
        YandexMetrica.activate(getApplicationContext(), yaconfig);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        YandexMetrica.enableActivityAutoTracking(this);
        OneSignal.setAppId("b1496846-555c-44bb-a36a-20902d432d6d");
    }
}
