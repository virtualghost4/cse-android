package com.example.zhelon.camaraaplication;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by zhelon on 09-05-17.
 */

public class MyApp extends Application {
    /**
     * Created by zhelon on 09-05-17.
     */


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
