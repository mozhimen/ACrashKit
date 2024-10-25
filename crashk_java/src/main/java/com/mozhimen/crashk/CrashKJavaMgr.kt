package com.mozhimen.crashk

import com.mozhimen.crashk.basic.commons.ICrashK
import com.mozhimen.crashk.commons.ICrashKJava
import com.mozhimen.kotlin.lintk.optins.OApiInit_InApplication

/**
 * @ClassName CrashJavaMgr
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/10/24
 * @Version 1.0
 */
@OApiInit_InApplication
class CrashKJavaMgr(private val _crashK: CrashKJavaProxy) : ICrashK by _crashK , ICrashKJava<CrashKJavaProxy> by _crashK {
    companion object {
        @JvmStatic
        val instance = INSTANCE.holder
    }

    //////////////////////////////////////////////////////////////

    private object INSTANCE {
        val holder = CrashKJavaMgr(CrashKJavaProxy())
    }
}