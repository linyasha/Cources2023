package dev.lynko.cources2023

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.lynko.cources2023.databinding.ItemCountryBinding
import java.util.*

class MyRecyclerViewAdapter(
    private val delegate: ClickDelegate
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    private var countryList: MutableList<Country> = mutableListOf()

    inner class MyViewHolder(private val itemBinding: ItemCountryBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(country: Country) {
            itemBinding.name.text = country.name
            val drawable = when (country.icon) {
                0 -> R.drawable.c_1
                1 -> R.drawable.c_2
                2 -> R.drawable.c_3
                else -> R.drawable.c_4
            }
            itemBinding.avatar.setImageResource(drawable)
            itemBinding.parent.setOnClickListener {
                delegate.onCounryClick(country.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val country = countryList[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun setData(newList: List<Country>) {
        val diffC = DiffCallback(countryList, newList)
        val result = DiffUtil.calculateDiff(diffC)
        countryList.clear()
        countryList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}



class DiffCallback(private val oldList: List<Country>, private val newList: List<Country>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = oldList[newItemPosition]
        return oldItem == newItem

    }

}
