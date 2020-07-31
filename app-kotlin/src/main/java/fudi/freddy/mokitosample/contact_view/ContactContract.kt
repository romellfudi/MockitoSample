package fudi.freddy.mokitosample.contact_view

import fudi.freddy.mokitosample.model.Contact

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */
interface ContactContract {
    interface ContactView {
        fun clearText()
        fun loadInput1(): String
        fun loadInput2(): String
        fun loadInput3(): String
        fun loadInput4(): String
        fun loadInput5(): String
        val contact: Contact
        fun display(message: String?)
        fun display(contact: Contact?)
    }

    interface UserActions {
        fun saveContact()
        fun loadContact(mail: String?)
    }
}