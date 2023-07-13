package dev.lynko.cources2023.repository

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.lynko.cources2023.R
import dev.lynko.cources2023.databinding.ActivityMainBinding.inflate
import dev.lynko.cources2023.databinding.ItemAnimalBinding
import dev.lynko.cources2023.model.Animal

class AnimalAdapter : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {
 private var animalList: MutableList<Animal> = mutableListOf()
    inner class AnimalViewHolder(private val itemBinding: ItemAnimalBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(animal: Animal) {
              itemBinding.animalName.text = animal.name
              itemBinding.animalAge.text = animal.age.toString()
            itemBinding.animalBreed.text = animal.description.toString()
            itemBinding.animalWeight.text = animal.weight.toString()
                when(animal.type.toInt()){
                    1 -> itemBinding.typeIcon.setImageResource(R.drawable.cat)
                    2-> itemBinding.typeIcon.setImageResource(R.drawable.dog)
                    3-> itemBinding.typeIcon.setImageResource(R.drawable.hamster)
                    4-> itemBinding.typeIcon.setImageResource(R.drawable.fish)
                    5-> itemBinding.typeIcon.setImageResource(R.drawable.bird)
                    else -> itemBinding.typeIcon.setImageResource(R.drawable.dog)

                }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemBinding =ItemAnimalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AnimalViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animalList[position]
        holder.bind(animal)
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    fun setAnimalList(animals: List<Animal>) {
        animalList.clear()
        animalList.addAll(animals)
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateAnimals(newAnimals: MutableList<Animal>) {
        animalList = newAnimals
        notifyDataSetChanged()
    }

}
