package com.enode.utilstest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.enode.utilstest.databinding.ActivityMainBinding
import com.firechun.dialog.MessageDialog
import com.firechun.dialog.MyDialog
import com.firechun.dialog.base.BaseDialog
import com.firechun.mylibrary.LogUtil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        LogUtil.e("测试库")

        binding.button.setOnClickListener {
//            MyDialog.showConfirm(this, "这是消息框")
            MyDialog.showSingleLineInputDialog(this, maxLength = 10, okCallback =  {_, content ->
                LogUtil.e(content)
            })
//            MyDialog.showMessage(this, message = "我是消息框")
//            MyDialog.showMultiLineInputDialog(this, default = "13590108551")
        }
    }
}