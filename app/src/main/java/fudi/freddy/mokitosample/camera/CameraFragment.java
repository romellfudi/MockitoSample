package fudi.freddy.mokitosample.camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import fudi.freddy.mokitosample.R;

import static android.app.Activity.RESULT_OK;


/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class CameraFragment extends Fragment implements CameraContract.CameraView {

    CameraPresenter cameraPresenter;

    private CameraFragment() {
    }

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
    static final int REQUEST_IMAGE_CAPTURE = 1;

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
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource,
                                                GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                    }
                });
//        imageView.setImageBitmap(path);
    }

    @Override
    public void showDefaultPicture() {
        imageView.setImageBitmap(null);
    }

    @Override
    public void openCamera(String path) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {

//                Uri photoURI = FileProvider.getUriForFile(getActivity(),
//                        getActivity().getPackageName(),
//                        photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(path));
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            cameraPresenter.viewPicture((Bitmap) data.getExtras().get("data"));
            cameraPresenter.viewPicture();
        } else {
            cameraPresenter.viewDefaultPicture();
        }
    }

}
