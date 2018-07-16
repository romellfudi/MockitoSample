package fudi.freddy.mokitosample.camera;

import android.os.Environment;
import android.support.annotation.VisibleForTesting;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class CameraPresenter implements CameraContract.CameraUserActions {

    private CameraContract.CameraView cameraView;
    File mImageFile;

    public CameraPresenter(CameraContract.CameraView cameraView) {
        this.cameraView = cameraView;
    }

    @VisibleForTesting
    public File pullImageFile() {
        return this.mImageFile;
    }

    @Override
    public void takePicture() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        try {
            mImageFile = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",        /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        cameraView.openCamera(mImageFile.getPath());
    }

    @Override
    public void viewPicture() {
        if (mImageFile.exists()) {
            cameraView.loadPicture(mImageFile.getPath());
        } else viewDefaultPicture();
    }

    @Override
    public void viewDefaultPicture() {
        mImageFile.delete();
        cameraView.showDefaultPicture();

    }
}
