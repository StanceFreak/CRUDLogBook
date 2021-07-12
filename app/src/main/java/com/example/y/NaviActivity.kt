package com.example.y

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.DrawableRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.y.Fragment.FragmentData
import com.example.y.Fragment.Home.FragmentHome
import com.example.y.Fragment.FragmentProfile
import com.example.y.LocalData.SharedPref
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.navi_activity.*

class NaviActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    private var mToolBarNavigationListenerIsRegistered: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navi_activity)

        drawerLayout = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        nav_navi.setNavigationItemSelectedListener(this)

        //berfungsi agar pada saat drawer dalam posisi terbuka dan user menekan tombol back, maka drawer akan tertutup
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        displayMenuFrag(-1)
        headerInfo()
    }

    //berfungsi untuk menampilkan fragment pada saat menu ditekan
    fun displayMenuFrag(id: Int) {
        val fragment =  when(id) {
            //mengarahkan ke fragment profil saat user menekan menu profile
            R.id.menu_home -> {
                FragmentHome()
            }
            R.id.menu_profile -> {
                FragmentProfile()
            }
            //mengarahkan ke fragment data barang saat user menekan menu data
            R.id.menu_data -> {
                FragmentData()
            }
            //jika user tidak menekan kedua menu diatas, maka aplikasi akan menampilkan home fragment sebagai posisi awal
            else -> {
                FragmentHome()
            }
        }

        //berfungsi untuk mengganti teks pada textview yang ada pada toolbar pada saat menu ditekan
        if (id == R.id.menu_home) {
            toolbar_title.text = "Home"
        }
        if (id == R.id.menu_profile) {
            toolbar_title.text = "Profile"
        }
        if (id == R.id.menu_data) {
            toolbar_title.text = "Data Barang"
        }
        else {
            toolbar_title.text = "Home"
        }

        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).addToBackStack(null).commit()

        //mengeksekusi function logout pada saat menu logout ditekan
        when(id) {
            R.id.menu_logout -> {
                logOut()
            }
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        displayMenuFrag(menuItem.itemId)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // function yang berfungsi untuk mengganti nama dan email pada header sesuai dengan data pada saat user membuat akun
    fun headerInfo() {
        val navigationView = nav_navi
        val headerView: View = navigationView.getHeaderView(0)
        val sharedPreferences: SharedPref = SharedPref(this)
        val namePref  = sharedPreferences.get_name("name_key")
        val emailPref = sharedPreferences.get_signmail("sign_mail")
        headerView.findViewById<TextView>(R.id.head_name).text = namePref
        headerView.findViewById<TextView>(R.id.head_email).text = emailPref
    }
    
    fun logOut() {
        val sharedPreferences: SharedPref = SharedPref(this)
        sharedPreferences.remove_user("log_email")
        sharedPreferences.remove_user1("log_pass")
        val out = Intent(this, LoginActivity::class.java)
        startActivity(out)
        finish()
    }


}