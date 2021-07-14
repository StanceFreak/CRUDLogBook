package com.example.y

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.y.Fragment.Home.FragmentHome
import com.example.y.Fragment.Home.HomeRoomDatabase.BarangEntity
import com.example.y.Fragment.Home.HomeRoomDatabase.BarangViewModel
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.detail_edit_dialog.*
import java.util.*

class DetailActivity: AppCompatActivity() {

    lateinit var viewModel: BarangViewModel
    private var isUpdate: Boolean = true

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

        val edit = findViewById<Button>(R.id.btn_detail_edit)

        edit.setOnClickListener {
            val mDialogView = Dialog(this)
            mDialogView.setCancelable(false)
            mDialogView.setContentView(R.layout.detail_edit_dialog)

            mDialogView.et_detail_name.setText(barang?.title)
            mDialogView.et_detail_quantity.setText(barang?.quantity)
            mDialogView.et_detail_supplier.setText(barang?.supplier)
            mDialogView.et_detail_date.setText(barang?.date)

            val save = mDialogView.findViewById<Button>(R.id.btn_detail_save)
            val cancel = mDialogView.findViewById<Button>(R.id.btn_detail_cancel)

            val date = mDialogView.findViewById<EditText>(R.id.et_detail_date)
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val barang = intent.getParcelableExtra<BarangEntity>(EXTRA_BARANG)

            val formatDate = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(formatDate, Locale.US)
            date.setText(sdf.format(c.time))

            date.setOnClickListener {
                val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    val selectedMonth: Int = mMonth+1
                    date.setText("$mDay/$selectedMonth/$mYear")
                },  day, month, year)
                dpd.show()
            }

            save.setOnClickListener {
                mDialogView.dismiss()
                val id = barang!!.id
                val editName = mDialogView.et_detail_name.text.toString()
                val editQuantity = mDialogView.et_detail_quantity.text.toString()
                val editSupplier = mDialogView.et_detail_supplier.text.toString()
                val editDate = mDialogView.et_detail_date.text.toString()

                if(editName.isNotBlank() && editQuantity.isNotBlank() && editSupplier.isNotBlank() && editDate.isNotBlank()){
                    val ubahData = BarangEntity(id, editName, editSupplier, editQuantity, editDate)
                    viewModel.updateBarang(ubahData)
                    txt_title.text =    "Nama produk : $editName"
                    txt_quantity.text = "Jumlah             : $editQuantity"
                    txt_supplier.text = "Dikirim oleh     : $editSupplier"
                    txt_date.text =     "Diterima pada : $editDate"
                    Toast.makeText(this, "Edit barang sukses!", Toast.LENGTH_SHORT).show()
                }

                else {
                    Toast.makeText(this, "Edit barang gagal!", Toast.LENGTH_SHORT).show()
                }
            }
            cancel.setOnClickListener {
                mDialogView.dismiss()
            }
            mDialogView.show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val barang = intent.getParcelableExtra<BarangEntity>(EXTRA_BARANG)
        val id = item.itemId

        if (id == R.id.detail_delete) {
            val mDialogView = Dialog(this)
            mDialogView.setCancelable(false)
            mDialogView.setContentView(R.layout.alert_dialog)

            val yes = mDialogView.findViewById<Button>(R.id.btn_yes)
            val no = mDialogView.findViewById<Button>(R.id.btn_no)

            yes.setOnClickListener {
                mDialogView.dismiss()
                if (barang != null) {
                    viewModel.deleteDataBarang(barang.id)
                }
                val back = Intent(this, FragmentHome::class.java)
                startActivity(back)
                finish()
            }
            no.setOnClickListener {
                mDialogView.dismiss()
            }
            mDialogView.show()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}