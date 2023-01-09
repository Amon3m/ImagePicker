package com.m3.imagepicker

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.m3.islami2.base.BaseActivity

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isPermissionGranted()) {
            openGallery()

        } else {
            requestPermissionFromUser()


        }

    }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {


            } else {

                Toast.makeText(this,"Application needs the Permission",Toast.LENGTH_LONG).show()

            }
        }

    private fun requestPermissionFromUser() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_MEDIA_IMAGES))
        {
            showDialoge(message = "Application needs the Permission "
                , posActionName = "Ok",
                posAction = { dialog, which ->
                    requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                    dialog.dismiss()

                }, negActionName = "No", negAction = { dialog, _ ->
                    dialog.dismiss()
                }
            )
        }
        else{

            requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
        }
    }


    private fun openGallery() {
        TODO("Not yet implemented")
    }


    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED

    }


}
