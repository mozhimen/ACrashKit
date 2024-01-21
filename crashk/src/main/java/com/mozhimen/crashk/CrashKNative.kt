package com.mozhimen.crashk

import com.mozhimen.crashk.commons.ICrashKListener
import com.mozhimen.basick.utilk.kotlin.UtilKStrPath
import com.mozhimen.basick.utilk.kotlin.createFolder
import com.mozhimen.basick.utilk.kotlin.getFolderFiles
import com.mozhimen.crashk_native.CrashKNativeLib
import com.mozhimen.crashk.commons.ICrashK
import java.io.File

/**
 * @ClassName CrashKNative
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Date 2022/3/28 14:34
 * @Version 1.0
 */
class CrashKNative : ICrashK {
    private var _crashKListener: ICrashKListener? = null

    var crashPathNative: String? = null
        get() {
            if (field != null) return field
            val crashFullPath = UtilKStrPath.Absolute.Internal.getCache() + "/crashk_native"
            crashFullPath.createFolder()
            return crashFullPath.also { field = it }
        }

    override fun init(listener: ICrashKListener?) {
        listener?.let { this._crashKListener = it }
        CrashKNativeLib.init(crashPathNative!!)
    }

    override fun getCrashFiles(): Array<File> =
        crashPathNative!!.getFolderFiles()
}