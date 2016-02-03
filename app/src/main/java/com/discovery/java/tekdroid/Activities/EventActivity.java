package com.discovery.java.tekdroid.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.discovery.java.tekdroid.APITools.API;
import com.discovery.java.tekdroid.Adapters.EventAdapter;
import com.discovery.java.tekdroid.Items.EventListItem;
import com.discovery.java.tekdroid.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EventActivity extends AppCompatActivity {

    public API _api = new API(this);
    TextView reg;
    String c1;
    String c2;
    String c3;
    String c4;
    String c0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _api.relayReceiver(getIntent());
        c0 = getIntent().getExtras().getString("scolaryear");
        c1 = getIntent().getExtras().getString("codemodule");
        c2 = getIntent().getExtras().getString("codeevent");
        c3 = getIntent().getExtras().getString("codeinstance");
        c4 = getIntent().getExtras().getString("codeacti");
        reg = (TextView) findViewById(R.id.reg);
        if (getIntent().getExtras().getString("registered").equals("registered"))
            reg.setText("Inscrit");
        else
            reg.setText("Non inscrit");
        _api.retrieveEvent(eventRequest(), c0, c4, c1, c2, c3);
    }

    public JsonHttpResponseHandler eventRequest()   {
        JsonHttpResponseHandler     callBack = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try
                {
                    Log.d("JAYVENTILEY", response.toString());
                    JSONObject event = response;
                    try { ((TextView) findViewById(R.id.acti_title)).setText(event.getString("acti_title")); } catch (NullPointerException e) {e.printStackTrace(); }
                    try { ((TextView) findViewById(R.id.acti_type)).setText(event.getString("type_title")); } catch (NullPointerException e) {e.printStackTrace(); }
                    try { ((TextView) findViewById(R.id.acti_room)).setText(event.getJSONObject("room").getString("code")); } catch (NullPointerException e) {e.printStackTrace(); }
                    try { ((TextView) findViewById(R.id.acti_modu)).setText(event.getString("module_title")); } catch (NullPointerException e) {e.printStackTrace(); }
                    try { ((TextView) findViewById(R.id.acti_desc)).setText(event.getString("description")); } catch (NullPointerException e) {e.printStackTrace(); }


                    } catch (JSONException e) {e.printStackTrace(); }
                System.out.println("Leaving Event request on success");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                System.out.println("Leaving Event request on failure");
            }
        };

        return callBack;
    }

}
