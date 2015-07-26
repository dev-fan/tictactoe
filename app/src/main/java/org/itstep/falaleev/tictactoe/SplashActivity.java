package org.itstep.falaleev.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashActivity extends Activity {

    private static final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView ivSplashX = (ImageView) findViewById(R.id.ivSplashX);
        Animation animX = AnimationUtils.loadAnimation(this, R.anim.splash);
        ivSplashX.startAnimation(animX);
        ImageView ivSplashO = (ImageView) findViewById(R.id.ivSplashO);
        Animation animO = AnimationUtils.loadAnimation(this, R.anim.splash);
        ivSplashO.startAnimation(animO);
        TextView tvSplashName = (TextView) findViewById(R.id.tvSplashName);
        Animation animN = AnimationUtils.loadAnimation(this, R.anim.splash);
        tvSplashName.startAnimation(animN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
