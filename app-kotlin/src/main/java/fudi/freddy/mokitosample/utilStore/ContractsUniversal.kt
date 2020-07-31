package fudi.freddy.mokitosample.utilStore

import fudi.freddy.mokitosample.store.SharePreferenceContact
import fudi.freddy.mokitosample.store.StoreContract

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
object ContractsUniversal {
    val storeContract: StoreContract<*>
        get() = SharePreferenceContact()
}