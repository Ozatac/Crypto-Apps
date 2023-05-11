package com.tunahanozatac.cryptoapps.infrastructure.worker

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.tunahanozatac.cryptoapps.util.Constants
import com.tunahanozatac.cryptoapps.util.Constants.SYNC_DATA
import com.tunahanozatac.cryptoapps.domain.provider.WorkerProvider
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerProviderImpl @Inject constructor(context: Context) : WorkerProvider {

    private val workManager = WorkManager.getInstance(context)

    private val workConstraints =
        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true).build()

    override fun createWork() {

        val workRequest = PeriodicWorkRequestBuilder<CoinWorker>(
            15, TimeUnit.MINUTES, 15, TimeUnit.MINUTES
        ).setConstraints(workConstraints).setInitialDelay(15, TimeUnit.MINUTES).addTag(SYNC_DATA)
            .build()

        workManager.enqueueUniquePeriodicWork(
            Constants.SYNC_DATA_WORK_NAME, ExistingPeriodicWorkPolicy.REPLACE, workRequest
        )
    }

    override fun onSuccess() = workManager.getWorkInfosByTagLiveData(SYNC_DATA)
}