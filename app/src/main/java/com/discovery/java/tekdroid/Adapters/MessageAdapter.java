package com.discovery.java.tekdroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.discovery.java.tekdroid.Items.MessageItem;
import com.discovery.java.tekdroid.R;

import java.util.ArrayList;

public class                    MessageAdapter extends ArrayAdapter<MessageItem> {
    Context                     mContext;
    ArrayList<MessageItem>      mContent;
    int                         mLayoutId;

    public MessageAdapter(Context context, int layoutId, ArrayList<MessageItem> content) {
        super(context, 0, content);

        mContext = context;
        mContent = content;
        mLayoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View                    view = convertView;
        MessageItemHolder        holder;
        MessageItem item;

        if (view == null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            view = inflater.inflate(mLayoutId, parent, false);
            holder = new MessageItemHolder();
            holder.mTitle = (TextView)view.findViewById(R.id.message_list_item_title);
            holder.mBody = (TextView)view.findViewById(R.id.message_list_item_body);
            holder.mSender = (TextView)view.findViewById(R.id.message_list_item_from);
            holder.mDate = (TextView)view.findViewById(R.id.message_list_item_date);
            view.setTag(holder);
        }
        else
        {
            holder = (MessageItemHolder)view.getTag();
        }
        item = mContent.get(position);
        if (item != null) {

            holder.mTitle.setText(item.mTitle);
            holder.mBody.setText(item.mContent);
            holder.mSender.setText(item.mUser);
            holder.mDate.setText(item.mDate);
        }
        return view;
    }


    private class   MessageItemHolder
    {
        TextView    mTitle;
        TextView    mBody;
        TextView    mSender;
        TextView    mDate;
    }
}
