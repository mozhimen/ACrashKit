package com.mozhimen.crashk.debug.test

import android.os.Bundle
import com.mozhimen.bindk.bases.activity.databinding.BaseActivityVDB
import com.mozhimen.crashk.debug.test.databinding.ActivityCrashkBinding
import java.lang.RuntimeException

class CrashKActivity : BaseActivityVDB<ActivityCrashkBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        throw RuntimeException()
    }
}