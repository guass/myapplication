package com.guass.navapp.protocol;

import android.util.Log;

import com.guass.navapp.bean.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guass on 2016/3/28.
 */
public class AppProtocol extends BaseProtocol <List<AppInfo>>{
    @Override
    public String getKey() {
        return "app";
    }

    @Override
    public List<AppInfo> parseJson(String json) {

        List<AppInfo>  datas = new ArrayList<AppInfo>();

        try
        {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                long id = jsonObject.getLong("id");
                String name = jsonObject.getString("name");
                String packageName = jsonObject.getString("packageName");
                String iconUrl = jsonObject.getString("iconUrl");
                double stars = jsonObject.getDouble("stars");
                long size = jsonObject.getLong("size");
                String downloadUrl = jsonObject.getString("downloadUrl");
                String des = jsonObject.getString("des");

                AppInfo appInfo = new AppInfo(des,downloadUrl,iconUrl,id,name,packageName,size,stars);
                datas.add(appInfo);

            }
            Log.d("jiguang ", "load: ApplicationFragment  ApplicationFragment datas === " + datas);
            return  datas;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
