package fudi.freddy.mokitosample.store

import com.romellfudi.sharepreference.SharePreferenced
import fudi.freddy.mokitosample.model.Contact

/**
 * @autor Romell Domínguez
 * @date 7/25/20
 * @version 1.0
 */
class SharePreferenceContact : StoreContract<Contact?> {
    override fun save(`object`: Contact?) {
        SharePreferenced.save(`object`, Contact::class.java)
    }

    override fun load(mail: String): Contact? {
        return SharePreferenced.load(Contact::class.java)
    }
}