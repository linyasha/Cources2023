package dev.lynko.cources2023.repository

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.lynko.cources2023.AnimalsViewModel
import dev.lynko.cources2023.R
import dev.lynko.cources2023.databinding.FragmentAnimalsBinding
import dev.lynko.cources2023.model.Animal


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AnimalsFragment : Fragment() {

    private lateinit var binding: FragmentAnimalsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalsBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object {
        fun newInstance(param1: String, param2: String) = AnimalsFragment()
    }
}
