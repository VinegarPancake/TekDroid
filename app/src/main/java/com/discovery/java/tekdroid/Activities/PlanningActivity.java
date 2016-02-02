package com.discovery.java.tekdroid.Activities;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.discovery.java.tekdroid.APITools.API;
import com.discovery.java.tekdroid.APITools.Profile;
import com.discovery.java.tekdroid.Adapters.GradeListAdapter;
import com.discovery.java.tekdroid.Adapters.MarkListAdapter;
import com.discovery.java.tekdroid.Fragments.LoginResearchFragment;
import com.discovery.java.tekdroid.Fragments.PlanningFragment;
import com.discovery.java.tekdroid.Fragments.UserProfileFragment;
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


public class PlanningActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public API _api = new API(this);
    public Profile _userProfile;
    private ArrayList<MarkListItem>     _markList = new ArrayList<>();
    private ArrayList<GradeListItem>    _gradeList = new ArrayList<>();
    private Fragment                    _test;

    private AutoCompleteTextView  DropDown(String text) {
        final ActionBar.LayoutParams lparams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        final AutoCompleteTextView   textView = new AutoCompleteTextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(text);
        textView.setBackgroundColor(0xffff00);
        textView.setTextColor(0x000000);
        textView.showDropDown();
        return textView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                AutoCompleteTextView loginSearch = (AutoCompleteTextView) findViewById(R.id.home_search_by_login_field);
//                ((RelativeLayout) findViewById(R.id.home_content_layout)).addView(DropDown(loginSearch.getText().toString()));
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//            }
//        });


        _api.relayReceiver(getIntent());
//        _api.retrieveProfileInformation(_api._userLogin, userProfileRequest());
//        _api.retrieveUserMark(userMarkRequest());
//        _api.retrieveUserModules(userGradeRequest());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer_nav_view);
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

        displayView(id);
        return true;

//        if (id == R.id.nav_camera) {
//        } else if (id == R.id.nav_gallery) {
//            System.out.println("_______________________GALLERY");
//
//        } else if (id == R.id.nav_slideshow) {
//            System.out.println("_______________________SLIDESHOW");
//
//        } else if (id == R.id.nav_manage) {
//            System.out.println("_______________________MANAGE");
//
//        } else if (id == R.id.nav_share) {
//            System.out.println("_______________________SHARE");
//
//        } else if (id == R.id.nav_send) {
//            System.out.println("_______________________SEND");
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
    }

    public void displayView(int viewId) {
        Fragment fragment = null;
        String   title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.nav_camera:
                fragment = new UserProfileFragment();
                title = "My Profile";
                break;
//                fragment = new LoginResearchFragment();
            case R.id.nav_gallery:
                fragment = new LoginResearchFragment();
                title = "Search by Login";
                break;
            case R.id.nav_planning:
                fragment = new PlanningFragment();
                title = "Planning";
                break;
            default: break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Bundle bun = new Bundle();

            bun.putString("userLogin", _api._userLogin);
            bun.putString("sessionToken", _api._session_token);
            fragment.setArguments(bun);
            ft.replace(R.id.home_content_layout, fragment);
            ft.commit();
        }

        if (getActionBar() != null)
        {
            getActionBar().setTitle(title);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }





}
