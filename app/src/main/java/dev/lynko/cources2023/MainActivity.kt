package dev.lynko.cources2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.lynko.cources2023.databinding.ActivityMainBinding
import javax.xml.datatype.DatatypeFactory.newInstance
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(binding.containerMain.id, FragmentAnimals.newInstance(), "tag")
            .commit()
    }
}