package com.discovery.java.tekdroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public API                      _api = new API(this);
    public Profile                  _userProfile;
    private ArrayList<MarkListItem> _markList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        _api.relayReceiver(getIntent());
        _api.retrieveProfileInformation(_api._userLogin, userProfileRequest());
        _api.retrieveUserMark(userMarkRequest());

        System.out.println("token = " + _api._session_token);
        System.out.println("user = " + _api._userLogin);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public JsonHttpResponseHandler userProfileRequest() {
        JsonHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                _userProfile = new Profile(response);
                ((TextView) findViewById(R.id.home_user_info_login)).setText(_userProfile.login);
                ((TextView) findViewById(R.id.home_user_info_fullName)).setText(_userProfile.fullName);
                ((TextView) findViewById(R.id.home_user_info_promo)).setText(_userProfile.year);
                ((TextView) findViewById(R.id.home_user_info_gpa)).setText(_userProfile.gpa);
                ((TextView) findViewById(R.id.home_user_info_email)).setText(_userProfile.email);
                Picasso.with(getApplicationContext()).load(_userProfile.pictureSrc).into((ImageView) findViewById(R.id.home_user_info_profile_picture));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        };

        return responseHandler;
    } /* _____!userProfileRequest */

    /* This request will fill HomeActivity::_markList with the 5 last marks obtained by the student */
    public JsonHttpResponseHandler  userMarkRequest()   {
        JsonHttpResponseHandler     callBack = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try
                {
                    JSONArray   marks = response.getJSONArray(API.getString(R.string.marks));

                    int         start = marks.length() <= 5 ? 0 : marks.length() - 5;

                    for (int i = start; i < marks.length(); i++)
                    {
                        try
                        {
                            JSONObject  m = marks.getJSONObject(i);
                            _markList.add(i - start, new MarkListItem(m.getString(API.getString(R.string.mark_project_name)),
                                                                     m.getString(API.getString(R.string.mark_final_mark)),
                                                                     m.getString(API.getString(R.string.mark_project_rater))));
                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                } catch (JSONException e) {e.printStackTrace(); }

                MarkListAdapter mMarkAdpater = new MarkListAdapter(HomeActivity.this, R.layout.mark_list_item, _markList);
                ((ListView) findViewById(R.id.home_last_marks_list)).setAdapter(mMarkAdpater);
                System.out.println("Leaving Mark request on success");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                System.out.println("Leaving Mark request on failure");
            }
        };

        return callBack;
    } /* ____!userMarkRequest() */

}
