package fudi.freddy.mokitosample.camera

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */
interface CameraContract {
    interface CameraView {
        fun openCamera(path: String?)
        fun loadPicture(bitmap: String?)
        fun showDefaultPicture()
    }

    interface CameraUserActions {
        fun takePicture()
        fun viewPicture()
        fun viewDefaultPicture()
    }
}