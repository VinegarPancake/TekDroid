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
            LayoutInflater  inflater = ((Activity)_context).getLayoutInflater();
            view = inflater.inflate(_layout_ressource_id, parent, false);
            holder = new EventItemHolder();
            holder.name = (TextView)view.findViewById(R.id.list_mark_item_project_name);
            holder.mark = (TextView)view.findViewById(R.id.list_mark_item_mark);
            holder.rater = (TextView)view.findViewById(R.id.list_mark_item_rater);
            view.setTag(holder);
        }
        else
        {
            holder = (EventItemHolder)view.getTag();
        }
        item = _content.get(position);
        holder.name.setText(item.mProjectName);
        holder.mark.setText(item.mProjectMark);
        holder.rater.setText(item.mProjectRater);

        return view;
    }

    private class   EventItemHolder {
        TextView    name;
        TextView    mark;
        TextView    rater;
    }
}
