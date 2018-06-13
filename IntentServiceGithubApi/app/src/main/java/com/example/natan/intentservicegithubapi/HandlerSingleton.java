package com.example.natan.intentservicegithubapi;

import android.os.Handler;
import android.os.Looper;

class HandlerSingleton extends android.os.Handler {
    private static HandlerSingleton ourInstance = null;

    static void init(Looper looper, Handler.Callback callback){
        ourInstance = new HandlerSingleton(looper, callback);
    }

    static HandlerSingleton getInstance() {
        return ourInstance;
    }

    private HandlerSingleton(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
