package dev.lynko.cources2023

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dev.lynko.cources2023.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var callButton: Button
//        callButton = findViewById(R.id.button)

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.button.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                TODO("Not yet implemented")
//            }
//
//        })
        binding.button.setOnClickListener {
            if (isCallPermissionGranted()) {
                call911()
            } else {
                requestCallPermission()
            }
        }

    }

    private fun call911() {
        val callUri = Uri.parse("tel://911")
        val callIntent = Intent(Intent.ACTION_CALL, callUri)
        startActivity(callIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CALL_CODE) {
            if (permissions.size == 1) {
                if (grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    call911()
                } else {
                    Toast.makeText(this, "Sorry :(", Toast.LENGTH_LONG).show()
                }
            }
        } else {
            //TODO("Other code's)
        }
    }

    private fun requestCallPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), REQUEST_PERMISSION_CALL_CODE)
    }

    private fun isCallPermissionGranted(): Boolean =
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED

    companion object {

        private const val REQUEST_PERMISSION_CALL_CODE = 135

    }

}