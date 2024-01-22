package com.mozhimen.crashk.debug

import java.lang.reflect.InvocationTargetException

/**
 * @ClassName CrashKDebugMgr
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/1/22
 * @Version 1.0
 */
object CrashKDebugMgr {

    @JvmStatic
    fun init() {
        try {
            val instanceField = Class.forName("com.mozhimen.crashk.CrashKMgr").getDeclaredField("instance")
            if (!instanceField.isAccessible)
                instanceField.isAccessible = true
            val instance = instanceField.get(null)
            val initMethod = Class.forName("com.mozhimen.crashk.CrashKMgr").getDeclaredMethod("init")
            initMethod.invoke(instance)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}