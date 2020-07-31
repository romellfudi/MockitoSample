package fudi.freddy.mokitosample.share

import com.nhaarman.mockitokotlin2.mock
import fudi.freddy.mokitosample.share.ShareContract.ShareView
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
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
class ShareUnitTest {

    @RelaxedMockK
    lateinit var shareView: ShareView

    @Mock
    internal val repository: Repository = mock()

    lateinit var sharePresent: SharePresent

    val captorString = slot<String>()

    @Before
    fun prepare() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        sharePresent = SharePresent(shareView)
    }

    @Test
    fun saveLoad() {
        val toSave = "save"
        sharePresent.visibleForTesting(repository)
        sharePresent.saveInput(toSave)
        verify { shareView.clearText() }
        verify { shareView.reLoadList() }
        `when`(repository.load()).thenReturn(toSave)
        sharePresent.loadInput()
        verify { shareView.loadText(capture(captorString)) }
        assertThat(toSave, equalTo(captorString.captured))
    }
}