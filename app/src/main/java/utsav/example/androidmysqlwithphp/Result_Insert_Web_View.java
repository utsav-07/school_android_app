package utsav.example.androidmysqlwithphp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Result_Insert_Web_View extends AppCompatActivity {
    private WebView wv1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__insert__web__view);
        //   b1=(Button)findViewById(R.id.button2);
        // ed1=(EditText)findViewById(R.id.editText);
        wv1=(WebView)findViewById(R.id.web_view);
        String url = Constants.URL_RESULT_INSERT;

        //http://localhost/MyFold/Android/result_creation_view/ResultSectionPage.php

       // String url = "http://192.168.43.165/MyFold/Android/result_creation_view/ResultSectionPage.php";
        // String url = "http://192.168.43.165/angularjs_insert_update_delete_new/HigerClass.php";
        // String url = "https://www.youtube.com/";
        wv1.getSettings().setOffscreenPreRaster(true);

        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.getSettings().setBuiltInZoomControls(true);
        wv1.getSettings().setDisplayZoomControls(false);

        // wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(url);

        //wv1=(WebView)findViewById(R.id.web_view);
        wv1.setWebChromeClient(new MyBrowser());
        //wv1.setWebViewClient(new MyBrowser());
        //wv1.setWebViewClient(new WebViewClient() );

    }

    private class MyBrowser extends WebChromeClient {
        //  @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wv1.canGoBack()) {
                        wv1.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}