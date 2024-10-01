package com.mozhimen.crashk.test

import android.view.View
import com.mozhimen.bindk.bases.activity.databinding.BaseActivityVDB
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.crashk.test.databinding.ActivityMainBinding

class MainActivity : BaseActivityVDB<ActivityMainBinding>() {

    fun goCrashK(view: View) {
        startContext<CrashKActivity>()
    }
}