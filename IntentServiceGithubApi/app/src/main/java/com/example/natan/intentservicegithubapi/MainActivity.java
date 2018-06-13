package com.example.natan.intentservicegithubapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.natan.intentservicegithubapi.model.Repository;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    protected static Handler myHandler;
    private EditText mUserNameEditText;
    private RecyclerView mUserListRecyclerView;
    private UserAdapter mUserAdapter;
    private ProgressBar mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserNameEditText = (EditText) findViewById(R.id.et_user_name);
        mUserListRecyclerView = (RecyclerView) findViewById(R.id.rv_user_list);
        mLoading = (ProgressBar) findViewById(R.id.pb_loading);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mUserListRecyclerView.setLayoutManager(linearLayoutManager);
        mUserListRecyclerView.setHasFixedSize(true);
        mUserAdapter = new UserAdapter();
        mUserListRecyclerView.setAdapter(mUserAdapter);

        // Inicializando a IntentService via Singleton
        Handler.Callback callback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                List<Repository> repositoryList = (List<Repository>) msg.obj;
                mUserAdapter.setmGithubApiData(repositoryList);

                mUserListRecyclerView.setVisibility(View.VISIBLE);
                mLoading.setVisibility(View.INVISIBLE);

                return false;
            }
        };
        HandlerSingleton.init(getApplication().getMainLooper(), callback);

        //via atributo estatico
//        myHandler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                Log.d("AGDEBUG", "Mensagem recebida via atributo");
//                super.handleMessage(msg);
//            }
//        };

    }

    public void makeSearch(View view) {
        mUserListRecyclerView.setVisibility(View.INVISIBLE);
        mLoading.setVisibility(View.VISIBLE);

        Intent intent = new Intent(this, MyIntentService.class);
        String userName = mUserNameEditText.getText().toString();
        intent.putExtra(Intent.EXTRA_TEXT, userName);
        startService(intent);
    }

    public void cleanList(View view) {
        mUserAdapter.setmGithubApiData(Collections.EMPTY_LIST);
    }
}
