package com.discovery.java.tekdroid.Fragments;



import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.discovery.java.tekdroid.APITools.API;
import com.discovery.java.tekdroid.APITools.Day;
import com.discovery.java.tekdroid.APITools.Profile;
import com.discovery.java.tekdroid.Activities.HomeActivity;
import com.discovery.java.tekdroid.Activities.PlanningActivity;
import com.discovery.java.tekdroid.Adapters.GradeListAdapter;
import com.discovery.java.tekdroid.Adapters.MarkListAdapter;
import com.discovery.java.tekdroid.Items.EventListItem;
import com.discovery.java.tekdroid.Items.GradeListItem;
import com.discovery.java.tekdroid.Items.MarkListItem;
import com.discovery.java.tekdroid.R;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class                    PlanningFragment extends Fragment {
    API _api;
    CalendarView calendar;
    Button viewButton;
    Switch s1;
    Switch s2;
    Switch s3;
    int _dyear;
    int _dmonth;
    int _dday;

    Day _day;
    //
    ArrayList<MarkListItem> _markList = new ArrayList<>();
    ArrayList<GradeListItem>    _gradeList = new ArrayList<>();
    View _fragmentView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _api = new API(getActivity());
        _dyear = 0;
        _dmonth = 0;
        _dday = 0;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try {   _api._userLogin = this.getArguments().getString("userLogin"); } catch (NullPointerException e) { e.printStackTrace(); }
        try {   _api._session_token = this.getArguments().getString("sessionToken");} catch (NullPointerException e) { e.printStackTrace(); }
        try {   _fragmentView = inflater.inflate(R.layout.fragment_planning, container, false);} catch (NullPointerException e) { e.printStackTrace(); }
        // TODO
        //_api.retrieveDay(_api._userLogin, dayRequest(), day);

        calendarInit(_fragmentView);
        s1 = (Switch) _fragmentView.findViewById(R.id.switch1);
        s2 = (Switch) _fragmentView.findViewById(R.id.switch2);
        s3 = (Switch) _fragmentView.findViewById(R.id.switch3);
        return _fragmentView;
    }



    public void calendarInit(View view) {
        calendar = (CalendarView)view.findViewById(R.id.calendar);
        calendar.setShowWeekNumber(false);
        calendar.setFirstDayOfWeek(2);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                _dyear = year;
                _dmonth = month;
                _dday = day;
            }
        });
        Calendar kalendar = Calendar.getInstance();
        _dyear = kalendar.get(Calendar.YEAR);
        _dmonth = kalendar.get(Calendar.MONTH);
        _dday = kalendar.get(Calendar.DAY_OF_MONTH);

        viewButton = (Button)view.findViewById(R.id.button_view);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PlanningActivity.class);
                _api.relayInciter(i);
                i.putExtra("b1", s1.isChecked());
                i.putExtra("b2", s2.isChecked());
                i.putExtra("b3", s3.isChecked());
                i.putExtra("day", _dday);
                i.putExtra("year", _dyear);
                i.putExtra("month", _dmonth);
                startActivity(i);
            }
        });

    }

}
