package com.firechun.dialog

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.firechun.dialog.base.BaseDialog

/**
 * @ClassName InputDialog
 * @Author panyd
 * @Date 2022/6/1
 * @Description 输入对话框
 */
class InputDialog {

    class Builder(context: Context) : CommonDialog.Builder<Builder>(context),
        BaseDialog.OnShowListener, OnEditorActionListener {

//        private val inputView: RegexEditText? by lazy { findViewById(R.id.tv_input_message) }
//        private val inputView: EditText? by lazy { findViewById(R.id.tv_input_message) }
        private var inputView: EditText? = null
        private var listener: OnListener? = null

        init {
//            setCustomView(R.layout.input_dialog)
            inputView?.setOnEditorActionListener(this)
            addOnShowListener(this)
        }

        override fun setCustomView(@LayoutRes id: Int): Builder {
            val b = setCustomView(LayoutInflater.from(getContext()).inflate(id, containerLayout, false))
            inputView = findViewById(R.id.tv_input_message)
            return b
        }

        fun setHint(@StringRes id: Int): Builder = apply {
            setHint(getString(id))
        }

        fun setHint(text: CharSequence?): Builder = apply {
            inputView?.hint = text
        }

        fun setContent(@StringRes id: Int): Builder = apply {
            setContent(getString(id))
        }

        fun setContent(text: CharSequence?): Builder = apply {
            inputView?.setText(text)
            val editable = inputView?.text ?: return@apply
            val index = editable.length
            if (index <= 0) {
                return@apply
            }
            inputView?.requestFocus()
            inputView?.setSelection(index)
        }

        fun setInputRegex(regex: String?): Builder = apply {
//            inputView?.setInputRegex(regex)
        }

        fun setListener(listener: OnListener?): Builder = apply {
            this.listener = listener
        }

        /**
         * [BaseDialog.OnShowListener]
         */
        override fun onShow(dialog: BaseDialog?) {
            postDelayed({ showKeyboard(inputView) }, 500)
        }

        @SingleClick
        override fun onClick(view: View) {
            when (view.id) {
                R.id.tv_ui_confirm -> {
                    autoDismiss()
                    listener?.onConfirm(getDialog(), inputView?.text?.toString() ?: "")
                }
                R.id.tv_ui_cancel -> {
                    autoDismiss()
                    listener?.onCancel(getDialog())
                }
            }
        }

        /**
         * [TextView.OnEditorActionListener]
         */
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                findViewById<View>(R.id.tv_ui_confirm)?.let {
                    // 模拟点击确认按钮
                    onClick(it)
                }
                return true
            }
            return false
        }
    }

    interface OnListener {

        /**
         * 点击确定时回调
         */
        fun onConfirm(dialog: BaseDialog?, content: String)

        /**
         * 点击取消时回调
         */
        fun onCancel(dialog: BaseDialog?) {}
    }
}