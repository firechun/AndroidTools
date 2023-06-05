package com.firechun.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import android.widget.RelativeLayout

/**
 * 底部弹出窗口基类
 * @param context Context
 * @param rid 布局资源ID
 */
open class BottomPopupWindow(context: Context, rid: Int) : PopupWindow() {
    @SuppressLint("InflateParams")
    protected val view: View = LayoutInflater.from(context).inflate(rid, null)
    init {
//        buttonClose.setOnClickListener { dismiss() }
        height = RelativeLayout.LayoutParams.WRAP_CONTENT
        width = RelativeLayout.LayoutParams.MATCH_PARENT
        animationStyle = R.style.pop_window_style

        contentView = view
    }
}