package com.example.raghavportfilio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kotlin.Suppress;

public class MainActivity extends AppCompatActivity
{

    private WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomWebViewClient client = new CustomWebViewClient(this);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(client);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://raghavg.vercel.app/");
    }
    // navigation
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        // go back
        if (keyCode == KeyEvent.KEYCODE_BACK && this.webView.canGoBack())
        {
            this.webView.goBack();
            return true;
        }
        // Exit from app
        return super.onKeyDown(keyCode, event);
    }
}

// Custom Web Client on Click on Links of App (internal links)
class CustomWebViewClient extends WebViewClient{
    private Activity activity;      // Variable

    public CustomWebViewClient(Activity activity)      // Constructer in Which we pass Activity Instance
    {
        this.activity = activity;
    }

    // API Level Less then 24
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url)
    {
        return false;     // allow to open link in app only (if true open app in External Brewser)
    }

    // API Level Grater then & Equal to 24
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request)
    {
        return false;     // allow to open link in app only (if true open app in brewser)
    }
}