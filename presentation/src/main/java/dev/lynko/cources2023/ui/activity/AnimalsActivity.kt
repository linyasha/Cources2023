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
import androidx.work.*
import dev.lynko.cources2023.*
import dev.lynko.cources2023.databinding.ActivityMainBinding
import dev.lynko.cources2023.ui.model.ValidateState

import dev.lynko.cources2023.ui.viewModel.AnimalsViewModel
import dev.lynko.cources2023.workManager.work.LoadWorker
import dev.lynko.cources2023.workManager.work.NotifyWorker
import dev.lynko.domain.models.Animal
import kotlinx.coroutines.delay

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit


class AnimalsActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding

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
//            startService(intent)
            bindService(intent, connectionBounded, Context.BIND_AUTO_CREATE)
        }
        binding.boundStop.setOnClickListener {
            unbindService(connectionBounded)
        }
        binding.boundWork.setOnClickListener {
            listener?.startWork()
        }

        binding.startWorkManager.setOnClickListener {
            // 1
//            val uploadRequest = OneTimeWorkRequest.from(LoadWorker::class.java)
//            val operation = WorkManager.getInstance(this)
//                .enqueue(uploadRequest)
//
//            Log.d("WorkManagerTest", "operation result: ${operation.result}")
//            operation.state.observe(this) {
//                Log.d("WorkManagerTest", "state liveData: ${it.toString()}")
//            }

            // 2
//            val uploadRequest = OneTimeWorkRequest.from(LoadWorker::class.java)
//            WorkManager.getInstance(this)
//                .enqueueUniqueWork(
//                    "uploadWork",
//                    ExistingWorkPolicy.APPEND,
//                    uploadRequest,
//                )

            // 3
//            val uploadRequest = OneTimeWorkRequest.from(LoadWorker::class.java)
//            val notifyRequest = OneTimeWorkRequest.from(NotifyWorker::class.java)
//            WorkManager.getInstance(this)
//                .beginUniqueWork(
//                    "uploadWork",
//                    ExistingWorkPolicy.REPLACE,
//                    uploadRequest,
//                )
//                .then(notifyRequest)
//                .enqueue()

            // 4
//            val uploadRequest = OneTimeWorkRequest.from(LoadWorker::class.java)
//            val notifyRequest = OneTimeWorkRequestBuilder<NotifyWorker>()
//                .setConstraints(
//                    Constraints.Builder()
//                        .setRequiredNetworkType(NetworkType.CONNECTED)
//                        .build()
//                )
//                .build()
//
//            WorkManager.getInstance(this)
//                .beginUniqueWork(
//                    "uploadWork",
//                    ExistingWorkPolicy.REPLACE,
//                    uploadRequest,
//                )
//                .then(notifyRequest)
//                .enqueue()

            // 5
//            val uploadRequest =
//                PeriodicWorkRequestBuilder<LoadWorker>(15, TimeUnit.MINUTES)
//                    .build()
//
//            WorkManager.getInstance(this)
//                .enqueueUniquePeriodicWork(
//                    "uploadWork",
//                    ExistingPeriodicWorkPolicy.KEEP,
//                    uploadRequest
//                )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        listener?.let {
            unbindService(connectionBounded)
        }
    }
}
