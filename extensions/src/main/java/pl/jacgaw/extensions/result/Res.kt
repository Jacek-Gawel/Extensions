package pl.jacgaw.extensions.result

import androidx.annotation.Keep

interface Error
typealias RootError = Error

@Keep
sealed interface Res<out D, out E : RootError> {
    data class Success<out D, out E : RootError>(val data: D) : Res<D, E>

    data class Error<out D, out E : RootError>(val error: E) : Res<D, E>

    fun getOrDefault(default: @UnsafeVariance D): D =
        when (this) {
            is Success -> data
            is Error -> default
        }

    fun getOrNull(): D? =
        when (this) {
            is Success -> data
            is Error -> null
        }
}
