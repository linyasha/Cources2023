package dev.lynko.cources2023

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.lynko.cources2023.databinding.FragmentAnimalsBinding

class FragmentAnimals : Fragment() {

    private lateinit var binding: FragmentAnimalsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimalsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingActionButton.setOnClickListener {

            val intent = Intent (context, FragmentAddAnimal::class.java)
            startActivity(intent)
        }

    }
    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentAnimals().apply {
                }
            }
    }
}