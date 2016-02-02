package com.discovery.java.tekdroid.Items;

/**
 * Created by Jamais on 01/02/2016.
 */

/* represent data fields of a project note obtained from a JSONObject */
public class MarkListItem {
    public String   mProjectName;
    public String   mProjectMark;
    public String   mProjectRater;

    public MarkListItem(String projectName, String projectMark, String projectRater)
    {
        super();
        mProjectName = projectName;
        mProjectMark = projectMark;
        mProjectRater = projectRater;
    }
}
