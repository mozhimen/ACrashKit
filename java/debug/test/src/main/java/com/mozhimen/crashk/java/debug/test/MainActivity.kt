package com.mozhimen.crashk.java.debug.test

import android.view.View
import com.mozhimen.uik.databinding.bases.viewdatabinding.activity.BaseActivityVDB
import com.mozhimen.kotlin.utilk.android.content.startContext
import com.mozhimen.crashk.java.debug.test.databinding.ActivityMainBinding

class MainActivity : BaseActivityVDB<ActivityMainBinding>() {

    fun goCrashK(view: View) {
        startContext<CrashKActivity>()
    }
}