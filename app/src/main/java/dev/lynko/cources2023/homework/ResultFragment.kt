package dev.lynko.cources2023.homework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.lynko.cources2023.R
import dev.lynko.cources2023.databinding.FragmentResultBinding
import dev.lynko.cources2023.homework.intefraces.OnFragmentTransaction
import kotlin.random.Random


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private var listener: OnFragmentTransaction? = null

    private var result = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = requireActivity() as? OnFragmentTransaction
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments()?.let {
            val min = it.getInt(MIN, -1)
            val max = it.getInt(MAX, -1)
            if (min != -1 && max != -1) {
                result = calculate(min, max)
                binding.resultNumber.text = result.toString()
            }
        }

        binding.backButton.setOnClickListener {
            listener?.backToStartFragment(result)
        }
    }


    private fun calculate (min: Int, max: Int) = Random.nextInt(min, max)

    companion object {

        private const val MIN = "min"
        private const val MAX = "max"
        private const val RESULT = "result"

        @JvmStatic
        fun newInstance(min: Int, max: Int) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putInt(MIN, min)
                    putInt(MAX, max)
                }
            }
    }
}