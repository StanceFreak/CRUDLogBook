package com.example.y.Fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.y.Fragment.Base.BaseFragment
import com.example.y.LocalData.SharedPref
import com.example.y.R
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.edit_dialog.*
import kotlinx.android.synthetic.main.fragment_profile.*

class FragmentProfile : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference: SharedPref = SharedPref(requireContext())
        val namepref = sharedPreference.get_name("name_key")
        val emailpref = sharedPreference.get_signmail("sign_mail")
        val phonepref = sharedPreference.get_phone("phone_key")
        val addresspref = sharedPreference.get_address("address_key")

        tv_name.text = namepref
        tv_email.text = emailpref
        tv_phone.text = phonepref
        tv_address.text = addresspref

        btn_edit.setOnClickListener {
            showDialog()
        }
    }

    fun showDialog() {

        val mDialogView = Dialog(requireContext())
        mDialogView.setContentView(R.layout.edit_dialog)
        val save = mDialogView.findViewById<Button>(R.id.btn_save)
        val cancel = mDialogView.findViewById<Button>(R.id.btn_cancel)
        save.setOnClickListener {
            mDialogView.dismiss()

            val email = mDialogView.et_email.text.toString()
            val phone = mDialogView.et_phone.text.toString()
            val address = mDialogView.et_address.text.toString()
            val sharedPreference : SharedPref = SharedPref(requireContext())

            if(email.isEmpty() || email.isBlank()) {
                et_email.setError("Please enter your new Email!")
                return@setOnClickListener
            }
            if(phone.isEmpty() || phone.isBlank()) {
                mDialogView.et_phone.setError("Please enter your new Phone Number!")
                return@setOnClickListener
            }
            if(address.isEmpty() || address.isBlank()) {
                mDialogView.et_address.setError("Please enter your new Address!")
                return@setOnClickListener
            }
            if(phone.length < 9 || phone.length > 14) {
                mDialogView.et_phone.setError("Phone number must be 10 - 13 digits")
                return@setOnClickListener
            }
            if(!isEmailValid(email)) {
                mDialogView.et_email.setError("Please enter a valid Email!")
                return@setOnClickListener
            }
            if(!isPhoneValid(phone)) {
                mDialogView.et_phone.setError("Please enter a valid Phone Number!")
                return@setOnClickListener
            }
            if(address.isEmpty() || address.isBlank()) {
                mDialogView.et_address.setError("Please enter your Address!")
                return@setOnClickListener
            }
            else{
                sharedPreference.sign_email("sign_mail", email)
                sharedPreference.phone("phone_key", phone)
                sharedPreference.address("address_key", address)

                tv_email.text = email
                tv_phone.text = phone
                tv_address.text = address
            }

        }
        cancel.setOnClickListener {
            mDialogView.dismiss()
        }
        mDialogView.show()
    }
    fun isEmailValid(Email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()
    }
    fun isPhoneValid(Phone: String): Boolean{
        return android.util.Patterns.PHONE.matcher(Phone).matches()
    }

}