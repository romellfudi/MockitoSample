package fudi.freddy.mokitosample.store;

import fudi.freddy.mokitosample.model.Contact;
import com.romellfudi.sharepreference.SharePreferenced;

/**
 * @autor Romell Domínguez
 * @date 26/02/17
 * @version 1.0
 */


public class SharePreferenceContact implements StoreContract<Contact> {
    @Override
    public void save(Contact object) {
        SharePreferenced.save(object,Contact.class);
    }

    @Override
    public Contact load(String mail) {
        return SharePreferenced.load(Contact.class);
    }
}
