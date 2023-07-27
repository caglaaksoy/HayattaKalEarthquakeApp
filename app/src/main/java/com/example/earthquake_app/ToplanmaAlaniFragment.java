package com.example.earthquake_app;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class ToplanmaAlaniFragment extends Fragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_toplanma_alani, container, false);
        final Button openWebViewButton = rootView.findViewById(R.id.openWebViewButton);
        final WebView webView = rootView.findViewById(R.id.webView);

        // WebView ayarlarını yapılandır
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // WebView yükleme işlemlerini dinle
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // WebView yüklenme tamamlandığında butonu gizle
                openWebViewButton.setVisibility(View.GONE);
            }
        });

        // Butona tıklama işlemlerini dinle
        openWebViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // WebView görünürlüğünü ayarla
                webView.setVisibility(View.VISIBLE);

                // Belirtilen URL'yi yükle
                webView.loadUrl("https://www.turkiye.gov.tr/");
            }
        });

        return rootView;
    }


    public ToplanmaAlaniFragment() {
        // Required empty public constructor

    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_toplanma_alani, container, false);
//
//    }
}