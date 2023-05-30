package dev.lynko.cources2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import dev.lynko.cources2023.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), ClickDelegate {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = MyRecyclerViewAdapter(this)
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(this@SecondActivity)
            recyclerView.adapter = adapter
            recyclerView.setItemViewCacheSize(3)
        }
        adapter.setData(
            mutableListOf (
                Country("Belarus", 0, 1),
                Country("Russia", 1, 2),
                Country("USA", 2, 3),
                Country("Ukraine", 3, 4),
            )
        )

    }

    override fun onCounryClick(id: Int) {


    }

    companion object {
        private const val TAG = "secondActivity"
    }
}