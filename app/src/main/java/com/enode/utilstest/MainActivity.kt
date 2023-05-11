package com.enode.utilstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enode.mylibrary.LogUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LogUtil.e("测试库")
    }
}