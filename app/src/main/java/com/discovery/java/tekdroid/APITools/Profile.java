package com.discovery.java.tekdroid.APITools;

import com.discovery.java.tekdroid.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jamais on 01/02/2016.
 */
public class Profile {
    public String   fullName;
    public String   login;
    public String   email;
    public String   pictureSrc;
    public String   year;
    public String   gpa;
    public String   activeTime;
    public String   phone;
    public String   credits;

    public Profile  () {
        super();
    }

    public Profile (JSONObject jsonProfile) {
        super();

        try { fullName = jsonProfile.getString(API.getString(R.string.fullName)); } catch (JSONException e) {e.printStackTrace(); }
        try { login = jsonProfile.getString(API.getString(R.string.login)); } catch (JSONException e) {e.printStackTrace(); }
        try { pictureSrc = jsonProfile.getString(API.getString(R.string.picture)); } catch (JSONException e) {e.printStackTrace(); }
        try { email = jsonProfile.getString(API.getString(R.string.email)); } catch (JSONException e) {e.printStackTrace(); }
        try { year = jsonProfile.getString(API.getString(R.string.promo)); } catch (JSONException e) {e.printStackTrace(); }
        try { gpa = jsonProfile.getJSONArray(API.getString(R.string.gpa)).getJSONObject(0).getString(API.getString(R.string.gpa)); } catch (JSONException e) {e.printStackTrace(); }
        try { activeTime = jsonProfile.getJSONObject(API.getString(R.string.complementary_info)).getString(API.getString(R.string.active_time)); } catch (JSONException e) {e.printStackTrace(); }
        try { credits = jsonProfile.getString(API.getString(R.string.credits)); } catch (JSONException e) {e.printStackTrace(); }

    }
}
