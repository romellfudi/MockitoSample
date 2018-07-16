package fudi.freddy.mokitosample.contact_view;

import fudi.freddy.mokitosample.model.Contact;
import fudi.freddy.mokitosample.store.StoreContract;
import fudi.freddy.mokitosample.utilStore.ContractsUniversal;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class ContactPresent implements ContactContract.UserActions {

    private ContactContract.ContactView contactView;
    private StoreContract storeContract = ContractsUniversal.getStoreContract();

    public ContactPresent(ContactContract.ContactView contactView) {
        this.contactView = contactView;
    }

    @Override
    public void saveContact() {
//        storeContract.save(new Contact(
//                contactView.loadInput3(),
//                contactView.loadInput2(),
//                contactView.loadInput1(),
//                contactView.loadInput4(),
//                contactView.loadInput5()
//        ));
        storeContract.save(contactView.getContactView());
        contactView.clearText();
        contactView.display("Data guardada");
    }

    @Override
    public void loadContact(String mail) {
        contactView.display((Contact) storeContract.load(mail));
    }
}
