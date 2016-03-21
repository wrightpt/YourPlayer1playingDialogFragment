package com.yourplayer.c.yourplayer1;

import android.content.res.*;
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
public class DialogFragment1 extends DialogFragment implements YouTubePlayer.OnInitializedListener{

    YouTubePlayerSupportFragment mYouTubePlayerFragment;

    String mNum;
   // String frameVideo = "<html><body>Youtube video .. <br> <iframe width="320" height="315" src="https://www.youtube.com/embed/lY2H2ZP56K4" frameborder="0" allowfullscreen></iframe></body></html>";
   // WebView myWebView;
   final static String myBlogAddr = "https://www.youtube.com/watch?v=YQHsXMglC9A";
    String myUrl;


    static DialogFragment1 newInstance(String num) {
        DialogFragment1 f = new DialogFragment1();
        Log.d("num",num);
        Bundle args = new Bundle();
        args.putString("num", num);
        f.setArguments(args);


        return f;


}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getString("num");
        Log.d("mNum",mNum);



}

   String b = mNum;

  // String c = "src=\"https://www.youtube.com/embed/%s=0" ;
  //  String result1 = String.format(c,b);
 //  String youtubeHTML = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/vWZ2W86JkME?rel=0\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
   // String youtubeHTML1 = "<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/uO59tfQ2TbA\" frameborder=\"0\" allowfullscreen></iframe></body></html>";




  //  String n = youtubeHTML.replaceAll("vWZ2W86JkME?rel=0",b);

   // String result = String.format(youtubeHTML,result1 );


    //public  String getHTML(String videoid) {
      //  String videoid =


    //    String html2 = "<iframe class=\"youtube-player\" style=\"overflow:hidden; width: 100%; height: 95%; scrolling=\"no\" padding:0px; margin:0px\" id=\"ytplayer\" type=\"text/html\" src=\"https://www.youtube.com/embed/"
    //            + mNum
    //            + "?html5=1&autoplay=1 & fs=0\" frameborder=\"0\" >\n"
    //            + "</iframe>\n ";

       // return html2;
  //  }





    //public static String insert(String bag, String marble, int index) {
        //String bagBegin = bag.substring(0,index);
       // String bagEnd = bag.substring(index);
        //return bagBegin + marble + bagEnd;

   // }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String a = mNum;
        final View fragmentYoutubeView = inflater.inflate(R.layout.fragment_youtube, container, false);
        mYouTubePlayerFragment = new YouTubePlayerSupportFragment();
        mYouTubePlayerFragment.initialize(YoutubeConnector.KEY,  this);
       FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_youtube_player, mYouTubePlayerFragment);
        fragmentTransaction.commit();

     //   mYoutubeVideoTitle = (TextView)fragmentYoutubeView.findViewById(R.id.fragment_youtube_title);
     //   mYoutubeVideoDescription = (TextView)fragmentYoutubeView.findViewById(R.id.fragment_youtube_description);

     //   mYoutubeVideoTitle.setText(getArguments().getString(Resources.KEY_VIDEO_TITLE));
      //  mYoutubeVideoDescription.setText(getArguments().getString(Resources.KEY_VIDEO_DESC));

      //  VideoFragment.setTextToShare(getArguments().getString(Resources.KEY_VIDEO_URL));

        return fragmentYoutubeView;

       









        //   View v = inflater.inflate(R.layout.layout_webfragment, container, false);
    //    WebView myWebView = (WebView) v.findViewById(R.id.mywebview);

     //  WebSettings settings = myWebView.getSettings();

        //settings.setJavaScriptEnabled(true);
      //  myWebView.getSettings().setJavaScriptEnabled(true);
       // myWebView.getSettings().setDomStorageEnabled(true);

       // myWebView.setWebChromeClient(new MyChromwClient() );
     //   myWebView.setWebChromeClient(new WebChromeClient());
        //myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
       // myWebView.setWebViewClient(new MyWebviewClient());
   //   myWebView.setWebViewClient(new WebViewClient());
       // myWebView.addJavascriptInterface(new MyJavaScriptInterface(new));
    //    myWebView.addJavascriptInterface(new MyJavaScriptInterface(this), "JSHandler");
   //     myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //myWebView.addJavascriptInterface(new MyJavaScriptInterface(this), "JSHandler");
      //  webView.loadUrl("file:///android_asset/ytplayer.html");
        //myWebView.loadUrl(a);
        //myWebView.loadData(frameVideo, "text/html", "utf-8");
       // myWebView.loadDataWithBaseURL();
       // myWebView.loadDataWithBaseURL("https://www.youtube.com", youtubeHTML, "text/html; charset=utf-8", "UTF-8", null);
   //     StringBuilder sb;
  //      sb = new StringBuilder(32);
   //     sb.append("<html><body><iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/");
  //      sb.append(mNum);


   //   sb.append("\" frameborder=\"0\" allowfullscreen></iframe></body></html>");
      // String nonyou;
      //  sb.toString(nonyou);
      //myWebView.loadDataWithBaseURL("https://www.youtube.com", youtubeHTML, "text/html; charset=utf-8", "UTF-8", null);
      //  myWebView.loadDataWithBaseURL("https://www.youtube.com", sb.toString(), "text/html; charset=utf-8", "UTF-8", null);
    //    myWebView.loadDataWithBaseURL("https://www.youtube.com", n, "text/html; charset=utf-8", "UTF-8", null);
       // myWebView.loadDataWithBaseURL("https://www.youtube.com", html2, "text/html; charset=utf-8", "UTF-8", null);

       // myWebView.loadDataWithBaseURL("", html2, "text/html","UTF-8", "");
        //
        // myWebView.loadData(youtubeHTML1,"text/html; charset=utf-8", "UTF-8");
       // myWebView.loadUrl("https://www.youtube.com/embed/" + mNum);


        //View tv = v.findViewById(R.id.text);
        //((TextView)tv).setText("Dialog #" + mNum + ": using style "
          //      + getNameForNum(mNum));

   //     return v;


}

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if(!b){
            youTubePlayer.cueVideo("aLnVhgYdVd8");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {


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
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;




             //   @Override
             //   public void onReceivedError(WebView view, int errorCode,
              //                              String description, String failingUrl) {
                    // TODO Auto-generated method stub
               //     super.onReceivedError(view, errorCode, description, failingUrl);

                    //Log.d(TAG, "onReceivedError : description = " + description);

                }


                    }








            }
