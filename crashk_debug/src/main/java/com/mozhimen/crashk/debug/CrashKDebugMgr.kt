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
            val instanceClazz = instanceField.get(null) as Class<*>
            val initMethod = instanceClazz.getDeclaredMethod("init")
            initMethod.invoke(instanceClazz)
        } catch (e: Exception) {
            e.printStackTrace()
        }/*catch (_: ClassNotFoundException) {
        } catch (_: InstantiationException) {
        } catch (_: IllegalAccessException) {
        } catch (_: NoSuchMethodException) {
        } catch (_: InvocationTargetException) {
        }*/
    }
}