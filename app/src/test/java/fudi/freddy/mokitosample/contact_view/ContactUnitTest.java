package fudi.freddy.mokitosample.contact_view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fudi.freddy.mokitosample.model.Contact;
import fudi.freddy.mokitosample.store.StoreContract;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class ContactUnitTest {

    @Mock
    ContactContract.ContactView contactView;
    @Mock
    StoreContract storeContract;
//    @Mock
//    ShareContract.ShareUserActions shareUserActions;

    @InjectMocks
    ContactPresent contactPresent = new ContactPresent(contactView);

    @Captor
    private ArgumentCaptor<String> captorString;

    @Before
    public void prepare() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void contactFragment() {

        Contact contact = new Contact("a", "b", "@mail.com", "d", "e");
        contactView.display(contact);
        contactPresent.saveContact();
        verify(storeContract).save(any(Contact.class));
        verify(contactView).clearText();
        verify(contactView).display(captorString.capture());
        assertThat(captorString.getValue(),is("Data guardada"));
        contactPresent.loadContact("@mail.com");
        verify(contactView, times(2)).display(any(Contact.class));
    }
}
