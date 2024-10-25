package com.mozhimen.crashk_native

import com.mozhimen.crashk.basic.commons.ICrashK
import com.mozhimen.kotlin.lintk.optins.OApiInit_InApplication
import com.mozhimen.kotlin.lintk.optins.permission.OPermission_READ_PHONE_STATE
import com.mozhimen.kotlin.lintk.optins.permission.OPermission_READ_PRIVILEGED_PHONE_STATE
/**
 * @ClassName CrashKMgr
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Version 1.0
 */
@OApiInit_InApplication
@OPermission_READ_PHONE_STATE
@OPermission_READ_PRIVILEGED_PHONE_STATE
class CrashKNativeMgr : ICrashK by CrashKNativeProxy() {
    companion object {
        @JvmStatic
        val instance = INSTANCE.holder
    }

    //////////////////////////////////////////////////////////////

    private object INSTANCE {
        val holder = CrashKNativeMgr()
    }
}