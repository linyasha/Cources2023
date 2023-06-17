package dev.lynko.cources2023

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import dev.lynko.cources2023.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = SQLiteOpenHelper(this)

        @Suppress("UNUSED_VARIABLE")
        val arrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            database.getListOfTopics()
        )
        val cursorAdapter = CursorAdapterExample(this@MainActivity, database.getCursorWithTopics())

        binding.listView.apply {
            adapter = cursorAdapter
        }
    }
}