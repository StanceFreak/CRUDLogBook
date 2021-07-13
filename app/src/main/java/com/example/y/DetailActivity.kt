package com.example.y

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.y.Fragment.Home.HomeRoomDatabase.BarangEntity
import com.example.y.Fragment.Home.HomeRoomDatabase.BarangViewModel
import kotlinx.android.synthetic.main.detail_activity.*

class DetailActivity: AppCompatActivity() {

    lateinit var viewModel: BarangViewModel

    companion object {
        const val EXTRA_BARANG = "extra_barang"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val actionbar = supportActionBar

        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayShowHomeEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.ic_back)
        }
        viewModel = ViewModelProvider(this).get(BarangViewModel::class.java)

        val barang = intent.getParcelableExtra<BarangEntity>(EXTRA_BARANG)

        txt_title.text =    "Nama produk : ${barang?.title}"
        txt_quantity.text = "Jumlah             : ${barang?.quantity}"
        txt_supplier.text = "Dikirim oleh     : ${barang?.supplier}"
        txt_date.text =     "Diterima pada : ${barang?.date}"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}