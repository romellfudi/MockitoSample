package fudi.freddy.mokitosample.mvp_sevice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import fudi.freddy.mokitosample.R;


/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class SecondAct extends AppCompatActivity implements ModelAct {

    private static ModelPresent modelPresent;
    ListView listView = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modelo);
        listView = (ListView) findViewById(R.id.listView);
        if (modelPresent == null){
            modelPresent = new ModelPresent();
        }
        Log.d("MMM","prepareAttach");
        modelPresent.attach(this);
    }

    @Override
    public Context getActContext() {
        return this;
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public ListView getListView() {
        return listView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        modelPresent.detach();
    }
}
