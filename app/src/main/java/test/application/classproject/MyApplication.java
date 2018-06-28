package test.application.classproject;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * Created by Iman on 6/26/2018.
 */

public class MyApplication extends Application {
    public static MyApplication appInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        appInstance=this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
