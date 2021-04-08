package com.app.fastprint.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.app.fastprint.ui.main.MainActivity;
import com.app.fastprint.R;
import com.app.fastprint.utills.AppControler;
import com.app.fastprint.utills.MyFirebaseMessagingService;
import com.google.firebase.FirebaseApp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;
    String auth_token="";
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context=SplashActivity.this;
        FirebaseApp.initializeApp(this);
        auth_token= AppControler.getInstance(context).getString(AppControler.Key.AUTH_TOKEN);
        startService(new Intent(SplashActivity.this, MyFirebaseMessagingService.class));


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                if (auth_token != null && !auth_token.isEmpty()) {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
