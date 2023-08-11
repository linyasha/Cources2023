package dev.lynko.cources2023.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.lynko.cources2023.*
import dev.lynko.cources2023.databinding.ActivityMainBinding
import dev.lynko.cources2023.ui.model.ValidateState

import dev.lynko.cources2023.ui.viewModel.AnimalsViewModel
import dev.lynko.domain.models.Animal
import kotlinx.coroutines.delay

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf


class AnimalsActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: AnimalsViewModel by viewModel {
        val accountId = intent.extras?.getInt("KEY_ACCOUNT_ID", 0)
        parametersOf(accountId)
    }

    var listener: MyForegroundBoundedService.MyBinder? = null

    private val connectionBounded = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            listener = p1 as MyForegroundBoundedService.MyBinder
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            listener = null
        }

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                12
            )
        }
//        lifecycleScope.launch {
//            viewModel.animals.collectLatest { animals ->
//                Log.d("HAHAH", "observe: $animals ")
//                binding.result.setText(animals.toString())
//            }
//        }
//        viewModel.state.observe(this) { status ->
//            with(binding) {
//                when(status) {
//                    ValidateState.SUCCESS -> {
//                        nameEditText.setText("")
//                        ageEditText.setText("")
//                        weightEditText.setText("")
//                    }
//                    ValidateState.FAIL -> {
//                        Toast.makeText(this@AnimalsActivity, "Check data!", Toast.LENGTH_SHORT).show()
//                    }
//                    ValidateState.DEFAULT -> {}
//                }
//            }
//        }
//        with(binding) {
//            add.setOnClickListener {
//                viewModel.insertAnimal(
//                    nameEditText.text.toString(),
//                    ageEditText.text.toString(),
//                    weightEditText.text.toString(),
//                    Animal.TYPE_CAT
//                )
//            }
//        }
        var count = 0
        binding.back.setOnClickListener {
            startService(
                Intent(this, MyService::class.java).apply {
                    putExtra("KEY", "$count")
                }
            )
            count++
        }
        binding.fore.setOnClickListener {
            val intent = Intent(this, MyForegroundService::class.java).apply {
                putExtra("KEY", "$count")
            }
//            startService(intent)
            ContextCompat.startForegroundService(this, intent)

        }
        binding.bound.setOnClickListener {
            val intent = Intent(this, MyForegroundBoundedService::class.java).apply {
                putExtra("KEY", "$count")
            }
            startService(intent)
            bindService(intent, connectionBounded, Context.BIND_AUTO_CREATE)
        }
        binding.boundStop.setOnClickListener {
            unbindService(connectionBounded)
        }
        binding.boundWork.setOnClickListener {
            listener?.startWork()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
//        unbindService(connectionBounded)
    }
}
