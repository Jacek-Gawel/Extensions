package pl.jacgaw.extensions.text

import android.content.Context
import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Keep
sealed interface UiText {
    data class DynamicString(val value: String) : UiText
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any,
    ) : UiText

    fun asString(context: Context): String {
        return when (this@UiText) {
            is DynamicString -> value
            is StringResource -> context.resources.getString(resId, *args)
        }
    }
}

@Composable
fun UiText.asString(): String {
    return when (this) {
        is UiText.DynamicString -> value
        is UiText.StringResource -> stringResource(resId, *args)
    }
}