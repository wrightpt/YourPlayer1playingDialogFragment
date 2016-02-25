package com.yourplayer.c.yourplayer1;

import android.os.*;
import android.support.v4.app.*;
import android.util.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;

import com.google.android.youtube.player.*;
import com.google.api.services.youtube.*;

/**
 * Created by c on 2/24/16.
 */
public class DialogFragment1 extends DialogFragment {

    String mNum;
   // String frameVideo = "<html><body>Youtube video .. <br> <iframe width="320" height="315" src="https://www.youtube.com/embed/lY2H2ZP56K4" frameborder="0" allowfullscreen></iframe></body></html>";
   // WebView myWebView;
   String youtubeHTML = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vWZ2W86JkME?rel=0\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
   final static String myBlogAddr = "https://www.youtube.com/watch?v=YQHsXMglC9A";
    String myUrl;


    static DialogFragment1 newInstance(String num) {
        DialogFragment1 f = new DialogFragment1();

        Bundle args = new Bundle();
        args.putString("num", num);
        f.setArguments(args);

        return f;


}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getString("num");



}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String a = mNum;
        View v = inflater.inflate(R.layout.layout_webfragment, container, false);
        WebView myWebView = (WebView) v.findViewById(R.id.mywebview);

       WebSettings settings = myWebView.getSettings();
        //settings.setJavaScriptEnabled(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);

        myWebView.setWebChromeClient(new MyChromwClient() );
        //myWebView.setWebChromeClient(new WebChromeClient());
        //myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        myWebView.setWebViewClient(new MyWebviewClient());
       // myWebView.setWebViewClient(new WebViewClient());
       // myWebView.addJavascriptInterface(new MyJavaScriptInterface(new));
        myWebView.addJavascriptInterface(new MyJavaScriptInterface(this), "JSHandler");
        myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //myWebView.addJavascriptInterface(new MyJavaScriptInterface(this), "JSHandler");
      //  webView.loadUrl("file:///android_asset/ytplayer.html");
        //myWebView.loadUrl(a);
        //myWebView.loadData(frameVideo, "text/html", "utf-8");
       // myWebView.loadDataWithBaseURL();
        myWebView.loadDataWithBaseURL("https://www.youtube.com", youtubeHTML, "text/html; charset=utf-8", "UTF-8", null);


        //View tv = v.findViewById(R.id.text);
        //((TextView)tv).setText("Dialog #" + mNum + ": using style "
          //      + getNameForNum(mNum));

        return v;


}



    private class MyChromwClient extends WebChromeClient{
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            // TODO Auto-generated method stub
//			Log.d(TAG, "consoleMessage : " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }

}


    public class MyJavaScriptInterface{


        private DialogFragment1 activity;

        public MyJavaScriptInterface(DialogFragment1 activity){

            this.activity = activity;
        }}






            private class MyWebviewClient extends WebViewClient{

                @Override
                public void onReceivedError(WebView view, int errorCode,
                                            String description, String failingUrl) {
                    // TODO Auto-generated method stub
                    super.onReceivedError(view, errorCode, description, failingUrl);

                    //Log.d(TAG, "onReceivedError : description = " + description);

                }


                    }








            }
