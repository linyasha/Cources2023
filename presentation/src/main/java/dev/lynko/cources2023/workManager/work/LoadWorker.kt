package dev.lynko.cources2023.workManager.work

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.lynko.cources2023.workManager.FileUploader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class LoadWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    companion object {
        const val TAG = "WorkManagerTest"
    }

    private val workerScope = CoroutineScope(Dispatchers.IO)

    private val fileUploader = FileUploader()

    override suspend fun doWork(): Result {
        Log.d(TAG, "IT-Academy: UploadWorker doWork")
        var result: Result = Result.retry()
        workerScope.async {
            fileUploader.upload(object : FileUploader.ProgressCallback {
                override fun onProgress(progress: Int) {
                    if (isStopped) {
                        fileUploader.cancel()
                    } else {
                        Log.d(TAG, "IT-Academy: UploadWorker progress $progress%")
                    }
                }

                override fun onComplete() {
                    Log.d(TAG, "IT-Academy: UploadWorker complete")
                    result = Result.success()
                }

                override fun onFail() {
                    Log.d(TAG, "IT-Academy: UploadWorker failed")
                    result = Result.failure()
                }
            })
        }.await()
        Log.d(TAG, "IT-Academy: UploadWorker doWork finished $result")
        return result
    }
}