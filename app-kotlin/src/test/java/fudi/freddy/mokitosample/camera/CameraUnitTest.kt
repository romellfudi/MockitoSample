package fudi.freddy.mokitosample.camera

import fudi.freddy.mokitosample.camera.CameraContract.CameraView
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

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CameraUnitTest {

    @RelaxedMockK
    lateinit var cameraView: CameraView

    lateinit var cameraPresenter: CameraPresenter

    val captorString = slot<String>()

    @Before
    fun preparate() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        cameraPresenter = CameraPresenter(cameraView)
    }

    @Test
    fun takePic() {
        cameraPresenter.takePicture()
        verify { cameraView.openCamera(capture(captorString)) }
        val path = captorString.captured
        show(path)
        cameraPresenter.viewPicture()
        verify(exactly = 1) { cameraView.loadPicture(capture(captorString)) }
        show(captorString.captured)
        assertThat(captorString.captured, equalTo(path))
        cameraPresenter.pullImageFile()!!.delete()
        cameraPresenter.viewPicture()
        verify(exactly = 1) { cameraView.showDefaultPicture() }
    }

    @Test
    fun defaultPic() {
        cameraPresenter.takePicture()
        verify { cameraView.openCamera(capture(captorString)) }
        show(captorString.captured)
        cameraPresenter.viewDefaultPicture()
        verify { cameraView.showDefaultPicture() }
    }

    private fun show(string: String) = println(string)
}