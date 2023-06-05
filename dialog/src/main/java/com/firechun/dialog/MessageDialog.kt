package com.firechun.dialog

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import com.firechun.dialog.base.BaseDialog
import org.w3c.dom.Text

/**
 * @ClassName MessageDialog
 * @Author panyd
 * @Date 2022/4/25
 * @Description 消息对话框
 */
class MessageDialog {

    class Builder constructor(context: Context) : CommonDialog.Builder<Builder>(context) {

        private val messageView: TextView? by lazy { findViewById(R.id.tv_message_message) }

        private var listener: OnListener? = null
        private val cancelUi: TextView?

        init {
            setCustomView(R.layout.message_dialog)
            cancelUi = findViewById(R.id.tv_ui_cancel)
        }

        fun setMessage(@StringRes id: Int): Builder = apply {
            setMessage(getString(id))
        }

        fun setMessage(text: CharSequence?): Builder = apply {
            messageView?.text = text
        }

        fun setListener(listener: OnListener?): Builder = apply {
            this.listener = listener
        }

        fun setCancelVisible(isVisible: Boolean): Builder = apply {
            cancelUi?.let { it.isVisible = isVisible }
        }

        override fun create(): BaseDialog {
            // 如果内容为空就抛出异常
            if (("" == messageView?.text.toString())) {
                throw IllegalArgumentException("Dialog message not null")
            }
            return super.create()
        }

        @SingleClick
        override fun onClick(view: View) {
            when (view.id) {
                R.id.tv_ui_confirm -> {
                    autoDismiss()
                    listener?.onConfirm(getDialog())
                }
                R.id.tv_ui_cancel -> {
                    autoDismiss()
                    listener?.onCancel(getDialog())
                }
            }
        }
    }

    fun interface OnListener {

        /**
         * 点击确定时回调
         */
        fun onConfirm(dialog: BaseDialog)

        /**
         * 点击取消时回调
         */
        fun onCancel(dialog: BaseDialog) {}
    }
}