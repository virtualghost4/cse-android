package com.example.zhelon.instagramcseapplication.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by zhelon on 30-11-17.
 */

public class MyApp extends Application {

    private static MyApp instance;

    @Override

    public void onCreate() {

        super.onCreate();

        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext())
                .name("cse-android.realm").build();

        Realm.deleteRealm(config);

        Realm.setDefaultConfiguration(config);

    }

    public static MyApp getInstance() {

        return instance;

    }
}
