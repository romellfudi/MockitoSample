package fudi.freddy.mokitosample.contact_view

import com.nhaarman.mockitokotlin2.mock
import fudi.freddy.mokitosample.contact_view.ContactContract.ContactView
import fudi.freddy.mokitosample.model.Contact
import fudi.freddy.mokitosample.store.StoreContract
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.mockito.Mock
import org.mockito.Mockito.`when`

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ContactUnitTest {

    @RelaxedMockK
    lateinit var contactView: ContactView

    @RelaxedMockK
    @Mock
    lateinit var storeContract: StoreContract<Contact>

    @Mock
    internal val storeContractget: StoreContract<Contact> = mock()

    lateinit var contactPresent: ContactPresent

    val captorString = slot<String>()
    val captorContact = slot<Contact>()

    @Before
    fun prepare() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        contactPresent = ContactPresent(contactView)
    }

    @Test
    fun contactFragment() {
        val contact = Contact("BOOSTTAG", "b", "@mail.com", "946123456", "engineer")
        every { contactView.contact } returns contact

        contactPresent.visibleForTesting(storeContract)
        contactPresent.saveContact()

        verify { storeContract.save(capture(captorContact)) }
        assertThat(captorContact.captured.company, `is`(equalTo("BOOSTTAG")))
        assertThat(captorContact.captured.position, `is`(equalTo("engineer")))
        assertThat(captorContact.captured.cell, `is`(equalTo("946123456")))

        verify { contactView.clearText() }
        verify { contactView.display(capture(captorString)) }
        assertThat(captorString.captured, `is`(equalTo("Data guardada")))

        contactPresent.visibleForTesting(storeContractget)
        `when`(storeContractget.load("@mail.com")).thenAnswer { contact }

        contactPresent.loadContact("@mail.com")
        verify { contactView.display(capture(captorContact)) }
        assertThat(captorContact.captured.company, `is`(equalTo("BOOSTTAG")))
    }
}