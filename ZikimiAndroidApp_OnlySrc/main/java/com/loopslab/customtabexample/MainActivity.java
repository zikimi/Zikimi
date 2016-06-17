package com.loopslab.customtabexample;

import android.content.ComponentName;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";
    CustomTabsClient mClient;
    CustomTabsSession mCustomTabsSession;
    CustomTabsServiceConnection mCustomTabsServiceConnection;
    Button simpleTab,advancedTab;
    Button alert;
    Button soundOn,soundOff;
    boolean isSound;


   private static String URL = "https://apprtc.appspot.com/r/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);



        simpleTab = (Button) findViewById(R.id.simpleTab);
        alert = (Button)findViewById(R.id.Alert);
        soundOn= (Button)findViewById(R.id.on);
        soundOff = (Button)findViewById(R.id.off);
       // advancedTab = (Button) findViewById(R.id.advancedTab);


        simpleTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Setup Chrome Custom Tabs
                EditText URLEdit = (EditText)findViewById(R.id.numberText);
                String URLnumber=URLEdit.getText().toString();
                URL= URL+URLnumber;

                mCustomTabsServiceConnection = new CustomTabsServiceConnection() {
                    @Override
                    public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {

                        //Pre-warming
                        mClient = customTabsClient;
                        mClient.warmup(0L);
                        //Initialize a session as soon as possible.
                        mCustomTabsSession = mClient.newSession(null);

                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        mClient = null;
                    }
                };

                CustomTabsClient.bindCustomTabsService(MainActivity.this, CUSTOM_TAB_PACKAGE_NAME, mCustomTabsServiceConnection);


                CustomTabsIntent customSimpleTabsIntent;
                customSimpleTabsIntent = new CustomTabsIntent.Builder(mCustomTabsSession)

                        .setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary))
                        .setShowTitle(true)
                        .build();
                customSimpleTabsIntent.launchUrl(MainActivity.this, Uri.parse(URL));
            }
        });


        alert.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(isSound)
                    {
                        isSound=false;
                        soundOff.setVisibility(View.VISIBLE);
                        soundOn.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        isSound=true;
                        soundOn.setVisibility(View.VISIBLE);
                        soundOff.setVisibility(View.INVISIBLE);
                    }
                }

        });





      /*  advancedTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
                CustomTabActivityHelper.openCustomTab(
                        MainActivity.this, customTabsIntent, Uri.parse(URL), new WebviewFallback());
            }
        });
*/



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
      //   automatically handle clicks on the Home/Up button, so long
       //  as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

     //   noinspection SimplifiableIfStatement
     //   if (id == R.id.action_settings) {
     //       return true;
      //  }

        return super.onOptionsItemSelected(item);
    }
}
