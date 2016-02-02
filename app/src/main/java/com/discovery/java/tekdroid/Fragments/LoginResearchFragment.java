package com.discovery.java.tekdroid.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.discovery.java.tekdroid.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link LoginResearchFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link LoginResearchFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class LoginResearchFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;
//    private Profile                       mProfile;
//    private String                        mUserLogin;
//    private String                        mSessionToken;
//    private API                           mApi = new API(getContext());
//    private searchResultProfileHolder     mProfileHolder = new searchResultProfileHolder();

//    public LoginResearchFragment() {
//        // Required empty public constructor
//    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment LoginResearchFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static LoginResearchFragment newInstance(String param1, String param2) {
//        LoginResearchFragment fragment = new LoginResearchFragment();
//        Bundle args = new Bundle();
//        args.putString("userLogin", param1);
//        args.putString("sessionToken", param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mUserLogin = getArguments().getString("userLogin");
//            mSessionToken = getArguments().getString("sessionToken");
//            mApi._userLogin = mUserLogin;
//            mApi._session_token = mSessionToken;
//            mApi.retrieveProfileInformation(mUserLogin, fillProfile());
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View _view = inflater.inflate(R.layout.fragment_login_research, container, false);
//        mProfileHolder.set(_view);
//        return _view;
        return inflater.inflate(R.layout.fragment_login_research, container, false);
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }


//    private JsonHttpResponseHandler fillProfile() {
//        JsonHttpResponseHandler     callBack = new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//
//                mProfile = new Profile(response);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//            }
//        };
//
//        return callBack;
//    }
//
//    private class   searchResultProfileHolder {
//        Profile     mProfile;
//        ImageView   mPicture;
//        TextView    fullName;
//        TextView    login;
//        TextView    email;
//        TextView    year;
//        TextView    gpa;
//        TextView    activeTime;
//        TextView    phone;
//        TextView    credits;
//
//
//        public searchResultProfileHolder()
//        {
//            super();
//        }
//
//        public void set(View view)
//        {
//            mPicture = (ImageView)view.findViewById(R.id.login_research_profile_picture);
//            fullName = (TextView)view.findViewById(R.id.login_research_result_fullName);
//            login = (TextView)view.findViewById(R.id.login_research_result_login);
//            email = (TextView)view.findViewById(R.id.login_research_result_city);
//            year = (TextView)view.findViewById(R.id.login_research_result_promo);
//            gpa = (TextView)view.findViewById(R.id.login_research_result_gpa);
//        }
//    }
}
