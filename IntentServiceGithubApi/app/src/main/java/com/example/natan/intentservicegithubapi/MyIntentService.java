package com.example.natan.intentservicegithubapi;

import android.app.IntentService;
import android.content.Intent;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.natan.intentservicegithubapi.model.Repository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("teste");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Util util = new Util();
        String userName = intent.getStringExtra(Intent.EXTRA_TEXT);
        try {
            URL url = new URL("https://api.github.com/users/"+userName+"/repos");
            String data = util.requestData(url);

            List<Repository> repositories = util.formatData(data);

            Message msg = new Message();
            msg.obj = repositories;

            //via singleton
            HandlerSingleton handler = HandlerSingleton.getInstance();
            handler.sendMessage(msg);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //via atributo estatico
        //MainActivity.myHandler.sendEmptyMessage(1);
    }

}
