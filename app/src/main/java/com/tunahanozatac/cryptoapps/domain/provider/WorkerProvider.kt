package com.tunahanozatac.cryptoapps.domain.provider

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo

interface WorkerProvider {

    fun createWork()

    fun onSuccess(): LiveData<List<WorkInfo>>
}