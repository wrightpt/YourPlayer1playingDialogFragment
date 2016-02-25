package com.yourplayer.c.yourplayer1;

import android.content.*;
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
import com.squareup.picasso.*;

import java.util.*;

public class SearchActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private ListView videosFound;
    private EditText searchInput;
    private GoogleApiClient mGoogleApiClient;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = (EditText)findViewById(R.id.search_input);
        videosFound = (ListView)findViewById(R.id.videos_found);

    //    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
             //   .requestEmail()
             //   .build();

     //   mGoogleApiClient = new GoogleApiClient.Builder(this)
            //    .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
             //   .addOnConnectionFailedListener(this)
                //   .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) )

            //    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
             //   .addApi(AppIndex.API).build();



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
    public boolean onOptionsItemSelected(MenuItem item) {
        //  Handle action bar item clicks here. The action bar will
        //  automatically handle clicks on the Home/Up button, so long
        //  as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (item.getItemId()) {



            case R.id.sign_in:
                Log.d("sign_in_button1","sign in button1");
                Intent myintent = new Intent(SearchActivity.this,SignInActivity.class);
                SearchActivity.this.startActivity(myintent);
             //   signIn();
                return true;


            // if (id == R.id.action_settings) {

            // }

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

