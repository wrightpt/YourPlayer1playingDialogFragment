package com.yourplayer.c.yourplayer1;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.v4.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;

/**
 * Created by c on 4/18/16.
 */
public class DialogFragment2 extends android.support.v4.app.DialogFragment implements View.OnTouchListener {

    int mNum;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     * @param num
     */
    static DialogFragment2 newInstance(String num) {
        DialogFragment2 f = new DialogFragment2();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("num", num);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mNum = getArguments().getInt("num");

       // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch ((mNum-1)%6) {
            case 1: style = DialogFragment.STYLE_NO_TITLE; break;
            case 2: style = DialogFragment.STYLE_NO_FRAME; break;
            case 3: style = DialogFragment.STYLE_NO_INPUT; break;
            case 4: style = DialogFragment.STYLE_NORMAL; break;
            case 5: style = DialogFragment.STYLE_NORMAL; break;
            case 6: style = DialogFragment.STYLE_NO_TITLE; break;
            case 7: style = DialogFragment.STYLE_NO_FRAME; break;
            case 8: style = DialogFragment.STYLE_NORMAL; break;
        }
        switch ((mNum-1)%6) {
            case 4: theme = android.R.style.Theme_Holo; break;
            case 5: theme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 6: theme = android.R.style.Theme_Holo_Light; break;
            case 7: theme = android.R.style.Theme_Holo_Light_Panel; break;
            case 8: theme = android.R.style.Theme_Holo_Light; break;
        }
      //  setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Dialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);

        final WindowManager wm = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        //Context.getSystemService(Context.WINDOW_SERVICE);

        final LinearLayout fl = (LinearLayout)v.findViewById(R.id.linearlayoutfragmentdialog);

        //   mYoutubeVideoTitle = (TextView)fragmentYoutubeView.findViewById(R.id.fragment_youtube_title);
        //   mYoutubeVideoDescription = (TextView)fragmentYoutubeView.findViewById(R.id.fragment_youtube_description);

        //   mYoutubeVideoTitle.setText(getArguments().getString(Resources.KEY_VIDEO_TITLE));
        //  mYoutubeVideoDescription.setText(getArguments().getString(Resources.KEY_VIDEO_DESC));

        //  VideoFragment.setTextToShare(getArguments().getString(Resources.KEY_VIDEO_URL));
       // final WindowManager wm = getActivity().getWindowManager();



        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(

                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        wm.addView(fl,params);

        fl.setOnTouchListener(new View.OnTouchListener() {

            private WindowManager.LayoutParams paramsF = params;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("thisisit","thisisit");
               // if(event.getAction() == MotionEvent.ACTION_MOVE){
               switch (event.getAction() ){   //== MotionEvent.ACTION_MOVE) {
                    case MotionEvent.ACTION_DOWN:

                        // Get current time in nano seconds.

                        initialX = paramsF.x;
                        initialY = paramsF.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        paramsF.x = initialX + (int) (event.getRawX() - initialTouchX);
                        paramsF.y = initialY + (int) (event.getRawY() - initialTouchY);
                        Log.d("drag","drag");
                        wm.updateViewLayout(fl, paramsF);
                        //windowManager.updateViewLayout(layout,paramsF);
                        // fragmentYoutubeView.;
                        break;
                }
                return true;
            }
        });




        return v;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("ontouch2", "ontouch2");
        return false;
    }
}


