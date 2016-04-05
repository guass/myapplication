package com.guass.navapp.protocol;

import android.util.Log;

import com.guass.navapp.bean.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guass on 2016/4/3.
 */
public class DetailProtocol extends BaseProtocol<AppInfo>{
    String packageName;

    public DetailProtocol(String packageName)
    {
        this.packageName = packageName;
    }

    @Override
    public String getKey()
    {
        return "detail";
    }

    @Override
    public AppInfo parseJson(String json)
    {
        List<AppInfo>  datas = new ArrayList<AppInfo>();
        try
        {
            JSONObject jsonObject = new JSONObject(json);
            long id = jsonObject.getLong("id");
            String name = jsonObject.getString("name");
            String packageName = jsonObject.getString("packageName");
            String iconUrl = jsonObject.getString("iconUrl");
            double stars = jsonObject.getDouble("stars");
            long size = jsonObject.getLong("size");
            String downloadUrl = jsonObject.getString("downloadUrl");
            String des = jsonObject.getString("des");
            String author = jsonObject.getString("author");
            String downloadNum = jsonObject.getString("downloadNum");
            String version = jsonObject.getString("version");
            String date = jsonObject.getString("date");

            JSONArray screenJson = jsonObject.getJSONArray("screen");
            List<String> screen = new ArrayList<String>();
            for (int i = 0; i < screenJson.length() ; i++)
            {
                screen.add(screenJson.getString(i));
            }

            JSONArray safeJson = jsonObject.getJSONArray("safe");
            List<String> safeUrl = new ArrayList<String>();
            List<String> safeDesUrl = new ArrayList<String>();
            List<String> safeDes = new ArrayList<String>();
            List<Integer> safeDesColor = new ArrayList<Integer>();

            for (int i = 0; i < safeJson.length(); i++)
            {
                JSONObject object = safeJson.getJSONObject(i);
                safeUrl.add(object.getString("safeUrl"));
                safeDesUrl.add(object.getString("safeDesUrl"));
                safeDes.add(object.getString("safeDes"));
                safeDesColor.add(object.getInt("safeDesColor"));
            }

            AppInfo appInfo = new AppInfo( id,  name,  packageName,  iconUrl, stars,
             size, downloadUrl,  des,  downloadNum, version,
                 date, author, screen,  safeUrl,
               safeDesUrl, safeDes, safeDesColor);

            Log.d("jiguang ", "depr parseJson: " + appInfo);
    //        datas.add(appInfo);
            return  appInfo;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String getParams()
    {
        return "&packageName=" + packageName;
    }
}
