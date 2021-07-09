package com.example.y

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.y.LocalData.SharedPref
import kotlinx.android.synthetic.main.alert_register.view.*

class RegisterActivity : AppCompatActivity() {

    lateinit var user_name: EditText
    lateinit var user_email: EditText
    lateinit var user_pass: EditText
    lateinit var user_passConf: EditText
    lateinit var btn_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        user_name = findViewById(R.id.sign_name)
        user_email = findViewById(R.id.sign_email)
        user_pass = findViewById(R.id.sign_pass)
        user_passConf = findViewById(R.id.sign_passConfirm)
        btn_login = findViewById(R.id.btn_create)


        user_name.addTextChangedListener(signUpWatcher)
        user_email.addTextChangedListener(signUpWatcher)
        user_pass.addTextChangedListener(signUpWatcher)
        user_passConf.addTextChangedListener(signUpWatcher)

        register_condition()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun register_condition() {
        btn_login.setOnClickListener{
            val sharedPreferences: SharedPref = SharedPref(this)
            val name = user_name.text.toString()
            val email = user_email.text.toString()
            val pass = user_pass.text.toString()
            val passConf = user_passConf.text.toString()
            val emailPref = sharedPreferences.get_signmail("sign_mail")
            val passPref = sharedPreferences.get_signpass("sign_pass")

            if(name.isBlank() || name.isEmpty()) {
                user_name.setError("Please enter your Name!")
                return@setOnClickListener
            }
            if(email.isBlank() || email.isEmpty()) {
                user_email.setError("Please enter your Email!")
                return@setOnClickListener
            }
            if(pass.isBlank() || pass.isEmpty()) {
                user_pass.setError("Please enter your Password!")
                return@setOnClickListener
            }
            if(passConf.isBlank() || passConf.isEmpty()) {
                user_passConf.setError("Please confirm your Password!")
                return@setOnClickListener
            }
            if(!isEmailValid(email)) {
                user_email.setError("Please enter a valid Email!")
                return@setOnClickListener
            }
            if(pass.length < 8) {
                user_pass.setError("Password must be at least 8 characters")
                return@setOnClickListener
            }
            if(passConf != pass) {
                user_passConf.setError("Password didn't match")
                return@setOnClickListener
            }
            if(email.equals(emailPref) && pass.equals(passPref)) {
                Toast.makeText(applicationContext, "Account already exists", Toast.LENGTH_SHORT).show()
            }
            else{
                //showDialog()
                val sharedPreferences: SharedPref = SharedPref(this)
                sharedPreferences.name("name_key", name)
                sharedPreferences.sign_email("sign_mail", email)
                sharedPreferences.sign_pass("sign_pass", pass)

                val go = Intent(this, LoginActivity::class.java)
                startActivity(go)
                finish()
            }
        }
    }
    fun isEmailValid(Email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()
    }
    //fun showDialog() {
        //val name = user_name.text.toString()
        //val email = user_email.text.toString()
        //val pass = user_pass.text.toString()

        //val mDialogView = Dialog(this)
       // mDialogView.setCancelable(false)
        //mDialogView.setContentView(R.layout.alert_register)
        //val yes = mDialogView.findViewById<Button>(R.id.btn_yes)
        //val no  = mDialogView.findViewById<Button>(R.id.btn_no)
        //yes.setOnClickListener {
           // mDialogView.dismiss()
            //val sharedPreferences: SharedPref = SharedPref(this)
            //sharedPreferences.name("name_key", name)
           // sharedPreferences.sign_email("sign_mail", email)
            //sharedPreferences.sign_pass("sign_pass", pass)

            //val go = Intent(this, LoginActivity::class.java)
            //startActivity(go)
            //finish()

            //mDialogView.dismiss()
       // }
        //no.setOnClickListener {
            //mDialogView.dismiss()
        //}
        //mDialogView.show()
   // }
    private val signUpWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {

        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val input_name = user_name.text.toString().trim()
            val input_email = user_email.text.toString().trim()
            val input_pass = user_pass.text.toString().trim()
            val input_passConf = user_passConf.text.toString().trim()

            if(!input_name.isEmpty() && !input_email.isEmpty() && !input_pass.isEmpty() && !input_passConf.isEmpty()) {
                btn_login.isEnabled = true
            }
            else{
                btn_login.isEnabled = false
            }
        }
    }

}