package dev.lynko.cources2023.workManager

import kotlinx.coroutines.delay

class FileUploader {

    interface ProgressCallback {
        fun onProgress(progress: Int)
        fun onComplete()
        fun onFail()
    }

    private var isWorking = false

    suspend fun upload(progressCallback: ProgressCallback) {
        isWorking = true
        repeat(100) {
            if (!isWorking) {
                progressCallback.onFail()
                return
            }

            delay(100L)
            progressCallback.onProgress(it)
        }
        progressCallback.onComplete()
    }

    fun cancel() {
        isWorking = false
    }
}