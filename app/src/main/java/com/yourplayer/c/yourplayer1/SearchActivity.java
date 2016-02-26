package com.yourplayer.c.yourplayer1;

import android.content.*;
import android.net.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v7.app.AppCompatActivity;
import android.util.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;

import com.google.android.gms.appindexing.*;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.*;
import com.google.android.gms.common.api.*;
import com.google.api.services.youtube.model.*;
import com.squareup.picasso.*;

import java.util.*;

public class SearchActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private ListView videosFound;
    private EditText searchInput;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = (EditText)findViewById(R.id.search_input);
        videosFound = (ListView)findViewById(R.id.videos_found);

      GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
               .requestEmail()
               .build();

      mGoogleApiClient = new GoogleApiClient.Builder(this)
               .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
               .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)

              // .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) )

                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(AppIndex.API).build();



        handler = new Handler();

        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    searchOnYoutube(v.getText().toString());
                    return false;
                }
                return true;
            }
        });

        addClickListener();

    }


    private List<VideoItem> searchResults;

    private void searchOnYoutube(final String keywords){
        new Thread(){
            public void run(){
                YoutubeConnector yc = new YoutubeConnector(SearchActivity.this);
                searchResults = yc.search(keywords);
                handler.post(new Runnable(){
                    public void run(){
                        updateVideosFound();
                    }
                });
            }
        }.start();
    }


    private void updateVideosFound(){
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getApplicationContext(), R.layout.video_item, searchResults){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
                TextView title = (TextView)convertView.findViewById(R.id.video_title);
                TextView description = (TextView)convertView.findViewById(R.id.video_description);

                VideoItem searchResult = searchResults.get(position);

                Picasso.with(getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                description.setText(searchResult.getDescription());
                return convertView;
            }
        };

        videosFound.setAdapter(adapter);
    }

    private void addClickListener(){
        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");

                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                // Create and show the dialog.
                DialogFragment newFragment = DialogFragment1.newInstance(searchResults.get(pos).getId());

                newFragment.setStyle(DialogFragment.STYLE_NO_TITLE,0);
                newFragment.show(ft, "dialog");





                // Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                //intent.putExtra("VIDEO_ID", searchResults.get(pos).getId());
               // startActivity(intent);
            }

        });
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //  Handle action bar item clicks here. The action bar will
        //  automatically handle clicks on the Home/Up button, so long
        //  as you specify a parent activity in AndroidManifest.xml.

         int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (item.getItemId()) {



            case R.id.sign_in:
                Log.d("sign_in_button1","sign in button1");
               // Intent myintent = new Intent(SearchActivity.this,SignInActivity.class);
               // SearchActivity.this.startActivity(myintent);
             signIn();
                return true;


            // if (id == R.id.action_settings) {

            // }

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onstart", "onstart");
        mGoogleApiClient.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Search Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
               // com.yourplayer.c.yourplayer1
                Uri.parse("android-app://com.yourplayer.c.yourplayer1/http/host/path")
               // Uri.parse("android-app://com.patrick.c.simpleplayer1_13_16/http/host/path")
        );
        AppIndex.AppIndexApi.start(mGoogleApiClient, viewAction);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Search Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.yourplayer.c.yourplayer1/http/host/path")
                //Uri.parse("android-app://com.patrick.c.simpleplayer1_13_16/http/host/path")
        );
        AppIndex.AppIndexApi.end(mGoogleApiClient, viewAction);
        mGoogleApiClient.disconnect();
    }

    private void signIn() {
        Log.d("sign in", "sign in");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient );
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}

