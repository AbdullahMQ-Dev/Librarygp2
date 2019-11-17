package com.example.librarygp2.Activities;
import android.content.Intent;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarygp2.R;


public class Bot extends AppCompatActivity {
    private WebView webview;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        webview=(WebView)findViewById(R.id.web_view);
        Intent intent=getIntent();
       // String message=intent.getStringExtra(HomeActivity.KARAN);
        String url = "https://bot.dialogflow.com/695d10aa-df8a-4f94-9cad-36ead089dd17";
        webview.loadUrl(url);
        WebViewClient webViewClient=new WebViewClient();
        webview.setWebViewClient(webViewClient);
        webview.getSettings().setJavaScriptEnabled(true);

    }
}
