package com.example.y

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.y.LocalData.SharedPref
import kotlinx.android.synthetic.main.app_bar.*

class LoginActivity : AppCompatActivity() {

    lateinit var btn_create: Button
    lateinit var form_email: EditText
    lateinit var form_pass: EditText
    lateinit var click_create: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)


        form_email = findViewById(R.id.login_email)
        form_pass = findViewById(R.id.login_pass)
        btn_create = findViewById(R.id.btn_log)
        click_create = findViewById(R.id.click_create)

        direct_create()
        login_condition()

    }
    fun direct_create() {
        click_create.setOnClickListener {
            val create = Intent(this, RegisterActivity::class.java)
            startActivity(create)
        }
    }
    fun login_condition() {
        btn_create.setOnClickListener{
            val sharedPreference: SharedPref = SharedPref(this)
            val email = form_email.editableText.toString()
            val pass = form_pass.editableText.toString()
            val emailpref = sharedPreference.get_signmail("sign_mail")
            val passpref = sharedPreference.get_signpass("sign_pass")

            if(email.isEmpty() || email.isBlank()) {
                form_email.setError("Please insert your Email!")
                return@setOnClickListener
            }
            else if(!isEmailValid(email)){
                form_email.setError("Please insert a valid Email!")
                return@setOnClickListener
            }
            else if(pass.isEmpty() || pass.isBlank()){
                form_pass.setError("Please insert your Password!")
                return@setOnClickListener
            }
            else if(!email.equals(emailpref) && !pass.equals(passpref)) {
                Toast.makeText(applicationContext, "Email and Password didn't match!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else {
                val sharedPreferences: SharedPref = SharedPref(this)
                sharedPreference.log_email("log_email", "logmail")
                sharedPreference.log_pass("log_pass", "logpass")
                val login = Intent(this, NaviActivity::class.java)
                startActivity(login)
                finish()
            }
        }
    }
    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences: SharedPref = SharedPref(this)
        val uMail = sharedPreferences.get_logmail("log_email")
        val uPass = sharedPreferences.get_logpass("log_pass")

        if(uMail != null && uPass != null) {
            val go = Intent(this, NaviActivity::class.java)
            startActivity(go)
            finish()
        }
    }
    //fun toolbarProperties(toolbarId: Toolbar, title: String) {
    //(this).run {
    //setSupportActionBar(toolbarId)
    //supportActionBar?.let {
    // toolbarId.title = title
    //toolbarId.setTitleTextColor(
    //resources.getColor(R.color.teal_200)
    // )
    //toolbarId.setBackgroundColor(resources.getColor(R.color.white))
    //}
    //}
    //}
}
