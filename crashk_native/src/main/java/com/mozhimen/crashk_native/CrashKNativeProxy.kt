package com.mozhimen.crashk_native

import com.mozhimen.crashk.basic.commons.ICrashKListener
import com.mozhimen.crashk_native.commons.ICrashKNative
import com.mozhimen.kotlin.utilk.kotlin.UtilKStrPath
import com.mozhimen.kotlin.utilk.kotlin.createFolder
import com.mozhimen.kotlin.utilk.kotlin.getFolderFiles
import java.io.File

/**
 * @ClassName CrashKNative
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Version 1.0
 */
class CrashKNativeProxy : ICrashKNative {
    private var _crashKListener: ICrashKListener? = null

    ////////////////////////////////////////////////////////////////////////////

    var crashPathNative: String? = null
        get() {
            if (field != null) return field
            val crashFullPath = UtilKStrPath.Absolute.Internal.getCache() + "/crashk_native"
            crashFullPath.createFolder()
            return crashFullPath.also { field = it }
        }

    ////////////////////////////////////////////////////////////////////////////

    override fun init() {
        init(null)
    }

    override fun init(listener: ICrashKListener?) {
        listener?.let { this._crashKListener = it }
        CrashKNativeLib.init(crashPathNative!!)
    }

    override fun testNativeCrash() {
        CrashKNativeLib.testNativeCrash()
    }

    override fun getCrashFiles(): Array<File> =
        crashPathNative!!.getFolderFiles()
}