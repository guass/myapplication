package com.guass.navapp.protocol;

import android.os.Environment;
import android.util.Log;

import com.guass.navapp.bean.SubjectInfo;
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
 * Created by guass on 2016/3/23.
 */
public class SubjectProtocol extends BaseProtocol<List<SubjectInfo>>{


    @Override
    public String getKey() {
        return "subject";
    }

    @Override
    public List<SubjectInfo>  parseJson(String json) {


        List<SubjectInfo> datas = new ArrayList<SubjectInfo>();

        try 
        {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String des = jsonObject.getString("des");
                String url = jsonObject.getString("url");

                SubjectInfo subjectInfo = new SubjectInfo(des,url);
                datas.add(subjectInfo);
            }
            Log.d("jiguang", "subject datas : " + datas);
            return  datas;
        } 
        catch (JSONException e) 
        {
            e.printStackTrace();
            return  null;
        }

    }
}
