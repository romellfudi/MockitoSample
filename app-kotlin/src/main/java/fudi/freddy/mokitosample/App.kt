package fudi.freddy.mokitosample

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.romellfudi.sharepreference.SharePreferenced
import fudi.freddy.mokitosample.store.DatabaseHelper

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        DatabaseHelper.open(context,
                DatabaseHelper.DATABASE_DIRECTORY,
                DatabaseHelper.DATABASE_VERSION
        )
        SharePreferenced.init(context)
    }

    companion object {
        var context: Context? = null
            private set

        val prefer: SharedPreferences
            get() = context!!.getSharedPreferences(App::class.java.simpleName, Context.MODE_PRIVATE)
    }
}