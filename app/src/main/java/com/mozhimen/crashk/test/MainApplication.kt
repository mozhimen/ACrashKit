package com.mozhimen.crashk.test

import com.mozhimen.basick.elemk.android.app.bases.BaseApplication
import com.mozhimen.basick.lintk.optin.OptInApiInit_InApplication
import com.mozhimen.basick.lintk.optin.OptInApiMultiDex_InApplication
import com.mozhimen.crashk.CrashKMgr
import com.mozhimen.crashk.commons.ICrashKListener

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
        CrashKMgr.instance.init(_crashKCallback)

    }

    private val _crashKCallback = object : ICrashKListener {

        override fun onGetCrashLog(msg: String) {
            //msg?.e(TAG) ?: "Ops! A crash happened, but i didn't get it messages".e()
        }
    }
}