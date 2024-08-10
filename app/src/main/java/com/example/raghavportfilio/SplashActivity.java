package com.example.raghavportfilio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);
        TextView animatedText = findViewById(R.id.animated_text);

        // Create a scale animation for the logo
        Animation scaleAnimation = new ScaleAnimation(
                0.5f, 1.0f,   // Start and end values for the X axis scaling
                0.5f, 1.0f,   // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        scaleAnimation.setDuration(1000); // 1 second
        scaleAnimation.setFillAfter(true); // Keep the end state after the animation

        // Create a rotate animation for the logo
        Animation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000); // 1 second

        // Combine the scale and rotate animations
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);

        // Start the logo animation
        logo.startAnimation(animationSet);

        // Create a fade-in animation for the text
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(2000); // 2 seconds

        // Set the animation listener to make the text visible and start the animation
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animatedText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Transition to the main activity after the text animation
                new Handler().postDelayed(() -> {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }, 1500); // 1.5 seconds delay
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // No action needed here
            }
        });

        // Start the text animation with a delay to sync with the logo animation
        new Handler().postDelayed(() -> animatedText.startAnimation(fadeIn), 1000); // 1 second delay
    }
}