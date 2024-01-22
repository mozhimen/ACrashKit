package com.mozhimen.crashk

import android.util.Log
import com.mozhimen.basick.lintk.optin.OptInApiInit_InApplication
import com.mozhimen.basick.manifestk.cons.CPermission
import com.mozhimen.basick.manifestk.annors.AManifestKRequire
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
@OptInApiInit_InApplication
@AManifestKRequire(CPermission.READ_PHONE_STATE, CPermission.READ_PRIVILEGED_PHONE_STATE)
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