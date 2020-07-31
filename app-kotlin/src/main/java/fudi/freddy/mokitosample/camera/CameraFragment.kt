package fudi.freddy.mokitosample.camera

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import fudi.freddy.mokitosample.BuildConfig
import fudi.freddy.mokitosample.R
import fudi.freddy.mokitosample.camera.CameraContract.CameraView
import java.io.File

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */
const val REQUEST_IMAGE_CAPTURE = 123

class CameraFragment @SuppressLint("ValidFragment") internal constructor() : Fragment(), CameraView {

    var cameraPresenter: CameraPresenter = CameraPresenter(this)

    var button: Button? = null
    var imageView: ImageView? = null
    var uri: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_camera, null)
        button = view.findViewById<View>(R.id.btn) as Button
        imageView = view.findViewById<View>(R.id.img) as ImageView
        button!!.setOnClickListener { cameraPresenter.takePicture() }
        if (savedInstanceState != null) {
            loadPicture(savedInstanceState.getString("uri", null))
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("uri", uri)
        super.onSaveInstanceState(outState)
    }

    override fun loadPicture(path: String?) {
        uri = path
        if (path == null)
            showDefaultPicture()
        else Glide.with(this)
                .load(File(path))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView)
    }

    override fun showDefaultPicture() {
        Glide.with(this).load(R.mipmap.ic_launcher).into(imageView)
    }

    override fun openCamera(path: String?) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(context!!.packageManager) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    FileProvider.getUriForFile(context!!,
                            BuildConfig.APPLICATION_ID + ".provider", File(path)))
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            cameraPresenter.viewPicture()
        } else {
            cameraPresenter.viewDefaultPicture()
        }
    }

    companion object {
        val instance: CameraFragment
            get() = CameraFragment()
    }
}