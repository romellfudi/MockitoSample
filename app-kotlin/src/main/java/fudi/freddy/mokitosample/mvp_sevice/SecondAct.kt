package fudi.freddy.mokitosample.mvp_sevice

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ListView
import fudi.freddy.mokitosample.R

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
class SecondAct : AppCompatActivity(), ModelAct {
    override var listView: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modelo)
        listView = findViewById<View>(R.id.listView) as ListView
        if (modelPresent == null) {
            modelPresent = ModelPresent()
        }
        Log.d("SecondAct", "prepareAttach")
        modelPresent!!.attach(this)
    }

    override val appContext: Context?
        get() = applicationContext

    override fun onDestroy() {
        super.onDestroy()
        modelPresent!!.detach()
        Log.d("SecondAct", "postDeattach")
    }

    companion object {
        private var modelPresent: ModelPresent? = null
    }
}