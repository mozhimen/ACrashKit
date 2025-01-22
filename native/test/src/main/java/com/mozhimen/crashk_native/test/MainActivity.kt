package com.mozhimen.crashk_native.test

import android.os.Bundle
import com.mozhimen.bindk.bases.viewbinding.activity.BaseActivityVB
import com.mozhimen.crashk_native.CrashKNativeMgr
import com.mozhimen.crashk_native.test.databinding.ActivityMainBinding
import com.mozhimen.kotlin.lintk.optins.OApiInit_InApplication
import com.mozhimen.kotlin.utilk.android.util.w

class MainActivity : BaseActivityVB<ActivityMainBinding>() {

    @OptIn(OApiInit_InApplication::class)
    override fun initView(savedInstanceState: Bundle?) {
        vb.mainBtnCrash.setOnClickListener {
            CrashKNativeMgr.instance.testNativeCrash()
        }

        vb.mainBtnCrashGet.setOnClickListener {
            CrashKNativeMgr.instance.getCrashFiles().map { it.name to it.lastModified() }.toList().joinToString { it.first + ":" + it.second }.w()
        }
    }
}