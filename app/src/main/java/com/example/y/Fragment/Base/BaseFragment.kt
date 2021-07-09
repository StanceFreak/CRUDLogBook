package com.example.y.Fragment.Base

import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.y.R

abstract class BaseFragment: Fragment() {

    fun toolbarProperties(toolbarId: androidx.appcompat.widget.Toolbar, title: String) {
        (activity as AppCompatActivity).run {
            setSupportActionBar(toolbarId)
            supportActionBar?.let {
                toolbarId.title = title
                toolbarId.setTitleTextColor(
                        resources.getColor(R.color.white)
                )
            }
        }
    }

}