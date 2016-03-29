package com.guass.navapp.protocol;

import android.util.Log;

import com.guass.navapp.bean.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guass on 2016/3/24.
 */
public class HomeProtocol extends BaseProtocol<List<AppInfo>> {

    List<String> pictrueUrl;

    public List<String> getPictrueUrl() {
        return pictrueUrl;
    }

    @Override
    public String getKey() {
        return "home";
    }

    @Override
    public List<AppInfo> parseJson(String json) {
        List<AppInfo> datas = new ArrayList<AppInfo>();
         pictrueUrl = new ArrayList<String>();

        try
        {
            JSONObject object = new JSONObject(json);
            JSONArray picture = object.getJSONArray("picture");
            for (int i = 0; i < picture.length() ; i++)
            {
               String picStr =  picture.getString(i);
                pictrueUrl.add(picStr);
            }
            Log.d("jiguang", "parseJson: picUrl =="  + pictrueUrl);


            JSONArray list = object.getJSONArray("list");
            for (int i = 0; i < list.length(); i++)
            {
                JSONObject jsonObj = list.getJSONObject(i);
                long id = jsonObj.getLong("id");
                String name = jsonObj.getString("name");
                String packageName = jsonObj.getString("packageName");
                String iconUrl = jsonObj.getString("iconUrl");
                double stars = jsonObj.getDouble("stars");
                long size = jsonObj.getLong("size");
                String downloadUrl = jsonObj.getString("downloadUrl");
                String des = jsonObj.getString("des");

                AppInfo appInfo = new AppInfo(des,downloadUrl,iconUrl,id,name,packageName,size,stars);
                datas.add(appInfo);
            }

            return datas;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
