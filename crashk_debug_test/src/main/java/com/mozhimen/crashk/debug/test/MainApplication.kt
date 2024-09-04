package com.mozhimen.crashk.debug.test

import com.mozhimen.stackk.bases.BaseApplication
import com.mozhimen.kotlin.lintk.optin.OptInApiInit_InApplication
import com.mozhimen.kotlin.lintk.optin.OptInApiMultiDex_InApplication
import com.mozhimen.crashk.debug.CrashKDebugMgr

/**
 * @ClassName UnderlayKApplication
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/9/24 17:41
 * @Version 1.0
 */
@OptIn(OptInApiMultiDex_InApplication::class)
@OptInApiInit_InApplication
class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        //crashk
        CrashKDebugMgr.init()
    }
}