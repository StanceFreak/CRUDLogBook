package com.example.y.Fragment.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.y.Fragment.Home.HomeRoomDatabase.BarangEntity
import com.example.y.R
import kotlinx.android.synthetic.main.recycler_item.view.*

class AdapterHome : RecyclerView.Adapter<AdapterHome.HomeViewHolder> (){

    private var dataList = emptyList<BarangEntity>()

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //pada saat user scroll kebawah, maka otomatis function ini akan menampilkan data yang ada dibawahnya
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false))
    }

    //menampilkan data yang ada pada data list ke tampilan item recycler
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.itemView.tv_title.text = currentItem.title
        holder.itemView.tv_date.text = currentItem.date.toString()
    }

    //mereturn item yang ada pada recyclerview
    override fun getItemCount(): Int {
        return dataList.size
    }
    fun setData(barang_table : List<BarangEntity>) {
        this.dataList = barang_table
        notifyDataSetChanged()
    }
}