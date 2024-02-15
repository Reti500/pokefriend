package com.example.location

import android.content.Context
import java.util.concurrent.TimeUnit


/*class LocationWorker(
    private val context: Context,
    private val workerParams: WorkerParameters,
    private val locationManager: LocationManager
): Worker(context, workerParams){
    override fun doWork(): Result {
        val workerRequest = OneTimeWorkRequest
            .Builder(LocationWorker::class.java)
            .build()


        WorkManager.getInstance(context).enqueue(workerRequest)

        return Result.success()
    }
}*/