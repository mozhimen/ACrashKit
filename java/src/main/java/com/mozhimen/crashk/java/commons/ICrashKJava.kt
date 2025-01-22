package com.mozhimen.crashk.java.commons

/**
 * @ClassName ICrashKJava
 * @Description TODO
 * @Author mozhimen
 * @Date 2024/10/25
 * @Version 1.0
 */
interface ICrashKJava<T> {
    fun setEnableRestart(isRestart: Boolean): T
}