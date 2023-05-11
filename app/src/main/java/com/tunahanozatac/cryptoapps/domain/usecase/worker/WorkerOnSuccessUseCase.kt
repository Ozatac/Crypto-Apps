package com.tunahanozatac.cryptoapps.domain.usecase.worker

import com.tunahanozatac.cryptoapps.domain.provider.WorkerProvider
import javax.inject.Inject

class WorkerOnSuccessUseCase @Inject constructor(
    private val workerProvider: WorkerProvider
) {
    operator fun invoke() = workerProvider.onSuccess()
}