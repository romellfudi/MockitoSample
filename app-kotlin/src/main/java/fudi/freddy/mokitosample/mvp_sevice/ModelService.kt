package fudi.freddy.mokitosample.mvp_sevice

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.romellfudi.sharepreference.SharePreferenced
import java.util.*

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
class ModelService : IntentService("ModelService") {
    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val intentService: IntentService = this
            Log.d("fudi", "prepare")
            Log.d("fudi", "running")
            val response = Intent()
            val arrayList = ArrayList<String?>()
            arrayList.add("uno")
            arrayList.add("dos")
            arrayList.add("tres")
            SharePreferenced.save(arrayList, ArrayList::class.java)
            intent.action = "ACTION"
            LocalBroadcastManager.getInstance(intentService).sendBroadcast(response)
            Log.d("fudi", "run")
        } else {
            Log.d("fudi", "null")
        }
    }
}