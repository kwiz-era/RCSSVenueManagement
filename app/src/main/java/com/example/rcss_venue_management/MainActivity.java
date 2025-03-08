package com.example.rcss_venue_management;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DURATION = 5000; // 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize animations
        Animation topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        Animation bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        // Bind UI components
        ImageView logoImageView = findViewById(R.id.logo_imageView);
        TextView frontPageTextView = findViewById(R.id.frontpage_textView);

        // Set animations to UI components
        logoImageView.setAnimation(topAnim);
        frontPageTextView.setAnimation(bottomAnim);

        // Splash screen delay before moving to next activity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, Sign_In.class); // Change to your next activity
            startActivity(intent);
            finish(); // Close splash screen
        }, SPLASH_SCREEN_DURATION);
    }
}
