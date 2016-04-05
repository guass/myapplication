package com.guass.navapp.protocol;

import android.os.Environment;
import android.util.Log;

import com.guass.navapp.bean.AppInfo;
import com.guass.navapp.http.HttpHelper;
import com.guass.navapp.utils.FileUtils;
import com.lidroid.xutils.util.IOUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guass on 2016/3/24.
 */
public abstract class BaseProtocol <T>{

    public T load(int index)
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


    private String loadServer(int index)
    {
        String json = null;
        Log.d("jiguang", "loadServer: getpp ==" + getParams());
        HttpHelper.HttpResult httpResult = HttpHelper.get(HttpHelper.URL + getKey()+ "?index=" + index + getParams());
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

    public String getParams() {
        return "";
    }


    private String loadLocal(int index)
    {
        //if the time is out them no local native
        File cacheDir = FileUtils.getCacheDir();
        File cacheFile = new File(cacheDir, getKey() + "_" + index + getParams());
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

    private void saveLocal(String json, int index)
    {
        //write a time in the text to remember
        BufferedWriter bw = null;
        File dir = FileUtils.getCacheDir();
//        if(getKey().equals("detail"))
//        {
//            return;
//        }
        try
        {
            File file = new File(dir,getKey() + "_" + index + getParams());
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

    /**
     * 说明访问网络关键字
     * @return
     */
    public abstract String getKey();


    public abstract T parseJson(String json);

}
