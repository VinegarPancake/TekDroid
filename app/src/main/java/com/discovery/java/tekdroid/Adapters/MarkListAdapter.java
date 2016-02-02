package com.discovery.java.tekdroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.discovery.java.tekdroid.Items.MarkListItem;
import com.discovery.java.tekdroid.R;

import java.util.ArrayList;

/**
 * Created by Jamais on 01/02/2016.
 *
 *
 */


/*
Adapt an Array list of markListItem to a listView where there are displayed as follow :
    <projectName><projectMark><projectRater>
 */
public class MarkListAdapter extends ArrayAdapter<MarkListItem> {
    Context                  _context;
    int                      _layout_ressource_id;
    ArrayList<MarkListItem>  _content;

    public MarkListAdapter(Context context, int layoutId, ArrayList<MarkListItem> content)
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
            MarkItemHolder      holder;
            MarkListItem        item;

        if (view == null)
        {
            LayoutInflater  inflater = ((Activity)_context).getLayoutInflater();
            view = inflater.inflate(_layout_ressource_id, parent, false);
            holder = new MarkItemHolder();
            holder.name = (TextView)view.findViewById(R.id.list_mark_item_project_name);
            holder.mark = (TextView)view.findViewById(R.id.list_mark_item_mark);
            holder.rater = (TextView)view.findViewById(R.id.list_mark_item_rater);
            view.setTag(holder);
        }
        else
        {
            holder = (MarkItemHolder)view.getTag();
        }
        item = _content.get(position);
        holder.name.setText(item.mProjectName);
        holder.mark.setText(item.mProjectMark);
        holder.rater.setText(item.mProjectRater);

        return view;
    }

    private class   MarkItemHolder {
        TextView    name;
        TextView    mark;
        TextView    rater;
    }
}
