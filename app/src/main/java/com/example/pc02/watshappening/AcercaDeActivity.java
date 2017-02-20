package com.example.pc02.watshappening;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AcercaDeActivity extends AppCompatActivity {

    String nameAct = "AcercaDeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);
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
        MyLog.d(nameAct, "OnDestroy...");
        super.onDestroy();
    }
}
