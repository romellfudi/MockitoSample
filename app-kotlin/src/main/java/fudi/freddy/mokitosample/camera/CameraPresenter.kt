package fudi.freddy.mokitosample.camera

import android.os.Environment
import android.support.annotation.VisibleForTesting
import fudi.freddy.mokitosample.camera.CameraContract.CameraUserActions
import fudi.freddy.mokitosample.camera.CameraContract.CameraView
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */
class CameraPresenter(private val cameraView: CameraView) : CameraUserActions {

    var mImageFile: File? = null

    @VisibleForTesting
    fun pullImageFile(): File? {
        return mImageFile
    }

    override fun takePicture() {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)
        try {
            mImageFile = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",  /* suffix */
                    storageDir /* directory */
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        cameraView.openCamera(mImageFile!!.path)
    }

    override fun viewPicture() {
        if (mImageFile!!.exists()) {
            cameraView.loadPicture(mImageFile!!.path)
        } else viewDefaultPicture()
    }

    override fun viewDefaultPicture() {
        mImageFile!!.delete()
        cameraView.showDefaultPicture()
    }

}