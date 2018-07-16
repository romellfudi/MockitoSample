package fudi.freddy.mokitosample;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import fudi.freddy.mokitosample.store.DatabaseHelper;
import fudi.freddy.sharepreferencelib.SharePreferenced;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        DatabaseHelper.open(context,
                DatabaseHelper.DATABASE_DIRECTORY,
                DatabaseHelper.DATABASE_VERSION
        );
        SharePreferenced.init(context);
    }

    public static Context getContext() {
        return context;
    }

    public static SharedPreferences getPrefer() {
        return context.getSharedPreferences(App.class.getSimpleName(), MODE_PRIVATE);
    }

}
