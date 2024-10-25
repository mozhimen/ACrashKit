package com.mozhimen.crashk.commons

import java.io.File


/**
 * @ClassName ICrashKMgr
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
interface ICrashKMgr {
    fun getJavaCrashFiles(): Array<File>
    fun getNativeCrashFiles(): Array<File>
    fun getCrashFiles(): Array<File>
}