package com.discovery.java.tekdroid.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.discovery.java.tekdroid.Items.TrombiItem;
import com.discovery.java.tekdroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jamais on 02/02/2016.
 *
 */
public class                    TrombiProfileAdapter extends ArrayAdapter<TrombiItem> {
    Context                     mContext;
    ArrayList<TrombiItem>       mContent;
    int                         mLayoutId;

    public TrombiProfileAdapter(Context context, int layoutId, ArrayList<TrombiItem> content) {
        super(context, 0, content);

        mContext = context;
        mContent = content;
        mLayoutId = layoutId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View                    view = convertView;
        TrombiItemHolder        holder;
        TrombiItem item;

        if (view == null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            view = inflater.inflate(mLayoutId, parent, false);
            holder = new TrombiItemHolder();
            holder.mPicture = (ImageView)view.findViewById(R.id.trombi_list_item_picture);
            holder.mLogin = (TextView)view.findViewById(R.id.trombi_list_item_login);
            view.setTag(holder);
        }
        else
        {
            holder = (TrombiItemHolder)view.getTag();
        }
        item = mContent.get(position);
        if (item != null) {
            System.out.println("ITEM CONTAINS LOGIN +++> " + item.mLogin);
            if (holder.mLogin == null)
                System.out.println("holder.mLogin IS NULL");

            holder.mLogin.setText(item.mLogin);
            Picasso.with(mContext).load(item.mPicture).into(holder.mPicture);
        }
        return view;
    }


    private class   TrombiItemHolder
    {
        ImageView   mPicture;
        TextView    mLogin;
    }
}
