package dev.lynko.cources2023

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.lynko.cources2023.databinding.FragmentAnimalsBinding
import dev.lynko.cources2023.repository.AnimalsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentAnimals : Fragment(), ClickDelegate {

    private lateinit var binding: FragmentAnimalsBinding
    private lateinit var animalsRepository: AnimalsRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimalsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animalsRepository = AnimalsRepository(MyAnimalsApp.INSTANCE.database.animalsDao())
        val adapter = MyAdapter(this)

        with(binding) {
            floatingActionButton.setOnClickListener {
                (requireActivity() as? MainActivity)?.changeFragment(FragmentAddAnimal.newInstance())
                    ?:throwToast("failed to find context")
            }


            RecyclerView.layoutManager = LinearLayoutManager(this@FragmentAnimals.context)
            RecyclerView.adapter = adapter
            RecyclerView.setItemViewCacheSize(3)

            lifecycleScope.launch(Dispatchers.IO) {
                val animals = animalsRepository.getAllAnimas()
                withContext(Dispatchers.Main) {
                    adapter.setData(animals)
                }
            }
        }
    }

    override fun onAnimalClick(id: Int) {
    }

    private fun throwToast(message: String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

    companion object{

        const val TAG = "152"
        @JvmStatic
        fun newInstance() =
            FragmentAnimals().apply{
                arguments = Bundle().apply {  }
            }

    }
}