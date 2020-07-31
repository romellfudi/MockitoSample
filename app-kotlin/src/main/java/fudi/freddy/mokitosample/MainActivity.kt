package fudi.freddy.mokitosample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.romellfudi.permission.PermissionService
import fudi.freddy.mokitosample.camera.CameraFragment
import fudi.freddy.mokitosample.mvp_sevice.SecondAct
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            PermissionService(this).request(permisos)
        }
    }

    private fun views() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment, CameraFragment.instance)
                .commit()
        btn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondAct::class.java))
        }
    }

    private var permisos: PermissionService.Callback = object : PermissionService.Callback() {
        override fun onResponse(refusePermissions: ArrayList<String>?) {
            views()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permisos.handler(permissions, grantResults)
    }
}