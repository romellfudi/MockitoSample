package fudi.freddy.mokitosample.share

import fudi.freddy.mokitosample.App

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
class RepositoryShare private constructor() : Repository {

    override fun save(newData: String?) {
        App.prefer.edit().putString("text", newData).apply()
    }

    override fun load(): String? {
        return App.prefer.getString("text", "")
    }

    companion object {
        var instance: Repository  = RepositoryShare()
    }
}