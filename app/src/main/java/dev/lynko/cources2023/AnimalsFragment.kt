package dev.lynko.cources2023

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class AnimalsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    interface onAddAnimal {
        fun addAnimal()
    }

    lateinit var  listener: onAddAnimal

    var button: Button? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = requireActivity() as onAddAnimal
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animals, container, false).also {
            button = it.findViewById(R.id.button) as? Button
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button?.setOnClickListener {
            listener.addAnimal()
            (requireActivity() as? MainActivity)?.transactionToAddAnimal()
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AnimalsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}