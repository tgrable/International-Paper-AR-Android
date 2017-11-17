package com.internationalpaper.ip4d;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

import java.io.IOException;

public class ARActivity extends AppCompatActivity {
    ArchitectView mArchitectView;
    /**
     * urlListener handling "document.location= 'architectsdk://...' " calls in JavaScript"
     */
    protected ArchitectView.ArchitectUrlListener urlListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        //setup the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
            }

            toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        this.mArchitectView = (ArchitectView) findViewById(R.id.architectView);
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey(getString(R.string.license));
        this.mArchitectView.onCreate( config );

        // set urlListener, any calls made in JS like "document.location = 'architectsdk://foo?bar=123'" is forwarded to this listener, use this to interact between JS and native Android activity/fragment
        this.urlListener = this.getUrlListener();

        // register valid urlListener in architectView, ensure this is set before content is loaded to not miss any event
        if ( this.urlListener != null ) {
            Log.d("TrekkAdmin", "this.urlListener != null");
            this.mArchitectView.registerUrlListener( this.getUrlListener() );
        }
        else {
            Log.d("TrekkAdmin", "this.urlListener == null");
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mArchitectView != null) {
            try {
                mArchitectView.onPostCreate();
//                mArchitectView.load("http://goo.gl/m63fod");
                mArchitectView.load("index.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mArchitectView != null) {
            // call mandatory live-cycle method of architectView
            mArchitectView.onResume();

        }
    }

    @Override
    public void onPause() {
        super.onPause();

        // call mandatory live-cycle method of architectView
        if (mArchitectView != null) {
            mArchitectView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mArchitectView != null) {
            mArchitectView.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mArchitectView != null) {
            mArchitectView.onLowMemory();
        }
    }

    public ArchitectView.ArchitectUrlListener getUrlListener() {
        return new ArchitectView.ArchitectUrlListener() {

            @Override
            public boolean urlWasInvoked(String uriString) {
                // by default: no action applied when url was invoked

                Log.d("TrekkAdmin", uriString);

                String url = App.BASE_URL;
                if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-1")) {
//                if (uriString.toLowerCase().contains("ip4d-1")) {
                    url = url + App.IP4D_ONE;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-2")) {
                    url = url + App.IP4D_TWO;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-3")) {
                    url = url + App.IP4D_THREE;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-4")) {
                    url = url + App.IP4D_FOUR;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-5")) {
                    url = url + App.IP4D_FIVE;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-6")) {
                    url = url + App.IP4D_SIX;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-7")) {
                    url = url + App.IP4D_SEVEN;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-8")) {
                    url = url + App.IP4D_EIGHT;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-9")) {
                    url = url + App.IP4D_NINE;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-10")) {
                    url = url + App.IP4D_TEN;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-11")) {
                    url = url + App.IP4D_ELEVEN;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-12")) {
                    url = url + App.IP4D_TWELVE;
                }
                else if (uriString.equals("architectsdk://170613_PFL_KIT_4x6WEBCARDS_IP4D-13")) {
                    url = url + App.IP4D_THIRTEEN;
                }
                else {

                }

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                return false;
            }
        };
    }
}
