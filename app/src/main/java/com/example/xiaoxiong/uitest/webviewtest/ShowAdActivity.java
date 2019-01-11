package com.example.xiaoxiong.uitest.webviewtest;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.example.xiaoxiong.uitest.R;

public class ShowAdActivity extends AppCompatActivity {

    private String testJs = "<img src='https://imp2.track.tappx.com/validate?pg=ZZ28760DR1532417906&sz=320x50&ni=68&sdk=&bgd=0&id=QqtZJ_hWIs_w2mYCjXmEvbSeay2ak4q1AkygwiRvcTOIYUdfenjFFVYL_RzVmbqg0fFAJkZDx3EeQjdSRFTxl0tnJkBbAIbQzrUrl8944GKrq2j_SWceRL1IrzOQkBzlePM3yj70IYmWkkatWVlx7U2jRI1jTXd-QMgBx3IwsFscYiLRY6O22WjIr2QEpx5P4ZAEnL581_fyvTtzNl33mv9vvbYuzmMWZtPZvdFP3I-97dRsiIwjtAIiwrryfv7mP1kW44nTi7ck7hdsBk4CuzZG1-EjnSScy-EYWhVNLBgJ0GMUngM9Z9-vUFETMzzHOb_AHkKHsZwrLAQ6leT9rSBvBtnQEFCIzz0ljYL6Ux0EBbPCSnQ0cPd0bjhQzfv_cj3rC6CzbAYd2Lxv27Eqo7to83HX-m5B8Mbn6lMU-amkgEteKKdzvo8z40ZorXSWDEPzCGjlkAvEASWLOIW7kK9GHaPexDyDJhggK2Eg6ZMIA5Wemlts4RxMT6iYvERl1a-wBCQBF1aaVQSW5JXvTBm_V1eFYH5HhLwI5BYOWGT_kixWxPIBvvDteEUHOqXas5PDkX-o_B5ZW7Zy2HcNcQ' width='1' height='1' style='visibility: hidden; width: 1px;height: 1px;position: fixed;'/>\n" +
            "<!DOCTYPE html>\n" +
            "<html class='httppx' lang='en' xmlns='http://www.w3.org/1999/xhtml'>\n" +
            "\n" +
            "<head>\n" +
            "    <meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0'  />\n" +
            "    <meta charset='utf-8'  />\n" +
            "    <title>:tappx</title> <style type='text/css'>html.httppx, body.bdtppx, table.tbltppx, tr.trtppx, td.tdtppx { -webkit-touch-callout: none; overflow:hidden; border: 0; margin: 0; padding: 0; text-align: center; vertical-align: middle; color: white !important; } html.httppx { margin: -1px !important; background-color: transparent !important; } html.httppx, body.bdtppx, table.tbltppx, tr.trtppx, td.tdtppx { height: 100%; width: 100%; }</style> </head> <body class=\"bdtppx\" style=\"background-color:black\"><a href=\"http://omax.admarvel.com/rtb/ck?p=__pid%3Dc490f6e7399a25d6__sid%3D177366%7C177367%7C75004__bid%3D1482828%7C986411%7C986411__cb%3D91aa14f3-97c8-44f1-9b1f-b5e50151cd2f__h%3D1544092172__rbid%3D40__ek%3D988a81400c9037ce6442551996f3733c_20181206_10%3Aa9e9c3583cfe0b61edd094c0e5133b55_20181206_10__r%3DUS5vd3RUWncjcy5Sd3NSenV1fHYucnRTU3d2I08uT09RT3Qyd3xTLnhzUFIzeiN1LnJ2dnR3elY%3D__s%3D196a3bade2a078d2f31ea4cf3c116ca6__appid%3Dd68cf9502c075658330a10b701b52e78__maxdest%3Dhttps%253A%252F%252Ft.manage.com%252F22870%253Fbts%253D1544092172%2526pt%253Dapp%2526pi%253D97f0deaac5d9a5d0de6d9e261943e435%2526p%253DDU%252BFlashlight%252B-%252BBrightest%252BLED%252B%252526amp%25253B%252BFlashlight%252BFree%252B-%252BApps%252Bon%252BGoogle%252BPlay%2526pb%253DDU%252BApps%2526pbi%253Dd68cf9502c075658330a10b701b52e78%2526bnd%253Dcom.dianxinos.flashlight.duplay%2526sz%253D2%2526city%253D%2526country%253DUSA%2526gender%253D%25253F%2526_ip%253D1757501643%2526isp%253DBeijing%252BBaidu%252BNetcom%252BScience%252Band%252BTechnology%252BCo.%2526netspd%253Dwifi%2526model%253DNexus%252B5%2526sk%253D0%2526os%253DAndroid%2526osv%253D5.1.1%2526region%253D%25253F%2526zip%253D%2526dma%253D%25253F%2526si%253D47%2526_uh%253Dgaid%25253A331abca0-ba7e-4b16-8fb3-ed567f7e7920%2526bidid%253D9RHXj2kFvFva47Cs8-ebmazjoyg%2526atb%253D33%2526sub4%253D335-b%2526sub9%253D%2526host%253Dweb91-east%2526sdkv%253D%2526lt%253D%25253F.%25253F%2526ff%253Dandroid_phone%2526dvmo%253DGoogle%252BNexus%252B5%2526sl%253D800%2526ss%253D1544091372%2526l%253Den%2526uhgid%253D70%2526at%253D2%2526rw%253D0%2526acat%253DTools%2526pr2%253D0.03%2526g4%253D0%2526g4c%253D0%2526idx%253D1%2526agidx%253D1%2526ai%253D916425%2526ag%253D13070%2526ci%253D1270%2526cu%253DUSD%2526bid%253D0.00028%2526pr%253D1%2526dealid%253D%2526mi1%253D217307%2526mi2%253D217331%2526mp1%253D0.00692%2526mp2%253D0.01071%2526uimp%253D0%2526uclk%253D0%2526uins%253D0%2526useg%253D0%2526mid%253D217331%2526cm%253D2%2526pr1%253D2%2526vte%253D1%2526ece%253D0\"><img src=\"http://cdn.manage.com/1270/m_WIS_320x50_SMix.gif\" width=\"320\" height=\"50\"></a><img height=\"1\" width=\"1\" src=\"https://gum.criteo.com/sid/pixel?gaid=331abca0-ba7e-4b16-8fb3-ed567f7e7920&displayid=manage_47_9RHXj2kFvFva47Cs8-ebmazjoyg&origin=display&arbitrageId=9RHXj2kFvFva47Cs8-ebmazjoyg&cb=1544092172.1644\"><img src=\"http://adcolony-east-bidder.manage.com/2/win?_ei=22869&_cost1000=0.077&bts=1544092172&pt=app&pi=97f0deaac5d9a5d0de6d9e261943e435&p=DU+Flashlight+-+Brightest+LED+%26amp%3B+Flashlight+Free+-+Apps+on+Google+Play&pb=DU+Apps&pbi=d68cf9502c075658330a10b701b52e78&bnd=com.dianxinos.flashlight.duplay&sz=2&city=&country=USA&gender=%3F&_ip=1757501643&isp=Beijing+Baidu+Netcom+Science+and+Technology+Co.&netspd=wifi&model=Nexus+5&sk=0&os=Android&osv=5.1.1&region=%3F&zip=&dma=%3F&si=47&_uh=gaid%3A331abca0-ba7e-4b16-8fb3-ed567f7e7920&bidid=9RHXj2kFvFva47Cs8-ebmazjoyg&atb=33&sub4=335-b&sub9=&host=web91-east&sdkv=&lt=%3F.%3F&ff=android_phone&dvmo=Google+Nexus+5&sl=800&ss=1544091372&l=en&uhgid=70&at=2&rw=0&acat=Tools&pr2=0.03&g4=0&g4c=0&idx=1&agidx=1&ai=916425&ag=13070&ci=1270&cu=USD&bid=0.00028&pr=1&dealid=&mi1=217307&mi2=217331&mp1=0.00692&mp2=0.01071&uimp=0&uclk=0&uins=0&useg=0&mid=217331&cm=2&pr1=2&vte=1&ece=0&acid=91aa14f3-97c8-44f1-9b1f-b5e50151cd2f\" width=\"1\" height=\"1\" /><img src=\"http://omax.admarvel.com/rtb/view?p=__pid=c490f6e7399a25d6__sid=177366|177367|75004__bid=1482828|986411|986411__cb=91aa14f3-97c8-44f1-9b1f-b5e50151cd2f__h=1544092172__rbid=40__ek=988a81400c9037ce6442551996f3733c_20181206_10:a9e9c3583cfe0b61edd094c0e5133b55_20181206_10__r=US5vd3RUWncjcy5Sd3NSenV1fHYucnRTU3d2I08uT09RT3Qyd3xTLnhzUFIzeiN1LnJ2dnR3elY=__s=196a3bade2a078d2f31ea4cf3c116ca6__appid=d68cf9502c075658330a10b701b52e78__crid=916425__cid=1270__preqid=__pth=0__wp=0.0563\" width=\"1\" height=\"1\" /><script src=\"http://admarvel.s3.amazonaws.com/js/common/action/ad_tracking/track_ad_viewable_lib.js?cb=91aa14f3-97c8-44f1-9b1f-b5e50151cd2f\"></script><script id=\"omwViewabilityLoader\">!function(){function p(){OMW.performAction&&OMW.performAction(l,m)}function q(a,b){var c=document.createElement(\"script\");c.addEventListener(\"load\",b),c.src=a,document.getElementsByTagName(\"head\")[0].appendChild(c)}window.OMW=window.OMW||{};var a=\"91aa14f3-97c8-44f1-9b1f-b5e50151cd2f\";a.indexOf(\"{\")!==-1&&(a=Math.floor(1e5*Math.random()));var b=\"banner\";b.indexOf(\"{\")!==-1?b=\"banner\":\"interstitial\"!==b&&(b=\"banner\");var c=\"WAP\",d=!0;c.indexOf(\"{\")!==-1?d=!0:\"iphone\"===c||\"android\"===c?d=!1:\"WAP\"===c&&(d=!0);var e=\"\",f=document.getElementById(\"omwViewabilityLoader\");if(f&&(f.id=\"\"),d){var g=document.createElement(\"div\");e=\"omw-\"+a,g.id=e,g.style.height=\"0px\",f.parentNode.insertBefore(g,f)}var h=\"http://omax.admarvel.com/rtb/et?d=73XpV1HdqsHDdlxhmfLcrgK8VMlj9EqlDAZAMmQTrwIxeN3nIdsnmeqhxWqcnE2EKPONizL0sDv8xTq2QvmuRXpc4hQngaVBD1amEXimBe03zYWEx7OdnfTa9jq4Kf6vz3UGXXJ4v5z7umjxLYeGU8xXmqXSnefIaabFb8o9fsvucpLTYhUahSnoIDgTc4gtccElJ8uPKRN3RAYXYpTGxtIWiN17UJEDbzVcUbnbWCX5rEKyPyfo4TmhYhSvcRrGl5PMT8DJU1PPosHM8s8KOSaaxFcNevWFpvolX9SFpIfaedxOpKGsYiX4\",i=\"http://omax.admarvel.com/rtb/et?d=GGUVeI4EadKCwVWEjmrjoNzTF3dt4x8Ci1bjhgJQVVnnJxMXPaOHIvaRwbQnedFHc1IJBiRh9vUfi1c4Th2OOjN8uboLT85ScM3tmSJVX2YJCZCok9LUFXPdVvertAPaNnuUAR2JeNZNnx4XPdsmEssMGxvJP7556uYlzHmKqO8l6elVdn9G8EXElgvpqQfYx9sgJKLhePzhaJWyiLyxsg4G66udjLMDFutDYyK06ttqTcQCQsVz8B4mffMarbCT7KxOjhzjLMrbCRsaFv0hUBZ4d1ZoXs5gj2WCpq8BK5k7UKiGGmmdv8p91F7SlbW0jT2nA\",j=\"{event_url_videoviewable}\",k=\"{event_url_videoviewablemeasurable}\",l=\"trackAdViewable\",m={adType:b,mobileWeb:d,pixels:{adViewable:[[h,{percentage:50,duration:1}]],adViewableMeasurable:[i],videoViewable:[[j,{percentage:50,duration:2}]],videoViewableMeasurable:[k]}};if(d)m.adInstanceId=a,m.adElementId=e,m.virtualHeight=50;else{var n=\"\";n.indexOf(\"{\")===-1&&(m.sdkVersionNumeric=parseInt(n))}var o=\"http://admarvel.s3.amazonaws.com/js/common/action/ad_tracking/track_ad_viewable_lib.js\";OMW.performAction?p():q(o,p)}();</script><img src=\"http://omax.admarvel.com/rtb/et?d=5tITZBTOPKZ5PAWDYop1NtnH9lKktk3hRiP7VHgn2pnDwZyUjEtKpb43Effmct9T6Du1JdAIehSxBl7rgYqvfjOuWXWI6zvfkThXixa4dhETsKodXnCcPcYHfxGmW8K65TkuI05NeBp0UWsFTEqm2QhkIJK6z2vgo5FAi07QvanHdv9t00wYP7V5eXrrhPb7s2gCfbaflPt4qu0qirEknrPgBE7xC0H5ZJwUPAiNSqI62bXh3fORtKWYBHchvQMkrsZnjhRl6Qa8h3gBtLtbhejcz7gbR5ODpe90IRT7LjrRbJf8\" height=\"1\" width=\"1\"></body></html><img src='https://imp2.track.tappx.com/validate?pg=ZZ28760DR1532417906&sz=320x50&ni=68&sdk=&bgd=0&ia=t&id=S6ye9Ly12nUgCJPbWo4j1jwI8pkrJqgdu3cAZRyh2fpImGwAG5OivTh6ozM6i4MYpVVbVkn_zmrPKPhP84TewmIamz_-Hr08XM3ywDB7r53-u_4ABycDCU8KL7MuPXJKuEIKtubcZIdyhuMTZ_n1AuVBn_SxghYHwwx0wrWBC3xsPZUMkmluBFjII8RtmITfb6SNuX-J66bE6nyj8-2B6wBmv_7Uqti3unHSUN1z0hdJbpI8QgO3ZHlck5haDdms8k79eJQfv4y916Bf5lr9S3E70sEWZ18Be1Yn7yFTOsKImcIzTr87uN3tfGcm4t5M_sNdvF3LUvGaz_AICKZOqAP89zVXPjtfl_MoXh4HIqHcXVlmFNJo1Y7Uxgv0gKdJNZvmxhrpipKtYEUc6N2v8GuSWcatup1hW7Zr_eyGJ3vpaTtFKRv01CvmX-t5PRTI6v33Mmju97BUoG8_o6y91w1RHCk8OsubnhVDy5g2InzyDifNRxv9desmmvGYMXbvlYfY3wEIfCLDNHLpoauvtdndjaXxe4-SnJ6XAQV64AXjpEl-e_99hUxug1Dtm8vl9MZbnU-qLmtSSTFz0g-Pmg' width='1' height='1' style='visibility: hidden; width: 1px;height: 1px;position: fixed;'/>";

    FrameLayout container;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ad);
        container = (FrameLayout)findViewById(R.id.container);

        webView = new WebView(this);
        WebSettings ws = webView.getSettings();
        // 网页内容的宽度是否可大于WebView控件的宽度
        ws.setLoadWithOverviewMode(true);
        // 启动应用缓存
        ws.setAppCacheEnabled(false);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        ws.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("wangyuchao","onPageStarted：" + url);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                        return super.shouldOverrideUrlLoading(view, request);
                Log.e("wangyuchao","shouldOverrideUrlLoading：" + request);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e("wangyuchao","onPageFinished：" + url);
                super.onPageFinished(view, url);
            }
        });

        webView.setWebChromeClient( new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.e("wangyuchao","onProgressChanged：" + newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.loadDataWithBaseURL("http://" + "duapps" + "/",
                testJs, "text/html", "UTF-8", null);
        container.removeAllViews();
//        container.addView(webView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        container.addView(webView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("wangyuchao","onStop");
        webView.destroy();
        webView = null;

        Log.d("wangyuchao","commit1");

    }
}
