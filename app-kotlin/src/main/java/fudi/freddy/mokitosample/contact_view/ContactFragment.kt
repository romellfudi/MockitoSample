package fudi.freddy.mokitosample.contact_view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import fudi.freddy.mokitosample.R
import fudi.freddy.mokitosample.contact_view.ContactContract.ContactView
import fudi.freddy.mokitosample.model.Contact
import kotlinx.android.synthetic.main.fragment_contact.*

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */
class ContactFragment @SuppressLint("ValidFragment") internal constructor() : Fragment(), ContactView {

    var contactPresent: ContactPresent = ContactPresent(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_contact, container, false)
        save.setOnClickListener { contactPresent.saveContact() }
        load.setOnClickListener { contactPresent.loadContact(et_1.text.toString().trim { it <= ' ' }) }
        return view
    }

    override fun clearText() {
        et_1!!.setText("")
        et_2!!.setText("")
        et_3!!.setText("")
        et_4!!.setText("")
        et_5!!.setText("")
    }

    override fun loadInput1(): String {
        return et_1.text.toString().trim { it <= ' ' }
    }

    override fun loadInput2(): String {
        return et_2.text.toString().trim { it <= ' ' }
    }

    override fun loadInput3(): String {
        return et_3.text.toString().trim { it <= ' ' }
    }

    override fun loadInput4(): String {
        return et_4.text.toString().trim { it <= ' ' }
    }

    override fun loadInput5(): String {
        return et_5.text.toString().trim { it <= ' ' }
    }

    override fun display(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override val contact: Contact
        get() = Contact(
                loadInput3(),
                loadInput2(),
                loadInput1(),
                loadInput4(),
                loadInput5()
        )

    override fun display(contact: Contact?) {
        if (contact != null) {
            et_1.setText(contact.email)
            et_2.setText(contact.name)
            et_3.setText(contact.cell)
            et_4.setText(contact.position)
            et_5.setText(contact.company)
            display("Datos recuperados")
        } else display("No encontrado")
    }

    companion object {
        val instance: ContactFragment
            get() = ContactFragment()
    }

}