package test.application.classproject.Activities;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import test.application.classproject.MainActivity;
import test.application.classproject.PublicMethods;
import test.application.classproject.R;

public class SplashScreen extends AppCompatActivity {

    TextView txt_city;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash_screen);
        super.onCreate(savedInstanceState);
        txt_city = findViewById(R.id.txt_city);
        intent();
        connect();

    }

    void intent() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        }, 3000);

    }

    void connect() {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://ip-api.com/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                PublicMethods.toast(throwable.toString());
            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                Gson gson = new Gson();
                API result = gson.fromJson(s, API.class);
                txt_city.setText(result.getCountry()+result.getCity());
            }
        });

    }
}
