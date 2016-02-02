package com.discovery.java.tekdroid.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.discovery.java.tekdroid.APITools.API;
import com.discovery.java.tekdroid.APITools.Profile;
import com.discovery.java.tekdroid.Activities.HomeActivity;
import com.discovery.java.tekdroid.Adapters.GradeListAdapter;
import com.discovery.java.tekdroid.Adapters.MarkListAdapter;
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

/**
 * Created by Jamais on 02/02/2016.
 * Home User Profile Fragment, displays user's profile information as well as 5 last marks & 3 last grades.
 */
public class                    UserProfileFragment extends Fragment {
    API                         _api;
    Profile                     _userProfile;
    ArrayList<MarkListItem>     _markList = new ArrayList<>();
    ArrayList<GradeListItem>    _gradeList = new ArrayList<>();
    View                        _fragmentView;
    String                      _intented;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _api = new API(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try {   _api._userLogin = this.getArguments().getString("userLogin"); } catch (NullPointerException e) { e.printStackTrace(); }
        try {   _api._session_token = this.getArguments().getString("sessionToken");} catch (NullPointerException e) { e.printStackTrace(); }
        try {   _fragmentView = inflater.inflate(R.layout.fragment_user_profile, container, false);} catch (NullPointerException e) { e.printStackTrace(); }

        _api.retrieveProfileInformation(_api._userLogin, userProfileRequest());
        _api.retrieveUserMark(userMarkRequest());
        _api.retrieveUserModules(userGradeRequest());
        return _fragmentView;
    }


    public JsonHttpResponseHandler userProfileRequest() {
        JsonHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                _userProfile = new Profile(response);
                try { ((TextView) _fragmentView.findViewById(R.id.home_user_info_login)).setText(_userProfile.login); } catch (NullPointerException e) {e.printStackTrace(); }
                try { ((TextView) _fragmentView.findViewById(R.id.home_user_info_fullName)).setText(_userProfile.fullName); } catch (NullPointerException e) {e.printStackTrace(); }
                try { ((TextView) _fragmentView.findViewById(R.id.home_user_info_promo)).setText(_userProfile.year); } catch (NullPointerException e) {e.printStackTrace(); }
                try { ((TextView) _fragmentView.findViewById(R.id.home_user_info_gpa)).setText(_userProfile.gpa); } catch (NullPointerException e) {e.printStackTrace(); }
                try { ((TextView) _fragmentView.findViewById(R.id.home_user_info_credits)).setText(_userProfile.credits); } catch (NullPointerException e) {e.printStackTrace(); }
                try { ((TextView) _fragmentView.findViewById(R.id.home_user_info_email)).setText(_userProfile.email); } catch (NullPointerException e) {e.printStackTrace(); }
                try { Picasso.with(getActivity()).load(_userProfile.pictureSrc).into((ImageView) _fragmentView.findViewById(R.id.home_user_info_profile_picture)); } catch (NullPointerException e) {e.printStackTrace(); }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        };

        return responseHandler;
    } /* _____!userProfileRequest */

    /* This request will fill UserProfileFragment::_markList with the 5 last marks obtained by the student */
    public JsonHttpResponseHandler  userMarkRequest()   {
        JsonHttpResponseHandler     callBack = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try
                {
                    JSONArray marks = response.getJSONArray(API.getString(R.string.marks));
                    int         position = 0;
                    for (int i = marks.length() - 1; i >= 0 && position < 10; i--)
                    {
                        try
                        {
                            JSONObject  m = marks.getJSONObject(i);
                            _markList.add(position, new MarkListItem(m.getString(API.getString(R.string.mark_project_name)),
                                    m.getString(API.getString(R.string.mark_final_mark)),
                                    m.getString(API.getString(R.string.mark_project_rater))));
                            ++position;
                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                } catch (JSONException e) {e.printStackTrace(); }
                MarkListAdapter mMarkAdapter = new MarkListAdapter(getActivity() , R.layout.mark_list_item, _markList);
                ((ListView) _fragmentView.findViewById(R.id.home_last_marks_list)).setAdapter(mMarkAdapter);
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

    /* This request will fill UserProfileFragment::_gradeList with the 3 last grades obtained by the student */
    public JsonHttpResponseHandler  userGradeRequest()   {
        JsonHttpResponseHandler     callBack = new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try
                {
                    JSONArray   modules = response.getJSONArray(API.getString(R.string.modules));
                    int         position = 0;
                    for (int i = modules.length() - 1; i >= 0 && position < 7; i--)
                    {
                        try
                        {
                            JSONObject  m = modules.getJSONObject(i);
                            String      g = m.getString(API.getString(R.string.grade_module_grade));
                            if (!g.equals("-")) {
                                _gradeList.add(position, new GradeListItem(m.getString(API.getString(R.string.grade_module_name)), g));
                                ++position;
                            }
                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                } catch (JSONException e) {e.printStackTrace(); }
                GradeListAdapter mGradeAdapter = new GradeListAdapter(getActivity(), R.layout.grade_list_item, _gradeList);
                ((ListView) _fragmentView.findViewById(R.id.home_last_grade_list)).setAdapter(mGradeAdapter);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        };

        return callBack;
    } /* ____!userGradeRequest() */



}
