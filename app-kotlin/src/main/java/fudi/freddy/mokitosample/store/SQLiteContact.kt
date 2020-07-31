package fudi.freddy.mokitosample.store

import fudi.freddy.mokitosample.model.Contact

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
class SQLiteContact : StoreContract<Contact?> {
    override fun save(`object`: Contact?) {
        DatabaseHelper.instance!!.createContact(`object`)
    }

    override fun load(mail: String): Contact? {
        return DatabaseHelper.instance!!.getContact(mail)
    }
}