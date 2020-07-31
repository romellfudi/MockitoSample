package fudi.freddy.mokitosample.contact_view

import android.support.annotation.VisibleForTesting
import fudi.freddy.mokitosample.contact_view.ContactContract.ContactView
import fudi.freddy.mokitosample.contact_view.ContactContract.UserActions
import fudi.freddy.mokitosample.model.Contact
import fudi.freddy.mokitosample.store.StoreContract
import fudi.freddy.mokitosample.utilStore.ContractsUniversal

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */
class ContactPresent(private val contactView: ContactView) : UserActions {
    private var storeContract = ContractsUniversal.storeContract as StoreContract<Contact>

    override fun saveContact() {
        storeContract.save(contactView.contact)
        contactView.clearText()
        contactView.display("Data guardada")
    }

    override fun loadContact(mail: String?) {
        val value = storeContract.load(mail!!)
        contactView.display(value)
    }

    internal fun visibleForTesting(storeContract: StoreContract<Contact>){
        this.storeContract = storeContract
    }

}