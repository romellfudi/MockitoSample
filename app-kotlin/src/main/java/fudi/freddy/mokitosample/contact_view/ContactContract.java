package fudi.freddy.mokitosample.contact_view;

import fudi.freddy.mokitosample.model.Contact;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public interface ContactContract {

    interface ContactView{
        void clearText();
        String loadInput1();
        String loadInput2();
        String loadInput3();
        String loadInput4();
        String loadInput5();
        Contact getContactView();
        void display(String message);
        void display(Contact contact);

    }

    interface UserActions{
        void saveContact();
        void loadContact(String mail);
    }
}
