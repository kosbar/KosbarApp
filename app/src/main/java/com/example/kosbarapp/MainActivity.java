package com.example.kosbarapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends AppCompatActivity {
    public final String TAG = "kosbarApp:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG, "HELLO ANDROID WORLD");
        CallbackManager callbackManager = CallbackManager.Factory.create();


        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e(TAG, "ETO POBEDA");
            }

            @Override
            public void onCancel() {
                Log.e(TAG, "ETO PORAZHENIE");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e(TAG, "WTF?!?!?!?");
            }
        });

    }
}
