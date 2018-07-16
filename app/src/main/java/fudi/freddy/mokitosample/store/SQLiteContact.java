package fudi.freddy.mokitosample.store;

import fudi.freddy.mokitosample.model.Contact;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 2/26/17
 */

public class SQLiteContact implements StoreContract<Contact> {
    @Override
    public void save(Contact object) {
        DatabaseHelper.getInstance().createContact(object);
    }

    @Override
    public Contact load(String mail) {
        return DatabaseHelper.getInstance().getContact(mail);
    }
}
