package com.firechun.dialog

import android.content.Context
import com.firechun.dialog.base.BaseDialog

class MyDialog {
    companion object {
        /**
         * 显示只有一个“确定”按钮的对话框
         * @param context Context
         * @param msgId 对话框文本资源ID
         * @param titleId 对话框标题资源ID，默认为"提示"
         * @param okCallback 按下“确定”时的回调，可以为null
         */
        fun showMessage(context: Context, msgId: Int, titleId: Int = R.string.common_title, okCallback: (() -> Unit)? = null) {
            showMessage(context, context.getString(msgId), context.getString(titleId), okCallback)
        }

        /**
         * 显示只有一个“确定”按钮的对话框
         * @param context Context
         * @param message 对话框文本
         * @param title 对话框标题，默认为"提示"
         * @param okCallback 按下“确定”时的回调，可以为null
         */
        fun showMessage(context: Context, message: String, title: String = "提示", okCallback: (() -> Unit)? = null) {
            MessageDialog.Builder(context)
                .setTitle(title)
                .setCancelVisible(false)
                .setMessage(message)
                .setCancel(null)
                .setListener { okCallback?.let { it() } }
                .show()
        }
        /**
         * 显示有“取消”和“确定”按钮的对话框
         * @param context Context
         * @param msgId 对话框文本资源ID
         * @param titleId 对话框标题资源ID，默认为"提示”
         * @param okCallback 按下“确定”时的回调，可以为null
         * @param cancelCallback 按下“取消”时的回调，可以为null
         */
        fun showConfirm(
            context: Context,
            msgId: Int,
            titleId: Int = R.string.common_title,
            okCallback: (() -> Unit)? = null,
            cancelCallback: (() -> Unit)? = null
        ) {
            showConfirm(context, context.getString(msgId), context.getString(titleId), okCallback, cancelCallback)
        }
        /**
         * 显示有“取消”和“确定”按钮的对话框
         * @param context Context
         * @param message 对话框文本
         * @param title 对话框标题，默认为"提示”
         * @param okCallback 按下“确定”时的回调，可以为null
         * @param cancelCallback 按下“取消”时的回调，可以为null
         */
        fun showConfirm(context: Context, message: String, title: String = "提示", okCallback: (() -> Unit)? = null, cancelCallback: (() -> Unit)? = null) {
            MessageDialog.Builder(context)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(message)
                .setListener(object : MessageDialog.OnListener {
                    override fun onConfirm(dialog: BaseDialog) {
                        okCallback?.let { it() }
                    }

                    override fun onCancel(dialog: BaseDialog) {
                        dialog.dismiss()
                        cancelCallback?.let { it() }
                    }
                })
                .show()
        }

        /**
         * 显示多行输入框
         * @param context Context
         * @param title 对话框标题
         * @param okCallback 按下“确定”时的回调，可以为null
         * @param cancelCallback 按下“取消”时的回调，可以为null
         */
        fun showMultiLineInputDialog(
            context: Context,
            title: String = "提示",
            default: String = "",
            okCallback: ((dialog: BaseDialog?, content: String) -> Unit)? = null,
            cancelCallback: (() -> Unit)? = null
        ) {
            InputDialog.Builder(context)
                .setCustomView(R.layout.multiline_input_dialog)
                .setTitle(title)
                .setDefault(default)
                .setAutoDismiss(false)
                .setListener(object : InputDialog.OnListener{
                    override fun onConfirm(dialog: BaseDialog, content: String) {
                        okCallback?.let { it(dialog, content) }
                    }

                    override fun onCancel(dialog: BaseDialog) {
                        dialog.dismiss()
                        cancelCallback?.let { it() }
                    }
                })
                .show()
        }
        /**
         * 显示单行行输入框
         * @param context Context
         * @param title 对话框标题
         * @param okCallback 按下“确定”时的回调，可以为null
         * @param cancelCallback 按下“取消”时的回调，可以为null
         */
        fun showSingleLineInputDialog(
            context: Context,
            title: String = "提示",
            default: String = "",
            maxLength: Int = 0,
            okCallback: ((dialog: BaseDialog, content: String) -> Unit)? = null,
            cancelCallback: (() -> Unit)? = null
        ) {
            InputDialog.Builder(context)
                .setCustomView(R.layout.singleline_input_dialog)
                .setTitle(title)
                .setDefault(default)
                .setMaxLength(maxLength)
                .setAutoDismiss(false)
                .setListener(object : InputDialog.OnListener{
                    override fun onConfirm(dialog: BaseDialog, content: String) {
                        okCallback?.let { it(dialog, content) }
                    }

                    override fun onCancel(dialog: BaseDialog) {
                        dialog.dismiss()
                        cancelCallback?.let { it() }
                    }
                })
                .show()
        }

    }
}