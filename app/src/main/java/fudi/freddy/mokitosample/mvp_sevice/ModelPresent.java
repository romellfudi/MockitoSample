package fudi.freddy.mokitosample.mvp_sevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import fudi.freddy.sharepreferencelib.SharePreferenced;


/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class ModelPresent extends AbstractPresent<ModelAct> {

    private ModelDataReceiver modelDataReceiver;


    @Override
    public void attach(ModelAct modelAct) {
        super.attach(modelAct);
        registerReceiver();
        Intent serviceIntent = new Intent(getView().getAppContext(), ModelService.class);
        Log.d("MMM","starting");
        getView().getAppContext().startService(serviceIntent);
    }

    private void registerReceiver() {
        modelDataReceiver = new ModelDataReceiver();
        Log.d("MMM","registering...");
        LocalBroadcastManager.getInstance(getView().getAppContext())
                .registerReceiver(modelDataReceiver, new IntentFilter("ACTION"));
        Log.d("MMM","registered");
    }

    private void unRegisterReceiver() {
        if (modelDataReceiver != null) {
            Log.d("MMM","unregistering...");
            LocalBroadcastManager.getInstance(getView().getAppContext())
                    .unregisterReceiver(modelDataReceiver);
            Log.d("MMM","unregistered");
        }
    }

    @Override
    public void detach() {
        super.detach();
        Intent serviceIntent = new Intent(getView().getAppContext(), ModelService.class);
        getView().getAppContext().stopService(serviceIntent);
        unRegisterReceiver();
    }

    private class ModelDataReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("ACTION")) {
                Log.d("MMM","receive");
                ModelAct activityView = getView();
                ArrayList arrayList = SharePreferenced.load(ArrayList.class);
                ArrayAdapter adapter = new ArrayAdapter(context,
                        android.R.layout.simple_list_item_2, arrayList);
                activityView.getListView().setAdapter(adapter);
                activityView.getListView().setVisibility(View.VISIBLE);
            }else {
                Log.d("MMM","NO-ACTION");
            }
        }
    }
}
