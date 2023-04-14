//=====================================================================
//                      Assignment 2
//
//  Name of file:       SecondActivity.java
//
//  Description:       Provides control over second screen
//
//=====================================================================


package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.ViewGroup;
import android.webkit.WebView;      // need this for WebView

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private Button continueButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        // ==============================
        // GET REFERENCES TO THE WIDGETS
        // ==============================
        webView = findViewById(R.id.webview);
        webView.loadUrl("https://www.trivago.ca/");

        /*
        String url = "https://www.trivago.ca/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        */

        continueButton = findViewById(R.id.continueButton);

        // SET THE LISTENERS
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThirdActivity();
            }
        });

    }


    // Open Third Activity
    public void openThirdActivity() {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}