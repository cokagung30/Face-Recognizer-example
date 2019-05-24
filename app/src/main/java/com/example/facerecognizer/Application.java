package com.example.facerecognizer;

import com.facebook.stetho.Stetho;
import com.orm.Database;
import com.orm.SugarApp;

public class Application extends SugarApp {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    public Database obtainDatabase() {
        return getDatabase();
    }
}
