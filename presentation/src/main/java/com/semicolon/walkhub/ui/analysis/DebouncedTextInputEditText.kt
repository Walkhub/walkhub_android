package com.semicolon.walkhub.ui.analysis

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import com.google.android.material.textfield.TextInputEditText
import com.semicolon.walkhub.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

class DebouncedTextInputEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int = R.attr.editTextStyle
) : TextInputEditText(context, attributeSet, defStyleAttr) {

    private val delay = 300L
    private var job: Job? = null

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun setOnDebounceTextWatcher(lifecycle:Lifecycle, onDebounceAction: (String) -> Unit) {
        job?.cancel()
        job = onDebounceTextChanged()
            .debounce(delay)
            .onEach { onDebounceAction(it) }
            .launchIn(lifecycle.coroutineScope)
    }

    fun removeOnDebounceTextWatcher() {
        job?.cancel()
    }

    @ExperimentalCoroutinesApi
    fun onDebounceTextChanged(): Flow<String> = channelFlow {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                trySend(s.toString())
            }
        }

        addTextChangedListener(textWatcher)

        awaitClose {
            removeTextChangedListener(textWatcher)
        }
    }
}
