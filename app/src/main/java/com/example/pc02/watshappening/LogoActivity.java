package com.example.pc02.watshappening;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {

    String nameAct = "LogoActivity";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run(){
                Intent intent = new Intent(getApplicationContext(), ListadoActivity.class);
                startActivity(intent);
                finish();
            }

        }, 3000);

    }

    @Override
    protected void onStart() {
        MyLog.d(nameAct, "OnStart...");
        super.onStart();
    }

    @Override
    protected void onResume() {
        MyLog.d(nameAct, "OnResume...");
        super.onResume();
    }

    @Override
    protected void onPause() {
        MyLog.d(nameAct, "OnPause...");
        super.onPause();
    }

    @Override
    protected void onStop() {
        MyLog.d(nameAct, "OnStop...");
        super.onStop();

    }

    @Override
    protected void onRestart() {
        MyLog.d(nameAct, "OnRestart...");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        MyLog.d(nameAct, "OnDestroy...");
        super.onDestroy();
    }
}
