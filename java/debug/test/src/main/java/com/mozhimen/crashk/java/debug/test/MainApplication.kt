package com.mozhimen.crashk.java.debug.test

import com.mozhimen.stackk.bases.BaseApplication
import com.mozhimen.crashk.java.debug.CrashKJavaDebugMgr
import com.mozhimen.kotlin.lintk.optins.OApiMultiDex_InApplication

/**
 * @ClassName UnderlayKApplication
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/9/24 17:41
 * @Version 1.0
 */
@OptIn(OApiMultiDex_InApplication::class)
class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        //crashk
        CrashKJavaDebugMgr.init()
    }
}