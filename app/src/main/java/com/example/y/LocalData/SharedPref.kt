package com.example.y.LocalData

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context){

    private val SP = "UserData"

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(SP, Context.MODE_PRIVATE)

    fun name(name_key: String, name: String) {
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(name_key, name)
        editor.apply()
    }
    fun sign_mail(sign_mail: String, signmail: String) {
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(sign_mail, signmail)
        editor.apply()
    }
    fun phone(phone_key: String, phone: String) {
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(phone_key, phone)
        editor.apply()
    }
    fun address(address_key: String, address: String) {
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(address_key, address)
        editor.apply()
    }
    fun sign_pass(sign_pass: String, signpass: String) {
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(sign_pass, signpass)
        editor.apply()
    }
    fun log_email(log_email: String, logmail: String) {
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(log_email, logmail)
        editor.apply()
    }
    fun log_pass(log_pass: String, logpass: String) {
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(log_pass, logpass)
        editor.apply()
    }
    fun get_name(name_key: String): String?{
        return sharedPreferences.getString(name_key, null)
    }
    fun get_phone(phone_key: String): String?{
        return sharedPreferences.getString(phone_key, null)
    }
    fun get_address(address_key: String): String?{
        return sharedPreferences.getString(address_key, null)
    }
    fun get_signmail(sign_mail: String): String?{
        return sharedPreferences.getString(sign_mail, null)
    }
    fun get_signpass(sign_pass: String): String?{
        return sharedPreferences.getString(sign_pass, null)
    }
    fun get_logmail(log_email: String): String?{
        return sharedPreferences.getString(log_email, null)
    }
    fun get_logpass(log_pass: String): String?{
        return sharedPreferences.getString(log_pass, null)
    }
    fun remove_user(log_email: String, log_pass: String) {
        val editor : SharedPreferences.Editor = sharedPreferences.edit()

        editor.remove(log_email)
        editor.remove(log_pass)
        editor.apply()
    }
}