package com.mozhimen.crashk.basic.commons

import java.io.File


/**
 * @ClassName ICrashK
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Version 1.0
 */
interface ICrashK {
    fun init(listener: ICrashKListener?)
    fun getCrashFiles(): Array<File>
}