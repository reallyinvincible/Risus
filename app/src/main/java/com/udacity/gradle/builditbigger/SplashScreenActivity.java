package com.udacity.gradle.builditbigger;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreenActivity extends AppCompatActivity {

    private LottieAnimationView animationView;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        animationView = findViewById(R.id.splash_screen_animation);
        view = findViewById(R.id.splash_screen);

        animationView.playAnimation();
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                launchHome();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHome();
            }
        });
    }

    void launchHome(){
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}


