package com.discovery.java.tekdroid.Items;

/**
 * Created by Jamais on 03/02/2016.
 */
public class    MessageItem {
    public String      mTitle;
    public String      mUser;
    public String      mContent;
    public String      mDate;

    public      MessageItem(String title, String sender, String body, String date)
    {
        super();
        mTitle = title;
        mUser = sender;
        mContent = body;
        mDate = date;
    }
}
