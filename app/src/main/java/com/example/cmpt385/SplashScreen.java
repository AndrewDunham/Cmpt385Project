/*********************************************************************************
 * MainActivity
 *
 * Description:
 * This class is responsible for displaying a splash screen at the startup
 *
 *Team Name: Team 10+10
 * Authors: Jimmy Kha, Amy Campbell, Andrew Dunham, Terrence Yang
 * Date: October 10 2020
 *
 * Input: none
 * Output: none
 *
 ********************************************************************************/

package com.example.cmpt385;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
