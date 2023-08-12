package dev.lynko.cources2023.workManager.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import dev.lynko.cources2023.workManager.work.LoadWorker.Companion.TAG
import kotlinx.coroutines.*

class NotifyWorker(
    context: Context,
    workerParams: WorkerParameters,
) : Worker(context, workerParams) {

    val workerScope = CoroutineScope(Job() + Dispatchers.IO)

    override fun doWork(): Result {
        Log.d(TAG, "IT-Academy: NotifyWorker doWork")
        workerScope.launch {

        }
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        workerScope.cancel()
    }
}