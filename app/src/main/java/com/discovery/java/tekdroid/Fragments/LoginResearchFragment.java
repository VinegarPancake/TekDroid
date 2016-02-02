package com.discovery.java.tekdroid.Fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.discovery.java.tekdroid.APITools.API;
import com.discovery.java.tekdroid.APITools.Profile;
import com.discovery.java.tekdroid.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class                        LoginResearchFragment extends Fragment {
    Profile                         _resultProfile;
    API                             _api;
    View                            mView;
    searchResultProfileHolder       mProfileHolder = new searchResultProfileHolder();
    SearchView                      mSearchButton;

    public LoginResearchFragment() {
    }

    public static LoginResearchFragment newInstance(String login, String session_token) {
        LoginResearchFragment fragment = new LoginResearchFragment();
        Bundle args = new Bundle();
        args.putString("login", login);
        args.putString("sessionToken", session_token);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _api = new API(getActivity());
        if (getArguments() != null) {
            _api._session_token = getArguments().getString("sessionToken");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_research, container, false);
        mProfileHolder.set(mView);
        mSearchButton = (SearchView)mView.findViewById(R.id.login_research_search_button);

        mSearchButton.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                _api.retrieveProfileInformation(query.toLowerCase(), fillProfile());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return mView;
    }


    private JsonHttpResponseHandler fillProfile() {
        JsonHttpResponseHandler     callBack = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                _resultProfile = new Profile(response);
                mProfileHolder.setText(_resultProfile);
                if (mProfileHolder.result_group_layout != null)
                    mProfileHolder.result_group_layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if (mProfileHolder.result_group_layout != null)
                    mProfileHolder.result_group_layout.setVisibility(View.INVISIBLE);
            }
        };

        return callBack;
    }

    private class   searchResultProfileHolder {
        RelativeLayout      result_group_layout = null;
        ImageView   mPicture = null;
        TextView    fullName = null;
        TextView    login = null;
        TextView    email = null;
        TextView    year = null;
        TextView    gpa = null;
        TextView    activeTime = null;
        TextView    membership = null;
        TextView    credits = null;


        public searchResultProfileHolder()
        {
            super();
        }

        public void set(View view)
        {
            try { result_group_layout = (RelativeLayout)view.findViewById(R.id.login_research_result_layout); } catch (NullPointerException e) { e.printStackTrace(); }
            try { mPicture = (ImageView)view.findViewById(R.id.login_research_profile_picture); } catch (NullPointerException e) { e.printStackTrace(); }
            try { fullName = (TextView)view.findViewById(R.id.login_research_result_fullName); } catch (NullPointerException e) { e.printStackTrace(); }
            try { login = (TextView)view.findViewById(R.id.login_research_result_login); } catch (NullPointerException e) { e.printStackTrace(); }
            try { email = (TextView)view.findViewById(R.id.login_research_result_city); } catch (NullPointerException e) { e.printStackTrace(); }
            try { year = (TextView)view.findViewById(R.id.login_research_result_promo); } catch (NullPointerException e) { e.printStackTrace(); }
            try { gpa = (TextView)view.findViewById(R.id.login_research_result_gpa); } catch (NullPointerException e) { e.printStackTrace(); }
            try { membership = (TextView)view.findViewById(R.id.login_research_info_value_membership); } catch (NullPointerException e) { e.printStackTrace(); }
        }

        public void setText(Profile profile)
        {
            Picasso.with(getActivity()).load(profile.pictureSrc).into(mPicture);
            fullName.setText(profile.fullName);
            login.setText(profile.login);
            email.setText(profile.email);
            year.setText(profile.year);
            gpa.setText(profile.gpa);
            membership.setText(profile.membership);
        }
    }
}
