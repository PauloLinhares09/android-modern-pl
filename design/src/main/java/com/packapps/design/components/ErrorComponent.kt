package com.packapps.design.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.packapps.design.R
import java.io.IOException
import java.util.*

class ErrorComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var onTryAgainClicked: (() -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.component_error, this, true)
    }

    fun setErrorType(errorType: ErrorType) {
        val titleView: TextView = findViewById(R.id.textView)
        val messageView: TextView = findViewById(R.id.textView2)
        titleView.setText(errorType.titleResId)
        messageView.setText(errorType.messageResId)
    }

    fun setOnTryAgainClickListener(listener: () -> Unit) {
        onTryAgainClicked = listener
        findViewById<Button>(R.id.btnTryAgain).setOnClickListener { onTryAgainClicked?.invoke() }
    }

    enum class ErrorType(val titleResId: Int, val messageResId: Int) {
        NETWORK_ERROR(R.string.network_error_title, R.string.network_error_message),
        LIST_EMPTY(R.string.list_empty_title, R.string.list_empty_message),
        UNKNOWN_ERROR(R.string.unknown_error_title, R.string.unknown_error_message);
    }

}

fun Throwable.toErrorComponent(): ErrorComponent.ErrorType {
    return when (this) {
        is IOException -> ErrorComponent.ErrorType.NETWORK_ERROR
        is EmptyStackException -> ErrorComponent.ErrorType.LIST_EMPTY
        else -> ErrorComponent.ErrorType.UNKNOWN_ERROR
    }
}

