package com.discovery.java.tekdroid.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.discovery.java.tekdroid.APITools.API;
import com.discovery.java.tekdroid.R;

/**
 * Created by Jamais on 02/02/2016.
 *
 */
public class                TrombiFragment extends Fragment {

    API                     mApi;
    View                    mFragmentView;
    String                  mYear;
    String                  mLocation;
    String                  mCourse;
    String                  mPromo;
    Spinner                 mLocationSpinner;
    Spinner                 mPromoSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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

        return mFragmentView;
    }


}
