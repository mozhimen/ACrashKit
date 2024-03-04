package com.mozhimen.crashk.debug.test

import android.view.View
import com.mozhimen.basick.elemk.androidx.appcompat.bases.databinding.BaseActivityVDB
import com.mozhimen.basick.utilk.android.content.startContext
import com.mozhimen.crashk.debug.test.databinding.ActivityMainBinding

class MainActivity : BaseActivityVDB<ActivityMainBinding>() {

    fun goCrashK(view: View) {
        startContext<CrashKActivity>()
    }
}