package com.mozhimen.crashk_native

/**
 * @ClassName CrashKNativeLib
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/11/8 23:23
 * @Version 1.0
 */
object CrashKNativeLib {
    init {
        System.loadLibrary("crashk_native")
    }

    external fun init(savePath: String)

    external fun testNativeCrash()
}