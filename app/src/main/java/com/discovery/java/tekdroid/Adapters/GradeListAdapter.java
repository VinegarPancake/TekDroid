package com.discovery.java.tekdroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.discovery.java.tekdroid.Items.GradeListItem;
import com.discovery.java.tekdroid.R;

import java.util.ArrayList;

/**
 * Created by Jamais on 01/02/2016.
 *
 */
public class        GradeListAdapter extends ArrayAdapter<GradeListItem> {
    Context                     _context;
    int                         _layout_ressource_id;
    ArrayList<GradeListItem>    _content;

    public GradeListAdapter(Context context, int layoutId, ArrayList<GradeListItem> content)
    {
        super(context, 0, content);
        _context = context;
        _layout_ressource_id = layoutId;
        _content = content;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View                view = convertView;
        GradeItemHolder      holder;
        GradeListItem        item;

        if (view == null)
        {
            LayoutInflater inflater = ((Activity)_context).getLayoutInflater();
            view = inflater.inflate(_layout_ressource_id, parent, false);
            holder = new GradeItemHolder();
            holder.name = (TextView)view.findViewById(R.id.grade_list_item_module_title);
            holder.grade = (TextView)view.findViewById(R.id.grade_list_item_module_grade);
            view.setTag(holder);
        }
        else
        {
            holder = (GradeItemHolder)view.getTag();
        }
        item = _content.get(position);
        holder.name.setText(item.mModuleName);
        holder.grade.setText(item.mModuleGrade);

        return view;
    }

    private class   GradeItemHolder {
        TextView    name;
        TextView    grade;
    }


}
