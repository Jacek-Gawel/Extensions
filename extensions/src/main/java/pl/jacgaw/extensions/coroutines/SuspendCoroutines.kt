package pl.jacgaw.extensions.coroutines

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine


suspend inline fun <T> suspendCoroutineWithTimeout(
    timeout: Long,
    crossinline block: (Continuation<T>) -> Unit,
): T? {
    return withTimeoutOrNull(timeout) {
        suspendCoroutine(block = block)
    }
}

suspend inline fun <T> suspendCancellableCoroutineWithTimeout(
    timeout: Long,
    crossinline block: (CancellableContinuation<T>) -> Unit,
): T? {
    return withTimeoutOrNull(timeout) {
        suspendCancellableCoroutine(block = block)
    }
}