package dev.lynko.cources2023.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import dev.lynko.cources2023.R
import dev.lynko.cources2023.databinding.ActivityMainHomeworkBinding
import dev.lynko.cources2023.homework.intefraces.OnFragmentTransaction

class MainActivityHomework : AppCompatActivity(), OnFragmentTransaction {

    private lateinit var binding: ActivityMainHomeworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainHomeworkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.container.id
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, StartFragment.newInstance(), TAG)
            .commit()

    }

    override fun onTransactionToResultFragment(min: Int, max: Int) {
        with(supportFragmentManager){
            popBackStack()
            beginTransaction()
                .add(binding.container.id, ResultFragment.newInstance(min,max))
                .commit()
        }
    }

    override fun backToStartFragment(result: Int) {
        with(supportFragmentManager) {
            popBackStack()
            beginTransaction()
                .add(binding.container.id, StartFragment.newInstance(result))
                .commit()
        }
    }

    companion object {
        const val TAG = "150"
    }

}
