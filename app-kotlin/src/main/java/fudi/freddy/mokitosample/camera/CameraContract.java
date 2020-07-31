package fudi.freddy.mokitosample.camera;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public interface CameraContract {

    interface CameraView{
        void openCamera(String path);
        void loadPicture(String bitmap);
        void showDefaultPicture();
    }
    interface CameraUserActions {
        void takePicture();
        void viewPicture();
        void viewDefaultPicture();
    }
}
