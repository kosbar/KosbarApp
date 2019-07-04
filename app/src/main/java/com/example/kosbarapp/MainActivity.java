package com.example.kosbarapp;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.widget.GameRequestDialog;
import com.facebook.share.model.GameRequestContent;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    public final String TAG = "kosbarApp:";
    private LoginButton loginButton;
    private Button gameRequestButton;
    private TextView displayName, emailID;
    private ImageView displayImage;
    private CallbackManager callbackManager;
    public Notification notification;
    //private GameRequestDialog




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

        Notification.Builder builder =
                new Notification.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Notification text");

        notification = builder.build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);


        gameRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification);

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
