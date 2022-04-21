package com.batofgotham.easyotp.utils

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setOtp")
fun TextView.setOtp(message:String){
    val otp: String = getOtpFromMsg(message)
    Log.i("ABCD",otp)
    text = "OTP: $otp"
    if(otp=="")
        visibility = View.GONE
    else
        visibility = View.VISIBLE
}