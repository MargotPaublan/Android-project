package android.project.spotitop;

import android.app.Application;
import android.project.spotitop.data.di.FakeDependencyInjection;
import android.util.Log;


import com.facebook.stetho.Stetho;

public class SpotiTopApplication extends Application {
    public static final String ID_TOP_PLAYIST = "37i9dQZEVXbMDoHDwVN2tF";

    public static final String CLIENT_ID = "d8e3b49e0c414ff89e9b8acb7b541a55";
    public static final String CLIENT_SECRET = "281502d7036b4dbca80192c16ec5cf53";

    private String accessToken;


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }

}
