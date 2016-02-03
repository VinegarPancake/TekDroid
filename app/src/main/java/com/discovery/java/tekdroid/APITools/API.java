package com.discovery.java.tekdroid.APITools;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.discovery.java.tekdroid.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Jamais on 01/02/2016.
 */
public class API {

    public  String                      _userLogin = "null";
    public  String                      _session_token = "null";
    private static AsyncHttpClient      _client = new AsyncHttpClient();
    static private Context                     _context = null;

    public API(Context context) {
        super();

        _context = context;
    }

    /* Retrieves information through Activity switch */
    public void         relayReceiver(Intent i)
    {
        _userLogin = i.getStringExtra(getString(R.string.relayUserLogin));
        _session_token = i.getStringExtra(getString(R.string.relaySessionToken));
    }

    /* Proceeds information through Activity switch */
    public void         relayInciter(Intent i)
    {
        i.putExtra(getString(R.string.relayUserLogin), _userLogin);
        i.putExtra(getString(R.string.relaySessionToken),_session_token);
    }

    static public String       getString(int id)
    {
        return _context.getString(id);
    }

    public void retrieveProfileInformation(String user, JsonHttpResponseHandler callBack) {
        RequestParams   p = new RequestParams();

        p.put(getString(R.string.token), _session_token);
        p.put(getString(R.string.user), user);
        _client.get(getString(R.string.userAPI), p, callBack);
    }

    public void         retrieveUserMark(JsonHttpResponseHandler callBack) {
        RequestParams   p = new RequestParams();

        p.put(getString(R.string.token), _session_token);
        _client.get(getString(R.string.marksAPI), p, callBack);
    }

    public void         retrieveUserModules(JsonHttpResponseHandler callBack) {
        RequestParams   p = new RequestParams();

        p.put(getString(R.string.token), _session_token);
        _client.get(getString(R.string.moduleAPI), p, callBack);
    }

    public void         retrieveDay(JsonHttpResponseHandler callBack, String day) {
        RequestParams   p = new RequestParams();

        Log.d("TOKEN", _session_token);
        Log.d("DAY", day);
        p.put(getString(R.string.token), _session_token);
        p.put(getString(R.string.start), day);
        p.put(getString(R.string.end), day);
        _client.get(getString(R.string.planningAPI), p, callBack);
    }

    public void         retrieveStudentList(String year, String location, String promo, JsonHttpResponseHandler callBack)
    {
        RequestParams   p = new RequestParams();

        p.put(getString(R.string.token), _session_token);
        p.put(getString(R.string.year), year);
        p.put(getString(R.string.location), location);
        if (!promo.equals("null"))
            p.put(getString(R.string.promotion), promo);
        _client.get(getString(R.string.trombiAPI), p, callBack);
    }

    public void         retrieveUserMessages(JsonHttpResponseHandler callBack)
    {
        RequestParams   p = new RequestParams();

        p.put(getString(R.string.token), _session_token);
        _client.get(getString(R.string.messagesAPI), p, callBack);
    }
}
