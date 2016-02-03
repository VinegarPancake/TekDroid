package com.discovery.java.tekdroid.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.discovery.java.tekdroid.APITools.API;
import com.discovery.java.tekdroid.Adapters.MessageAdapter;
import com.discovery.java.tekdroid.Adapters.TrombiProfileAdapter;
import com.discovery.java.tekdroid.Items.MessageItem;
import com.discovery.java.tekdroid.R;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Jamais on 03/02/2016.

 */
public class                MessagesFragment extends Fragment {
    API                     mAPI;
    ArrayList<MessageItem>  mMessageList = new ArrayList<>();
    View                    mFragmentView;
    ListView                mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAPI = new API(getActivity());
        try {   mAPI._session_token = this.getArguments().getString("sessionToken");} catch (NullPointerException e) { e.printStackTrace(); }
        mFragmentView = inflater.inflate(R.layout.fragment_messages, container, false);

        mAPI.retrieveUserMessages(userMessagesRequest());
        return mFragmentView;
    }

    public JsonHttpResponseHandler  userMessagesRequest() {
        JsonHttpResponseHandler     callBack = new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                    int position = 0;
                    for (int i = response.length() -1; i >= 0 && position < 20; i--)
                    {
                        try
                        {
                            JSONObject  m = response.getJSONObject(i);
                            String title = m.getString(API.getString(R.string.message_title));
                            String body = m.getString(API.getString(R.string.message_content));
                            String sender = "null";
                            try
                            {
                                JSONObject  user = m.getJSONObject(API.getString(R.string.message_user));
                                sender = user.getString(API.getString(R.string.message_teacher_name));
                            } catch (JSONException e) {e.printStackTrace(); }
                            String date = m.getString(API.getString(R.string.message_date));
                            mMessageList.add(position, new MessageItem(title, sender, body, date));
                            ++position;
                            } catch (JSONException e) {e.printStackTrace(); }

                    }
                MessageAdapter messageAdapter = new MessageAdapter(getActivity(), R.layout.message_list_item, mMessageList);
                ((ListView)mFragmentView.findViewById(R.id.fragment_messages_result_list)).setAdapter(messageAdapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        };

        return callBack;
    }

}
