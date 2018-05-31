package com.onwl007.apiblog.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author onwl007@126.com
 * @date 2018/5/31 21:39
 * @desc
 */
public class IPLocation {
    public String getLocation(String ip) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(2000);
            connection.setReadTimeout(2000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            JsonObject data=new JsonParser().parse(buffer.toString()).getAsJsonObject().getAsJsonObject("data");
            return data.get("region").getAsString()+" "+data.get("city").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
        }
        return null;
    }
}
