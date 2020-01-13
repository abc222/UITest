package com.uitest.webviewtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.uitest.R;

public class WebViewTestActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "WebViewTestActivity";
    private static String url = "http://13.112.253.115:8000/test";
    private RelativeLayout webviewContent;
    private ImageView imageView;
    private ViewGroup.LayoutParams lp_500;

    private String testJs = "<script type='text/javascript' src='https://appsrv.display.io/tagman?p=4565&t=1&idfa=728589b0-7f2e-449e-9179-7b46ebbc8f35&a=com.dianxinos.dxbs&dnt=false&lat=38.9326&lng=-77.1706'></script>\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);
        findViewById(R.id.webview_visible_500).setOnClickListener(this);
        findViewById(R.id.webview_visible_wrap).setOnClickListener(this);
        findViewById(R.id.webview_invisible_500).setOnClickListener(this);
        findViewById(R.id.webview_invisible_wrap).setOnClickListener(this);
        findViewById(R.id.webview_multi_visible_500).setOnClickListener(this);
        findViewById(R.id.webview_multi_visible_wrap).setOnClickListener(this);
        findViewById(R.id.webview_multi_invisible_500).setOnClickListener(this);
        findViewById(R.id.webview_multi_invisible_wrap).setOnClickListener(this);
        webviewContent = (RelativeLayout) findViewById(R.id.webview_content);
        imageView = (ImageView) findViewById(R.id.imageView);
        lp_500 = new ViewGroup.LayoutParams(1000, 1000);
        // 500dp换算成px，得1500
//        final float scale =this. getResources().getDisplayMetrics().density;
//        int px= (int) (500 * scale + 0.5f);
//        Log.d(TAG,"px: " + px);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.webview_visible_500:
                Intent intent = new Intent(WebViewTestActivity.this,ShowAdActivity.class);
                startActivity(intent);
                imageView.setVisibility(View.GONE);
//                try {
//                    WebView webView1 = new WebView(this);
//                    webView1.setLayoutParams(lp_500);
//                    setWebView(webView1);
//                    Log.e("fffpzf","webView1:"+",url:"+url);
//                    //webView1.loadUrl(url);
//                    webView1.loadDataWithBaseURL("http://" + "duapps" + "/",
//                            testJs, "text/html", "UTF-8", null);
//                    webviewContent.removeAllViews();
//                    webviewContent.addView(webView1);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Log.e("fffpzf","e:"+e.getMessage());
//                }
//                for(int i = 0;i < 5;i ++) {
//
//                }
//                WebView webView = new WebView(this);
//                webView.setLayoutParams(lp_500);
//                WebSettings ws = webView.getSettings();
//                // 网页内容的宽度是否可大于WebView控件的宽度
//                ws.setLoadWithOverviewMode(true);
//                // 启动应用缓存
//                ws.setAppCacheEnabled(false);
//                ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
//                ws.setJavaScriptEnabled(true);
//                webView.setWebViewClient(new WebViewClient(){
//                    @Override
//                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                        Log.e("wangyuchao","onPageStarted：" + url);
//                        super.onPageStarted(view, url, favicon);
//                    }
//
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
////                        return super.shouldOverrideUrlLoading(view, request);
//                        Log.e("wangyuchao","shouldOverrideUrlLoading：" + request);
//                        view.loadUrl(url);
//                        return true;
//                    }
//
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
//                        Log.e("wangyuchao","onPageFinished：" + url);
//                        super.onPageFinished(view, url);
//                    }
//                });
//
//                webView.setWebChromeClient( new WebChromeClient(){
//                    @Override
//                    public void onProgressChanged(WebView view, int newProgress) {
//                        Log.e("wangyuchao","onProgressChanged：" + newProgress);
//                        super.onProgressChanged(view, newProgress);
//                    }
//                });
//                webView.loadDataWithBaseURL("http://" + "duapps" + "/",
//                        testJs, "text/html", "UTF-8", null);
//                webviewContent.removeAllViews();
//                webviewContent.addView(webView);
                break;
            case R.id.webview_visible_wrap:
                imageView.setVisibility(View.GONE);
                WebView webView2 = new WebView(this);
                //webView2.setLayoutParams(lp_500);
                WebSettings ws2 = webView2.getSettings();
                // 网页内容的宽度是否可大于WebView控件的宽度
                ws2.setLoadWithOverviewMode(true);
                // 启动应用缓存
                ws2.setAppCacheEnabled(false);
                ws2.setCacheMode(WebSettings.LOAD_NO_CACHE);
                ws2.setJavaScriptEnabled(true);
                webView2.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        Log.e("wangyuchao","onPageStarted：" + url);
                        super.onPageStarted(view, url, favicon);
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                        return super.shouldOverrideUrlLoading(view, request);
                        Log.e("wangyuchao","shouldOverrideUrlLoading：" + request);
                        view.loadUrl(url);
                        return true;
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        Log.e("wangyuchao","onPageFinished：" + url);
                        super.onPageFinished(view, url);
                    }
                });
                webView2.setWebChromeClient( new WebChromeClient(){
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        Log.e("wangyuchao","onProgressChanged：" + newProgress);
                        super.onProgressChanged(view, newProgress);
                    }
                });
                webView2.loadDataWithBaseURL("http://" + "duapps" + "/",
                        testJs, "text/html", "UTF-8", null);
                webviewContent.removeAllViews();
                webviewContent.addView(webView2);
//                WebView webView2 = new WebView(this);
//                setWebView(webView2);
//                webView2.loadUrl(url);
//                webviewContent.removeAllViews();
//                webviewContent.addView(webView2);
                break;
            case R.id.webview_invisible_500:
                imageView.setVisibility(View.GONE);
                WebView webView3 = new WebView(this);
                webView3.setLayoutParams(lp_500);
                setWebView(webView3);
                webView3.loadUrl(url);
                webView3.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        Log.e("fffpzf","onPageStarted："+url);
                        super.onPageStarted(view, url, favicon);
                    }

                    @Override
                    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                        super.onReceivedError(view, request, error);
                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                        return super.shouldOverrideUrlLoading(view, request);
                        view.loadUrl(url);
                        return true;
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        Log.e("fffpzf","webview_invisible_500："+url);
                        view.loadUrl("javascript:window.java_obj.getSource('<head>'+" +
                                "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                        super.onPageFinished(view, url);
                    }
                });
                webviewContent.removeAllViews();
                webviewContent.addView(webView3);
                break;
            case R.id.webview_invisible_wrap:
                imageView.setVisibility(View.GONE);
                WebView webView4 = new WebView(this);
                setWebView(webView4);
                webView4.loadUrl(url);
                webviewContent.removeAllViews();
                break;
            case R.id.webview_multi_visible_500:
                imageView.setVisibility(View.VISIBLE);

                try {
                    WebView multi1_1 = new WebView(this);
                    multi1_1.setLayoutParams(lp_500);
                    setWebView(multi1_1);
                    multi1_1.loadUrl(url);

                    WebView multi1_2 = new WebView(this);
                    multi1_2.setLayoutParams(lp_500);
                    setWebView(multi1_2);
                    multi1_2.loadUrl(url);
                    webviewContent.removeAllViews();
                    webviewContent.addView(multi1_1);
                    webviewContent.addView(multi1_2);

//                    WebSettings ws = webView.getSettings();
//                    // 网页内容的宽度是否可大于WebView控件的宽度
//                    ws.setLoadWithOverviewMode(false);
//                    // 启动应用缓存
//                    ws.setAppCacheEnabled(true);
//                    ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//                    ws.setJavaScriptEnabled(true);
//                    webView.setWebViewClient(new WebViewClient());
//                    webView.setWebChromeClient(new WebChromeClient());


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("fffpzf","webview_multi_visible_500:"+e.getMessage());
                }
//                WebView multi1_3 = new WebView(this);
//                multi1_3.setLayoutParams(lp_500);
//                setWebView(multi1_3);
//                multi1_3.loadUrl(url);
//                webviewContent.removeAllViews();
//                webviewContent.addView(multi1_1);
//                webviewContent.addView(multi1_2);
//                webviewContent.addView(multi1_3);
                break;
            case R.id.webview_multi_visible_wrap:
                imageView.setVisibility(View.VISIBLE);
                WebView multi2_1 = new WebView(this);
                setWebView(multi2_1);
                multi2_1.loadUrl(url);
                WebView multi2_2 = new WebView(this);
                setWebView(multi2_2);
                multi2_2.loadUrl(url);
//                WebView multi2_3 = new WebView(this);
//                setWebView(multi2_3);
//                multi2_3.loadUrl(url);
                webviewContent.removeAllViews();
                webviewContent.addView(multi2_1);
                webviewContent.addView(multi2_2);
//                webviewContent.addView(multi2_3);
                break;
            case R.id.webview_multi_invisible_500:
                imageView.setVisibility(View.VISIBLE);
                WebView multi3_1 = new WebView(this);
                multi3_1.setLayoutParams(lp_500);
                setWebView(multi3_1);
                multi3_1.loadUrl(url);
                WebView multi3_2 = new WebView(this);
                multi3_2.setLayoutParams(lp_500);
                setWebView(multi3_2);
                multi3_2.loadUrl(url);
                WebView multi3_3 = new WebView(this);
                multi3_3.setLayoutParams(lp_500);
                setWebView(multi3_3);
                multi3_3.loadUrl(url);
                webviewContent.removeAllViews();
                break;
            case R.id.webview_multi_invisible_wrap:
                imageView.setVisibility(View.VISIBLE);
                WebView multi4_1 = new WebView(this);
                setWebView(multi4_1);
                multi4_1.loadUrl(url);
                WebView multi4_2 = new WebView(this);
                setWebView(multi4_2);
                multi4_2.loadUrl(url);
                WebView multi4_3 = new WebView(this);
                setWebView(multi4_3);
                multi4_3.loadUrl(url);
                webviewContent.removeAllViews();
                break;
            default:
                break;
        }
    }

    private void setWebView(WebView webView) {
        WebSettings ws = webView.getSettings();
        // 网页内容的宽度是否可大于WebView控件的宽度
        ws.setLoadWithOverviewMode(true);
        // 启动应用缓存
        ws.setAppCacheEnabled(false);
//        ws.setCacheMode(WebSettings.LOAD_CACHE_ONLY);

//        ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        ws.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
    }

//    private void setWebView(WebView webView,boolean OverviewMode,boolean cacheEnable,int cacheMode) {
//        WebSettings ws = webView.getSettings();
//        // 网页内容的宽度是否可大于WebView控件的宽度
//        ws.setLoadWithOverviewMode(OverviewMode);
//        // 启动应用缓存
//        ws.setAppCacheEnabled(cacheEnable);
//        ws.setCacheMode(cacheMode;
//        ws.setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
//    }

}

/**
 * 逻辑处理
 * @author linzewu
 */
final class InJavaScriptLocalObj {
    @JavascriptInterface
    public void getSource(String html) {
        Log.d("html=", html);
    }
}