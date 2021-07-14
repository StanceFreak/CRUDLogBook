package com.example.y.Fragment.Home

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.y.Fragment.Home.HomeRoomDatabase.BarangEntity
import com.example.y.Fragment.Home.HomeRoomDatabase.BarangViewModel
import com.example.y.R
import kotlinx.android.synthetic.main.add_dialog.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class FragmentHome : Fragment() {

    lateinit var viewModel: BarangViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BarangViewModel::class.java)

        setupRecyclerView()
        addBtn()
    }

    private fun setupRecyclerView() {
        val homeAdapter = AdapterHome()
        with(rv_home) {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.getDataBarang.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            homeAdapter.setData(it)
        })
    }

    fun addBtn() {
        btn_add.setOnClickListener {
            val mDialogView = Dialog(requireContext())
            mDialogView.setCancelable(false)
            mDialogView.setContentView(R.layout.add_dialog)

            val save = mDialogView.findViewById<Button>(R.id.btn_saveAdd)
            val cancel = mDialogView.findViewById<Button>(R.id.btn_cancelAdd)

            val date = mDialogView.findViewById<EditText>(R.id.et_date)


            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val formatDate = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(formatDate, Locale.US)
            date.setText(sdf.format(c.time))

            date.setOnClickListener {
                val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    val selectedMonth: Int = mMonth + 1
                    date.setText("$mDay/$selectedMonth/$mYear")
                }, day, month, year)
                dpd.show()
            }

            save.setOnClickListener {
                mDialogView.dismiss()
                val svProduct = mDialogView.et_product.editableText.toString()
                val svQuantity = mDialogView.et_quantity.editableText.toString()
                val svSupplier = mDialogView.et_supplier.editableText.toString()
                val svDate = mDialogView.et_date.editableText.toString()

                if(svProduct.isNotBlank() && svQuantity.isNotBlank() && svSupplier.isNotBlank() && svDate.isNotBlank()){
                    val barangMasuk = BarangEntity(0, svProduct, svSupplier, svQuantity, svDate)
                    viewModel.insertData(barangMasuk)
                    Toast.makeText(requireContext(), "Upload Berhasil", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(requireContext(), "Upload Gagal", Toast.LENGTH_SHORT).show()
                }
            }
            cancel.setOnClickListener {
                mDialogView.dismiss()
            }
            mDialogView.show()
        }
    }

}