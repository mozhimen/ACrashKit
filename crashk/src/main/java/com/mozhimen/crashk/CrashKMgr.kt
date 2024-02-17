package com.mozhimen.crashk

import android.util.Log
import com.mozhimen.basick.lintk.optins.OApiInit_InApplication
import com.mozhimen.basick.lintk.optins.permission.OPermission_READ_PHONE_STATE
import com.mozhimen.basick.lintk.optins.permission.OPermission_READ_PRIVILEGED_PHONE_STATE
import com.mozhimen.basick.utilk.bases.IUtilK
import com.mozhimen.crashk.commons.ICrashKListener
import com.mozhimen.crashk.commons.ICrashKMgr
import java.io.File

/**
 * @ClassName CrashKMgr
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Date 2022/3/28 14:32
 * @Version 1.0
 */
@OApiInit_InApplication
@OPermission_READ_PHONE_STATE
@OPermission_READ_PRIVILEGED_PHONE_STATE
class CrashKMgr : ICrashKMgr, IUtilK {

    companion object {
        @JvmStatic
        val instance = INSTANCE.holder
    }

    //////////////////////////////////////////////////////////////

    private val _crashKJava by lazy { CrashKJava() }
    private val _crashKNative by lazy { CrashKNative() }

    @JvmOverloads
    fun init(
        crashKJavaListener: ICrashKListener? = null,
        crashKNativeListener: ICrashKListener? = null,
        isRestart: Boolean = true
    ) {
        Log.d(TAG, "init: ")
        _crashKJava.setEnableRestart(isRestart).init(crashKJavaListener)
        _crashKNative.init(crashKNativeListener)
    }

    override fun getJavaCrashFiles(): Array<File> =
        _crashKJava.getCrashFiles()

    override fun getNativeCrashFiles(): Array<File> =
        _crashKNative.getCrashFiles()

    override fun getCrashFiles(): Array<File> =
        getJavaCrashFiles() + getNativeCrashFiles()

    //////////////////////////////////////////////////////////////

    private object INSTANCE {
        val holder = CrashKMgr()
    }
}