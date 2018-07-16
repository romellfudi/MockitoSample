package fudi.freddy.mokitosample.share;

import fudi.freddy.mokitosample.App;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class RepositoryShare implements Repository {

    private RepositoryShare(){

    }

    public static RepositoryShare getInstance() {
        return new RepositoryShare();
    }
    @Override
    public void save(String newData) {
        App.getPrefer().edit().putString("text",newData).commit();
    }

    @Override
    public String load() {
        return App.getPrefer().getString("text", "");
    }
}
