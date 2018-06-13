package com.example.natan.intentservicegithubapi;

import com.example.natan.intentservicegithubapi.model.Repository;
import com.example.natan.intentservicegithubapi.ssl.NoSSLv3SocketFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class Util {

    public static String requestData(URL url) throws IOException {
        String anwser = "";
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1");
            sslContext.init(null, null, null);
            NoSSLv3SocketFactory factory = new NoSSLv3SocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultSSLSocketFactory(factory);

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while((inputStr = streamReader.readLine()) != null){
                responseStrBuilder.append(inputStr);
            }

            anwser = responseStrBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return anwser;

    }

    public List<Repository> formatData(String data) {
        try {
            JSONArray array = new JSONArray(data);

            List<Repository> repositoryList = new ArrayList<>();

            for(int i = 0 ; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

                Repository repository = new Repository();

                repository.setName(object.getString("name"));
                repository.setAutorName(object.getJSONObject("owner").getString("login"));

                String description = object.getString("description");
                if(description.equals("null")) repository.setDescription("No there's description :(");
                else repository.setDescription(description);

                repository.setImageLink(object.getJSONObject("owner").getString("avatar_url"));

                repositoryList.add(repository);
            }

            return repositoryList;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Collections.EMPTY_LIST;
    }
}
