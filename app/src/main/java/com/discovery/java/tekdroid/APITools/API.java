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
    public  String                      _sem = "null";

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
        _sem = i.getStringExtra(getString(R.string.semester));
    }

    /* Proceeds information through Activity switch */
    public void         relayInciter(Intent i)
    {
        i.putExtra(getString(R.string.relayUserLogin), _userLogin);
        i.putExtra(getString(R.string.relaySessionToken),_session_token);
        i.putExtra(getString(R.string.semester), _sem);
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

        p.put(getString(R.string.token), _session_token);
        p.put(getString(R.string.start), day);
        p.put(getString(R.string.end), day);
        _client.get(getString(R.string.planningAPI), p, callBack);
    }

    public void         retrieveEvent(JsonHttpResponseHandler callBack, String year, String cacti, String cmodule, String cevent, String cinstance) {
        RequestParams   p = new RequestParams();

        p.put(getString(R.string.token), _session_token);
        p.put(getString(R.string.event_c0), year);
        p.put(getString(R.string.event_c1), cmodule);
        p.put(getString(R.string.event_c2), cinstance);
        p.put(getString(R.string.event_c3), cacti);
        p.put(getString(R.string.event_c4), cevent);
        _client.get(getString(R.string.eventAPI), p, callBack);
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

    public void         enterToken(JsonHttpResponseHandler callBack, Integer year, String cmodu, String cinst, String cacti, String cevent, String token) {
        RequestParams   p = new RequestParams();

        p.put(getString(R.string.token), _session_token);
        p.put(getString(R.string.event_c0), year);
        p.put(getString(R.string.event_c1), cmodu);
        p.put(getString(R.string.event_c2), cinst);
        p.put(getString(R.string.event_c3), cacti);
        p.put(getString(R.string.event_c4), cevent);
        p.put(getString(R.string.tokencode), token);
        _client.post(getString(R.string.tokenAPI), p, callBack);
    }
}
