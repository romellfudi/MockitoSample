package fudi.freddy.mokitosample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.romellfudi.permission.PermissionService;

import java.util.ArrayList;

import fudi.freddy.mokitosample.camera.CameraFragment;
import fudi.freddy.mokitosample.mvp_sevice.SecondAct;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            new PermissionService(this).request(permisos);
        }
    }

    private void views() {
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment, CameraFragment.getInstance())
                .commit();
        final AppCompatActivity compatActivity = this;
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(compatActivity, SecondAct.class));
            }
        });
    }

    PermissionService.Callback permisos = new PermissionService.Callback() {
        @Override
        public void onResponse(ArrayList<String> refusePermissions) {
            if (refusePermissions == null || refusePermissions.size() == 0)
                views();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permisos.handler(permissions, grantResults);
    }
}
