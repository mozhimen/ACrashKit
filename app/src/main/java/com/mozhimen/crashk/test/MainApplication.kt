package com.mozhimen.crashk.test

import com.mozhimen.crashk.CrashKJavaMgr
import com.mozhimen.crashk.basic.commons.ICrashKListener
import com.mozhimen.kotlin.lintk.optins.OApiInit_InApplication
import com.mozhimen.stackk.bases.BaseApplication
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
    @OptIn(OApiInit_InApplication::class)
    override fun onCreate() {
        super.onCreate()

        //crashk
        CrashKJavaMgr.instance.init(_crashKCallback)

    }

    private val _crashKCallback = object : ICrashKListener {
        override fun onGetCrashLog(msg: String) {
            //msg?.e(TAG) ?: "Ops! A crash happened, but i didn't get it messages".e()
        }
    }
}