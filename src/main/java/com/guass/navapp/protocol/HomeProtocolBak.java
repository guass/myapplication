package com.guass.navapp.protocol;

import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;


import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.FileUtils;
import com.guass.navapp.utils.Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.IOUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guass on 2016/3/18.
 */
public class HomeProtocolBak {



    public List<AppInfo> load(int index)
    {
        Log.i("jiguang","json =======111 " + Environment.getExternalStorageDirectory().getAbsolutePath());
        String json = loadLocal(index);
        if(json == null)
        {
           json =  loadServer(index);
            if(json != null)
            {
                saveLocal(json, index);
            }
        }

        if(json != null)
        {
           return parseJson(json);
        }
        else
        {
            return null;
        }

    }

    private List<AppInfo> parseJson(String json)
    {
        List<AppInfo> datas = new ArrayList<AppInfo>();
        try
        {
            JSONObject object = new JSONObject(json);
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

    private void saveLocal(String json, int index)
    {
        //write a time in the text to remember
        BufferedWriter bw = null;
        File dir = FileUtils.getCacheDir();

        try
        {
            File file = new File(dir,"home_" + index);
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(System.currentTimeMillis() + 1000*60*200 +"");
            bw.newLine();
            bw.write(json);
            bw.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
                IOUtils.closeQuietly(bw);
        }

    }

    private String loadLocal(int index)
    {
        //if the time is out them no local native
        File cacheDir = FileUtils.getCacheDir();
        File cacheFile = new File(cacheDir, "home_" + index);
        BufferedReader br = null;
        FileReader fr = null;
        try
        {
            fr = new FileReader(cacheFile);
            br = new BufferedReader(fr);
            long outOfdate = Long.parseLong(br.readLine());
            if(System.currentTimeMillis() > outOfdate)
            {
                return null;
            }
            else
            {
                String str = null;
                StringWriter sw = new StringWriter();
                while ((str = br.readLine()) != null)
                {
                    sw.write(str);
                }
                return  sw.toString();
            }

        }
        catch (Exception e)
        {
                e.printStackTrace();
            return  null;
        }
        finally
        {
            IOUtils.closeQuietly(fr);
            IOUtils.closeQuietly(br);
        }


    }

    private String loadServer(int index)
    {
        String json = null;
        HttpHelper.HttpResult httpResult = HttpHelper.get(HttpHelper.URL + "home" + "?index=" + index);
        if(httpResult != null)
        {
             json = httpResult.getString();
        }

        Log.i("jiguang","json == " + json);
        if(json != null)
        {
            return  json;
        }

        return  null;

    }
}
