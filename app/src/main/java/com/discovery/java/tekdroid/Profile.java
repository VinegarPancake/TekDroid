package com.discovery.java.tekdroid;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jamais on 01/02/2016.
 */
public class Profile {
    public String fullName;
    public String login;
    public String email;
    public String pictureSrc;
    public String year;
    public String gpa;
    public String activeTime;

    public Profile  () {
        super();
    }

    public Profile (JSONObject jsonProfile) {
        super();

        try
        {
            fullName = jsonProfile.getString(API.getString(R.string.fullName));
            login = jsonProfile.getString(API.getString(R.string.login));
            pictureSrc = jsonProfile.getString(API.getString(R.string.picture));
            email = jsonProfile.getString(API.getString(R.string.email));
            year = jsonProfile.getString(API.getString(R.string.promo));
            gpa = jsonProfile.getJSONArray(API.getString(R.string.gpa)).getJSONObject(0).getString(API.getString(R.string.gpa));
            activeTime = jsonProfile.getJSONObject(API.getString(R.string.complementary_info)).getString(API.getString(R.string.active_time));

        } catch (JSONException e) {e.printStackTrace();}

    }
}
