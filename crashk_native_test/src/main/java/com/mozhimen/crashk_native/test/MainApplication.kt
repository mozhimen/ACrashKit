package com.mozhimen.crashk_native.test

import android.app.Application
import com.mozhimen.crashk_native.CrashKNativeMgr
import com.mozhimen.kotlin.lintk.optins.OApiInit_InApplication
import com.mozhimen.kotlin.lintk.optins.permission.OPermission_READ_PHONE_STATE
import com.mozhimen.kotlin.lintk.optins.permission.OPermission_READ_PRIVILEGED_PHONE_STATE

/**
 * @ClassName MainApplication
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/10/28
 * @Version 1.0
 */
class MainApplication : Application() {
    @OptIn(OPermission_READ_PHONE_STATE::class, OPermission_READ_PRIVILEGED_PHONE_STATE::class, OApiInit_InApplication::class)
    override fun onCreate() {
        super.onCreate()

        CrashKNativeMgr.instance.init(null)
    }
}