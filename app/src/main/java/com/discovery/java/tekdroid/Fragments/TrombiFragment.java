package com.discovery.java.tekdroid.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.discovery.java.tekdroid.APITools.API;
import com.discovery.java.tekdroid.Adapters.TrombiProfileAdapter;
import com.discovery.java.tekdroid.Items.TrombiItem;
import com.discovery.java.tekdroid.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jamais on 02/02/2016.
 *
 */
public class                TrombiFragment extends Fragment {

    API                     mApi;
    View                    mFragmentView;
    String                  mYear;
    String                  mLocation = "";
    String                  mCourse = "";
    String                  mPromo = "";
    Spinner                 mLocationSpinner;
    Spinner                 mPromoSpinner;
    Button                  mSearchButton;
    EditText                mYearSelecter;
    ArrayList<TrombiItem>   mTrombiList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mApi = new API(getActivity());
        try {   mApi._userLogin = this.getArguments().getString("userLogin"); } catch (NullPointerException e) { e.printStackTrace(); }
        try {   mApi._session_token = this.getArguments().getString("sessionToken");} catch (NullPointerException e) { e.printStackTrace(); }
        try {   mFragmentView = inflater.inflate(R.layout.fragment_trombi, container, false);} catch (NullPointerException e) { e.printStackTrace(); }
        try
        {
            mLocationSpinner = (Spinner)mFragmentView.findViewById(R.id.fragment_trombi_location_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.trombi_locations, android.R.layout.simple_spinner_dropdown_item);
            mLocationSpinner.setAdapter(adapter);
        } catch (NullPointerException e ) {e.printStackTrace();}
        try
        {
            mPromoSpinner = (Spinner)mFragmentView.findViewById(R.id.fragment_trombi_promo_spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.trombi_promos, android.R.layout.simple_spinner_dropdown_item);
            mPromoSpinner.setAdapter(adapter);
        } catch (NullPointerException e ) {e.printStackTrace();}
        try
        {
            mYearSelecter = (EditText)mFragmentView.findViewById(R.id.fragment_trombi_year_selecter);
        } catch (NullPointerException e ) {e.printStackTrace();}
        try
        {
            mSearchButton = (Button)mFragmentView.findViewById(R.id.fragment_trombi_search_button);
            mSearchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try { mYear = mYearSelecter.getText().toString(); } catch (NullPointerException e) {e.printStackTrace(); }
                    try
                    {
                        mPromo = mPromoSpinner.getSelectedItem().toString();
                    } catch (NullPointerException e) {e.printStackTrace(); }
                    try { mLocation = mLocationSpinner.getSelectedItem().toString(); } catch (NullPointerException e) {e.printStackTrace(); }
                    mApi.retrieveStudentList(mYear, mLocation, mPromo, studentListRequest());
                }
            });
        } catch (NullPointerException e ) {e.printStackTrace();}
        try {
            setTitles();
            ((LinearLayout)mFragmentView.findViewById(R.id.fragment_trombi_header_layout)).setVisibility(View.INVISIBLE);
        } catch (NullPointerException e) {e.printStackTrace(); }

        return mFragmentView;
    }


    public JsonHttpResponseHandler  studentListRequest() {
        JsonHttpResponseHandler callBack = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try
                {
                    JSONArray studentList = response.getJSONArray(API.getString(R.string.studentList));
                    int position = 0;
                    for (int i = studentList.length() - 1; i >= 0; i--) {
                        try
                        {

                            JSONObject student = studentList.getJSONObject(i);
                            mTrombiList.add(position, new TrombiItem(student.getString(API.getString(R.string.login)),
                                    student.getString(API.getString(R.string.picture))));
                        } catch (JSONException e) { e.printStackTrace();}

                    }

                } catch (JSONException e) {e.printStackTrace();}
                TrombiProfileAdapter trombiAdapter = new TrombiProfileAdapter(getActivity(), R.layout.trombi_item, mTrombiList);
                ((ListView)mFragmentView.findViewById(R.id.fragment_trombi_result_list)).setAdapter(trombiAdapter);
                ((LinearLayout)mFragmentView.findViewById(R.id.fragment_trombi_header_layout)).setVisibility(View.VISIBLE);
                ((ListView)mFragmentView.findViewById(R.id.fragment_trombi_result_list)).setVisibility(View.VISIBLE);

                try {
                    setTitles();
                } catch (NullPointerException e) {e.printStackTrace(); }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                ((ListView)mFragmentView.findViewById(R.id.fragment_trombi_result_list)).setVisibility(View.INVISIBLE);
                ((LinearLayout)mFragmentView.findViewById(R.id.fragment_trombi_header_layout)).setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                ((ListView)mFragmentView.findViewById(R.id.fragment_trombi_result_list)).setVisibility(View.INVISIBLE);
                ((LinearLayout)mFragmentView.findViewById(R.id.fragment_trombi_header_layout)).setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                ((ListView)mFragmentView.findViewById(R.id.fragment_trombi_result_list)).setVisibility(View.INVISIBLE);
                ((LinearLayout)mFragmentView.findViewById(R.id.fragment_trombi_header_layout)).setVisibility(View.INVISIBLE);
            }
        };
        return callBack;
    }

    public void      setTitles()
    {
        ((TextView) mFragmentView.findViewById(R.id.fragment_trombi_promo_title)).setText(mPromo);
        ((TextView) mFragmentView.findViewById(R.id.fragment_trombi_year_title)).setText(mYear);
        ((TextView) mFragmentView.findViewById(R.id.fragment_trombi_city_title)).setText(mLocation);
    }
}
