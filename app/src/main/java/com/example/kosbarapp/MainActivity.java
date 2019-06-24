package com.example.kosbarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.GameRequestDialog;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    public final String TAG = "kosbarApp:";
    private LoginButton loginButton;
    private Button gameRequestButton;
    private TextView displayName, emailID;
    private ImageView displayImage;
    private CallbackManager callbackManager;
    private static GameRequestDialog gameRequestDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FacebookSdk.sdkInitialize(this.getApplicationContext());
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);

        displayName = findViewById(R.id.display_name);
        emailID = findViewById(R.id.email);
        displayImage = findViewById(R.id.image_view);
        loginButton = findViewById(R.id.login_button);
        gameRequestButton = findViewById(R.id.button2);

        gameRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestButton();
            }
        });

        Log.e(TAG, "HELLO ANDROID WORLD");
        callbackManager = CallbackManager.Factory.create();
        //loginButton.setReadPermissions("email");
        // If using in a fragment

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Set<String> perms = loginResult.getAccessToken().getPermissions();
                Log.e(TAG, "permisions" + perms.toString());
                Log.e(TAG, "ETO POBEDA " + loginResult.getAccessToken().getDataAccessExpirationTime());
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

    @Override
    public void onActivityResult(int requestCode, int resulrCode, Intent data) {
        super.onActivityResult(requestCode, resulrCode, data);

        callbackManager.onActivityResult(requestCode, resulrCode, data);
    }

    private void onClickRequestButton() {
        GameRequestDialog.show(this, new GameRequestContent.Builder().setMessage("TEST").build());
    }
}
