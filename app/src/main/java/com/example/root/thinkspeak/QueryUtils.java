package com.example.root.thinkspeak;

import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 24/2/17.
 */

public final class QueryUtils {
    

    private QueryUtils() {
    }

    public static Channel fetchFieldsData(String requestUrl){
        URL url=createUrl(requestUrl);
        String jsonResponse=null;

        try{
            jsonResponse=makeHttpRequest(url);
            if (jsonResponse==null){
                Log.v(QueryUtils.class.getSimpleName(), "Json response nullXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            }
            else{
                Log.v(QueryUtils.class.getSimpleName(), "Json response XXXXXXXXXXXXXXXXXXXXXXXXXXXX");

            }
            Log.v(QueryUtils.class.getSimpleName(), "Crazzzzzzzzzzzzzzzzzzzzzy"+jsonResponse);

        } catch (IOException e) {
            Log.e(QueryUtils.class.getSimpleName(), "Problem making the HTTP request.", e);
        }
     Channel chann= extractFieldsFromJson(jsonResponse);
        return  chann;

    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(QueryUtils.class.getSimpleName(), "Error response code: " + urlConnection.getResponseCode());

            }

        } catch (IOException e) {
            Log.e(QueryUtils.class.getSimpleName(), "Problem retrieving the Channel JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;

    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset().forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static Channel extractFieldsFromJson(String channelJson) {
        Channel channel = null;
        String field1="";
        String field2="";
        String field3="";
        String field4="";
        String field5="";
        String field6="";
        String field7="";
        String field8="";

        if (TextUtils.isEmpty(channelJson)) {
            return null;
        }
      


        try {

            JSONObject baseJsonResponse = new JSONObject(channelJson);
            JSONObject channelobject = baseJsonResponse.getJSONObject("channel");
            if (channelobject.has("field1")){
             field1=channelobject.getString("field1");}
            if (channelobject.has("field2")){
             field2=channelobject.getString("field2");}
            if (channelobject.has("field3")){
                field3=channelobject.getString("field3");}
            if (channelobject.has("field4")){
             field4=channelobject.getString("field4");}
            if (channelobject.has("field5")){
             field5=channelobject.getString("field5");}
            if (channelobject.has("field6")){
             field6=channelobject.getString("field6");}
            if (channelobject.has("field7")){
             field7=channelobject.getString("field7");}
            if (channelobject.has("field8")){
             field8=channelobject.getString("field8");}



            channel=new Channel(field1,field2,field3,field4,field5,field6,field7,field8);
            


        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (channel==null){
            Log.v(QueryUtils.class.getSimpleName(),"Channel is still empty XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        }
        else {
            Log.v(QueryUtils.class.getSimpleName(),"Channel is XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        }
        return channel;
    }
}