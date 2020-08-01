package fudi.freddy.mokitosample.mvp_sevice

import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.romellfudi.sharepreference.SharePreferenced
import java.util.*

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
class ModelPresent : AbstractPresent<ModelAct?>() {

    private var modelDataReceiver: ModelDataReceiver = ModelDataReceiver()

    override fun attach(actView: ModelAct?) {
        super.attach(actView)
        registerReceiver()
        val serviceIntent = Intent(view?.appContext, ModelService::class.java)
        Log.d("ModelPresent", "starting")
        view?.appContext?.startService(serviceIntent)
    }

    private fun registerReceiver() {
        Log.d("ModelPresent", "registering...")
        LocalBroadcastManager.getInstance(view?.appContext!!)
                .registerReceiver(modelDataReceiver, IntentFilter("ACTION"))
        Log.d("ModelPresent", "registered")
    }

    private fun unRegisterReceiver() {
        Log.d("ModelPresent", "unregistering...")
        LocalBroadcastManager.getInstance(view?.appContext!!)
                .unregisterReceiver(modelDataReceiver)
        Log.d("ModelPresent", "unregistered")
    }

    override fun detach() {
        val serviceIntent = Intent(view?.appContext, ModelService::class.java)
        view?.appContext?.stopService(serviceIntent)
        unRegisterReceiver()
        super.detach()
    }

    private inner class ModelDataReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == "ACTION") {
                Log.d("ModelPresent", "receive")
                val arrayList = SharePreferenced.load(ArrayList::class.java)
                val adapter: ArrayAdapter<*> = ArrayAdapter(context,
                        R.layout.simple_list_item_2, arrayList)
                view?.listView!!.adapter = adapter
                view?.listView!!.visibility = View.VISIBLE
            } else {
                Log.d("ModelPresent", "NO-ACTION")
            }
        }
    }
}