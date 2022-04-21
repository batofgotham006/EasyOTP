package com.batofgotham.easyotp

import android.Manifest.permission.READ_SMS
import android.Manifest.permission.RECEIVE_SMS
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.batofgotham.easyotp.message.MessageFragment
import com.vmadalin.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_EasyOTP)

        setContentView(R.layout.activity_main)
    }

}