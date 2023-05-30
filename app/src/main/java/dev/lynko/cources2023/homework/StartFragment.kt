package dev.lynko.cources2023.homework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import dev.lynko.cources2023.R
import dev.lynko.cources2023.databinding.FragmentStartBinding
import dev.lynko.cources2023.homework.intefraces.OnFragmentTransaction


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    private var listener: OnFragmentTransaction? = null

    private var min: Int? = null
    private var max: Int? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = requireActivity() as? OnFragmentTransaction

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireArguments().let {
            val result = it.getInt(RESULT, -1)
            if (result != -1) binding.previousResalt.text = result.toString()
         }
        binding.minValueEditText.addTextChangedListener {

        }

        binding.buttonGenerate.setOnClickListener {
            val min = 1
            val max = 5


            listener?.onTransactionToResultFragment(min, max)
        }


    }

    companion object {

        private const val RESULT = "result"

        @JvmStatic
        fun newInstance(result: Int? = null) =
            StartFragment().apply {
                result?.let {
                    arguments = bundleOf(RESULT to it)
                }
            }
    }
}