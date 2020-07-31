package fudi.freddy.mokitosample.camera;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

import fudi.freddy.mokitosample.BuildConfig;
import fudi.freddy.mokitosample.R;

import static android.app.Activity.RESULT_OK;


/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class CameraFragment extends Fragment implements CameraContract.CameraView {

    CameraPresenter cameraPresenter;

    public static Fragment getInstance() {
        return new CameraFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cameraPresenter = new CameraPresenter(this);
    }

    Button button;
    ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 123;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, null);
        button = (Button) view.findViewById(R.id.btn);
        imageView = (ImageView) view.findViewById(R.id.img);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraPresenter.takePicture();
            }
        });
        return view;
    }

    @Override
    public void loadPicture(String path) {
        Glide.with(this)
                .load(new File(path))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void showDefaultPicture() {
        Glide.with(this).load(R.mipmap.ic_launcher).into(imageView);
    }

    @Override
    public void openCamera(String path) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    FileProvider.getUriForFile(getContext(),
                            BuildConfig.APPLICATION_ID + ".provider", new File(path)));
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            cameraPresenter.viewPicture();
        } else {
            cameraPresenter.viewDefaultPicture();
        }
    }

}
