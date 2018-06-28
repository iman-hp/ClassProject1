package test.application.classproject;

import android.content.Context;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

/**
 * Created by Iman on 6/26/2018.
 */

public class PublicMethods {



    public static void saveValue(String key,String value){
        Hawk.put(key,value);
    }
    public static String getvalue(String key,String defvalue){
        return Hawk.get(key,defvalue);
    }
    public static void toast(String msg){
        Toast.makeText(MyApplication.appInstance,msg, Toast.LENGTH_SHORT).show();
    }
}
