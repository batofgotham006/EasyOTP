package com.batofgotham.easyotp.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

fun getOtpFromMsg(message:String): String{

    val otpPattern: Pattern = Pattern.compile("(\\d{6})")
    val matcher: Matcher = otpPattern.matcher(message)

    if(matcher.find()){
        return matcher.group(0)!!
    }
    else
        return ""
}

fun hasOtp(message: String): Boolean{
    val otpPattern: Pattern = Pattern.compile("(|^)\\d{6}")
    val matcher: Matcher = otpPattern.matcher(message)

    return matcher.find()
}