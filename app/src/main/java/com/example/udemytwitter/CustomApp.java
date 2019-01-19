package com.example.udemytwitter;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.example.udemytwitter.R.id.*;


public class CustomApp extends Application {

    public static String CUSTOMER_KEY ="XlzsY5SJ0Q7R9EX9R13GiZRPA";
    public static String CUSTOMER_SECRET ="6A3rxpUsVmmalXIRR2JTidVc8BZy7gdycbYHjj24cOE7Aa7RZ7";

    private TwitterLoginButton loginButton;


    public void onCreate() {
        super.onCreate();

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(CUSTOMER_KEY, CUSTOMER_SECRET))
                .debug(true)
                .build();


        Twitter.initialize(config);



        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                TwitterSession session = result.data;

                String msg = "@" + session.getUserName()+"logged in! (#"+session.getUserId();
                Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_LONG).show();

            }

            @Override
            public void failure(TwitterException exception) {
            Log.d("TwitterKit","Login with Twitter failure", exception);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

}
