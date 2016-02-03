package com.discovery.java.tekdroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.discovery.java.tekdroid.Items.EventListItem;
import com.discovery.java.tekdroid.Items.MarkListItem;
import com.discovery.java.tekdroid.R;

import java.util.ArrayList;


public class EventAdapter extends ArrayAdapter<EventListItem> {
    Context                  _context;
    int                      _layout_ressource_id;
    ArrayList<EventListItem>  _content;

    public EventAdapter(Context context, int layoutId, ArrayList<EventListItem> content)
    {
        super(context, 0, content);
        _context = context;
        _layout_ressource_id = layoutId;
        _content = content;
    }

    @Override
    public  View     getView(int position, View convertView, ViewGroup parent)
    {
        View                view = convertView;
        EventItemHolder      holder;
        EventListItem item;

        if (view == null)
        {
            LayoutInflater  inflater = LayoutInflater.from(_context);
            view = inflater.inflate(_layout_ressource_id, parent, false);
            holder = new EventItemHolder();
            holder.title = (TextView)view.findViewById(R.id.event_name);
            holder.start = (TextView)view.findViewById(R.id.event_time_start);
            holder.end = (TextView)view.findViewById(R.id.event_time_end);
            holder.module = (TextView)view.findViewById(R.id.event_module);

            view.setTag(holder);
        }
        else
        {
            holder = (EventItemHolder)view.getTag();
        }
        item = _content.get(position);
        holder.title.setText(item.mEventTitle);
        holder.start.setText(item.mEventStart.substring(11, 16));
        holder.end.setText(item.mEventEnd.substring(11, 16));
        holder.module.setText(item.mEventCodeModule);

        return view;
    }

    private class   EventItemHolder {
        TextView    title;
        TextView    start;
        TextView    end;
        TextView    module;
    }
}
