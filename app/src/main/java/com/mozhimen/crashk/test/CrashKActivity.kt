package com.mozhimen.crashk.test

import android.os.Bundle
import com.mozhimen.mvvmk.bases.activity.databinding.BaseActivityVDB
import com.mozhimen.crashk.test.databinding.ActivityCrashkBinding
import java.lang.RuntimeException

class CrashKActivity : BaseActivityVDB<ActivityCrashkBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        throw RuntimeException()
    }
}