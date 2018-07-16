package fudi.freddy.mokitosample.mvp_sevice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;

import fudi.freddy.sharepreferencelib.SharePreferenced;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class ModelService extends IntentService {
    public ModelService() {
        super("ModelService");
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        if (intent!=null){
            final IntentService intentService= this;
            Log.d("fudi","prepare");

            Log.d("fudi","running");
            Intent response = new Intent();
            ArrayList arrayList = new ArrayList<>();
            arrayList.add("uno");
            arrayList.add("dos");
            arrayList.add("tres");
            SharePreferenced.save(arrayList,ArrayList.class);
            intent.setAction("ACTION");
            LocalBroadcastManager.getInstance(intentService).sendBroadcast(response);
            Log.d("fudi","run");
        }else {
            Log.d("fudi","null");
        }
    }
}
