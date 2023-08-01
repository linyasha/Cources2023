package dev.lynko.cources2023

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.lynko.cources2023.databinding.ItemAnimalBinding
import dev.lynko.cources2023.model.Animal
import java.util.*

class MyAdapter(
    private val delegate: ClickDelegate
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var animalList: MutableList<Animal> = mutableListOf()

    inner class MyViewHolder(private val itemBinding: ItemAnimalBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(animal: Animal) {
            itemBinding.name.text = animal.name
            val drawable = when (animal.type) {
                1.toByte() -> R.drawable.cat
                2.toByte() -> R.drawable.dog
                else -> R.drawable.android
            }
            itemBinding.avatar.setImageResource(drawable)
            itemBinding.parent.setOnClickListener {
                delegate.onAnimalClick(animal.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val animal = animalList[position]
        holder.bind(animal)
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    fun setData(newList: List<Animal>) {
        val diffC = DiffCallback(animalList, newList)
        val result = DiffUtil.calculateDiff(diffC)
        animalList.clear()
        animalList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}



class DiffCallback(private val oldList: List<Animal>, private val newList: List<Animal>) : DiffUtil.Callback() {

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