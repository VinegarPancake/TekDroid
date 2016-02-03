package com.discovery.java.tekdroid.Items;

import com.discovery.java.tekdroid.APITools.API;
import com.discovery.java.tekdroid.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EventListItem {
    public String   mEventStart;
    public String   mEventEnd;
    public String   mEventTitle;
    public String   mEventCodeEvent;
    public String   mEventCodeActi;
    public String   mEventCodeInstance;
    public String   mEventCodeModule;
    public String   mScolarYear;
    public boolean  mEventModuleRegistered;
    public String  mEventRegisterStudent;
    public int      mEventSemester;

    public EventListItem(JSONObject jsonEvent)
    {
        super();

        try { mEventStart = jsonEvent.getString("start"); } catch (JSONException e) {e.printStackTrace(); }
        try { mScolarYear = jsonEvent.getString("scolaryear"); } catch (JSONException e) {e.printStackTrace(); }
        try { mEventEnd = jsonEvent.getString("end"); } catch (JSONException e) {e.printStackTrace(); }
        try { mEventTitle = jsonEvent.getString("acti_title"); } catch (JSONException e) {e.printStackTrace(); }
        try { mEventCodeEvent = jsonEvent.getString("codeevent"); } catch (JSONException e) {e.printStackTrace(); }
        try { mEventCodeActi = jsonEvent.getString("codeacti"); } catch (JSONException e) {e.printStackTrace(); }
        try { mEventCodeInstance = jsonEvent.getString("codeinstance"); } catch (JSONException e) {e.printStackTrace(); }
        try { mEventCodeModule = jsonEvent.getString("codemodule"); } catch (JSONException e) {e.printStackTrace(); }
        try { mEventModuleRegistered = jsonEvent.getBoolean("module_registered"); } catch (JSONException e) {e.printStackTrace(); }
        try { mEventRegisterStudent = jsonEvent.getString("event_registered"); } catch (JSONException e) {e.printStackTrace(); }
        try { mEventSemester = jsonEvent.getInt("semester"); } catch (JSONException e) {e.printStackTrace(); }
    }
}
