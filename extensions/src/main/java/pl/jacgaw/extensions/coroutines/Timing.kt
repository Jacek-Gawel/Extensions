package pl.jacgaw.extensions.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.isActive
import kotlin.time.Duration

@OptIn(ExperimentalCoroutinesApi::class)
fun <T> Flow<T?>.emitLatestEvery(period: Long): Flow<T?> =
    transformLatest { value ->
        while (currentCoroutineContext().isActive) {
            emit(value)
            delay(period)
        }
    }

fun <T> Flow<T?>.emitLatestEvery(period: Duration): Flow<T?> =
    emitLatestEvery(period.inWholeMilliseconds)

fun <T> flowInterval(interval: Duration, task: suspend () -> T): Flow<T> {
    return flow {
        while (currentCoroutineContext().isActive) {
            emit(task())
            delay(interval)
        }
    }
}