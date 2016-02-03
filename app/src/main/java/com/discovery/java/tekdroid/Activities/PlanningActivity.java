package com.discovery.java.tekdroid.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.discovery.java.tekdroid.APITools.API;
import com.discovery.java.tekdroid.APITools.Profile;
import com.discovery.java.tekdroid.Adapters.EventAdapter;
import com.discovery.java.tekdroid.Adapters.GradeListAdapter;
import com.discovery.java.tekdroid.Adapters.MarkListAdapter;
import com.discovery.java.tekdroid.Fragments.LoginResearchFragment;
import com.discovery.java.tekdroid.Fragments.PlanningFragment;
import com.discovery.java.tekdroid.Fragments.UserProfileFragment;
import com.discovery.java.tekdroid.Items.EventListItem;
import com.discovery.java.tekdroid.Items.GradeListItem;
import com.discovery.java.tekdroid.Items.MarkListItem;
import com.discovery.java.tekdroid.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class PlanningActivity extends Activity {
    public API _api = new API(this);
    public Profile _userProfile;
    private ArrayList<EventListItem>     _eventList = new ArrayList<>();
    ListView _list;
    Boolean current_semester;
    Boolean module_sub;
    Boolean event_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        _api.relayReceiver(getIntent());
        current_semester = getIntent().getBooleanExtra("b1", false);
        module_sub = getIntent().getBooleanExtra("b2", false);
        event_sub = getIntent().getBooleanExtra("b3", false);
        Bundle b = getIntent().getExtras();
        String dey = String.valueOf(b.getInt("year")) + "-";
        if (b.getInt("month") < 9)
            dey += "0";
        dey += String.valueOf(b.getInt("month") + 1) + "-";
        if (b.getInt("day") < 10)
            dey += "0";
        dey += String.valueOf(b.getInt("day"));
        _list = (ListView) findViewById(R.id.list_events);
        _list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), EventActivity.class);
                _api.relayInciter(i);
                i.putExtra("scolaryear", _eventList.get(position).mScolarYear);
                i.putExtra("codemodule", _eventList.get(position).mEventCodeModule);
                i.putExtra("codeevent", _eventList.get(position).mEventCodeEvent);
                i.putExtra("codeacti", _eventList.get(position).mEventCodeActi);
                i.putExtra("codeinstance", _eventList.get(position).mEventCodeInstance);
                i.putExtra("registered", _eventList.get(position).mEventRegisterStudent);
                startActivity(i);
            }
        });

       _api.retrieveDay(dayRequest(), dey);

    }

    public JsonHttpResponseHandler  dayRequest()   {
        JsonHttpResponseHandler     callBack = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try
                {
                    Log.d("JAYSON", response.toString());
                    JSONArray events = new JSONArray(response.toString());

                    int         position = 0;
                    for (int i = events.length() - 1; i >= 0 && position < 20; i--)
                    {
                        try
                        {
                            JSONObject  m = events.getJSONObject(i);
                            _eventList.add(position, new EventListItem(m));
                            ++position;
                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                } catch (JSONException e) {e.printStackTrace(); }
                EventAdapter mEventAdapter = new EventAdapter(getBaseContext(), R.layout.item_event, _eventList);
                _list.setAdapter(mEventAdapter);
                System.out.println("Leaving Event request on success");
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                System.out.println("Leaving Event request on failure");
            }
        };

        return callBack;
    } /* ____!userMarkRequest() */


}
