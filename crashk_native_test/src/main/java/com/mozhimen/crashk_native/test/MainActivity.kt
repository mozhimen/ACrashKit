package com.mozhimen.crashk_native.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.mozhimen.crashk_native.CrashKNativeLib
import com.mozhimen.crashk_native.native.test.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CrashKNativeLib.init(this.cacheDir.absolutePath)
        findViewById<TextView>(R.id.main_btn_crash).setOnClickListener {
            CrashKNativeLib.testNativeCrash()
        }
    }
}