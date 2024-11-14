package com.mozhimen.crashk.java

import android.app.ActivityManager.MemoryInfo
import android.os.Build
import android.util.Log
import com.mozhimen.crashk.basic.commons.ICrashK
import com.mozhimen.crashk.basic.commons.ICrashKListener
import com.mozhimen.crashk.java.commons.ICrashKJava
import com.mozhimen.kotlin.elemk.cons.CMsg
import com.mozhimen.kotlin.lintk.optins.OApiInit_InApplication
import com.mozhimen.kotlin.lintk.optins.permission.OPermission_QUERY_ALL_PACKAGES
import com.mozhimen.kotlin.utilk.android.app.UtilKActivityManager
import com.mozhimen.kotlin.utilk.android.app.UtilKMemoryInfo
import com.mozhimen.kotlin.utilk.wrapper.UtilKApp
import com.mozhimen.kotlin.utilk.android.content.UtilKPackage
import com.mozhimen.kotlin.utilk.wrapper.UtilKDevice
import com.mozhimen.kotlin.utilk.android.os.UtilKBuild
import com.mozhimen.kotlin.utilk.bases.BaseUtilK
import com.mozhimen.kotlin.utilk.java.lang.UtilKThread
import com.mozhimen.kotlin.utilk.java.util.UtilKDateWrapper
import com.mozhimen.kotlin.utilk.kotlin.UtilKStrFile
import com.mozhimen.kotlin.utilk.kotlin.UtilKStrPath
import com.mozhimen.kotlin.utilk.kotlin.UtilKStringFormat
import com.mozhimen.kotlin.utilk.kotlin.createFolder
import com.mozhimen.kotlin.utilk.kotlin.getFolderFiles
import com.mozhimen.kotlin.utilk.kotlin.throwable2printWriter
import com.mozhimen.kotlin.utilk.wrapper.UtilKStorage
import com.mozhimen.kotlin.utilk.android.util.e
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter

/**
 * @ClassName CrashKJava
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Version 1.0
 */
@OApiInit_InApplication
class CrashKJavaProxy : BaseUtilK(), ICrashK, ICrashKJava<CrashKJavaProxy> {

    private var _crashKListener: ICrashKListener? = null
    private var _isRestart = true

    var crashPathJava: String? = null
        get() {
            if (field != null) return field
            val crashFullPath = UtilKStrPath.Absolute.Internal.getCache() + "/crashk_java"
            crashFullPath.createFolder()
            return crashFullPath.also { field = it }
        }

    override fun setEnableRestart(isRestart: Boolean): CrashKJavaProxy {
        _isRestart = isRestart
        return this
    }

    override fun init() {
        init(null)
    }

    override fun init(listener: ICrashKListener?) {
        listener?.let { this._crashKListener = it }
        Thread.setDefaultUncaughtExceptionHandler(CrashKUncaughtExceptionHandler(_isRestart))
    }

    override fun getCrashFiles(): Array<File> =
        crashPathJava!!.getFolderFiles()

    ////////////////////////////////////////////////////////////////////////////////////////

    @OApiInit_InApplication
    private inner class CrashKUncaughtExceptionHandler(private val _isRestart: Boolean = true) : Thread.UncaughtExceptionHandler {
        private val _defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        private val _launchTime = UtilKDateWrapper.getNowStr()

        @OptIn(OPermission_QUERY_ALL_PACKAGES::class)
        override fun uncaughtException(t: Thread, e: Throwable) {
            if (!handleException(e) && _defaultExceptionHandler != null) {
                _defaultExceptionHandler.uncaughtException(t, e)
            }
            if (_isRestart) {
                Thread.sleep(2000)
                UtilKApp.restartApp(isKillProcess = true, isValid = false)
            }
        }

        /**
         * 设备类型、0S本版、线程名、前后台、使用时长、App版本、升级渠道
         * CPU架构、内存信息、存储信息、permission权限
         * @param e Throwable
         * @return Boolean
         */
        private fun handleException(e: Throwable): Boolean {
            val crashLog = collectDeviceInfoAndCrash(e)
            e.printStackTrace()
            Log.e(TAG, "handleException: crashLog $crashLog")

            ///////////////////////////////////////////////////////////////////////////////

            _crashKListener?.onGetCrashLog(crashLog)

            ///////////////////////////////////////////////////////////////////////////////

            saveCrashLog2File(crashLog)
            return true
        }

        private fun saveCrashLog2File(log: String) {
            val savePath = crashPathJava + "/${UtilKStrFile.getStrFileName_ofNow()}.txt"
            UtilKStringFormat.str2file_use(log, savePath)
        }

        private fun collectDeviceInfoAndCrash(e: Throwable): String {
            val stringBuilder = StringBuilder()

            //device info
            stringBuilder.append(CMsg.LINE_BREAK).append(CMsg.PART_LINE_BIAS).append(CMsg.LINE_BREAK)
            stringBuilder.append("brand= ${UtilKBuild.getBrand()}").append(CMsg.LINE_BREAK)//手机品牌
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                stringBuilder.append("cpu_arch= ${UtilKBuild.getSupportABIs()}").append(CMsg.LINE_BREAK)
            }//CPU架构
            stringBuilder.append("model= ${UtilKBuild.getModel()}").append(CMsg.LINE_BREAK)//手机系列
            stringBuilder.append("rom= ${UtilKDevice.getRomVersion()}").append(CMsg.LINE_BREAK)//rom
            stringBuilder.append("os= ${UtilKBuild.getVersionRelease()}").append(CMsg.LINE_BREAK)//API版本:9.0
            stringBuilder.append("sdk= ${UtilKBuild.getVersionSDK()}").append(CMsg.LINE_BREAK)//SDK版本:31
            stringBuilder.append("launch_time= $_launchTime").append(CMsg.LINE_BREAK)//启动APP的时间
            stringBuilder.append("crash_time= ${UtilKDateWrapper.getNowStr()}").append(CMsg.LINE_BREAK)//crash发生的时间
//            stringBuilder.append("foreground= ${StackKCb.instance.isFront()}").append(CMsg.LINE_BREAK)//应用处于前台
            stringBuilder.append("thread= ${UtilKThread.getName_ofCurrent()}").append(CMsg.LINE_BREAK)//异常线程名

            //app info
            stringBuilder.append("version_code= ${UtilKPackage.getVersionCode(0)}").append(CMsg.LINE_BREAK)
            stringBuilder.append("version_name= ${UtilKPackage.getVersionName(0)}").append(CMsg.LINE_BREAK)
            stringBuilder.append("package_code= ${UtilKPackage.getPackageName()}").append(CMsg.LINE_BREAK)
            stringBuilder.append("requested_permissions= ${UtilKPackage.getRequestedPermissionsStr(0)}").append(CMsg.LINE_BREAK)

            //storage info
            val memoryInfo = MemoryInfo()
            UtilKMemoryInfo.get(_context, memoryInfo)
            stringBuilder.append("availableMemory= ${UtilKMemoryInfo.getAvailMemSizeStr(memoryInfo)}").append(CMsg.LINE_BREAK)//可用内存
            stringBuilder.append("totalMemory= ${UtilKMemoryInfo.getTotalMenSizeStr(memoryInfo)}").append(CMsg.LINE_BREAK)//设备总内存

            //sd storage size
            try {
                stringBuilder.append("availableStorage= ${UtilKStorage.getExternalMemorySize_ofFree()}").append(CMsg.LINE_BREAK)//存储空间
            } catch (_: Exception) {
            }
            stringBuilder.append(CMsg.PART_LINE_BIAS).append(CMsg.LINE_BREAK)

            //stack info
            val stringWriter = StringWriter()
            val printWriter = PrintWriter(stringWriter)
            e.throwable2printWriter(printWriter)
//            e.printStackTrace(printWriter)
//            var cause = e.cause
//            while (cause != null) {
//                cause.printStackTrace(printWriter)
//                cause = cause.cause
//            }
//            printWriter.close()
            stringBuilder.append(stringWriter.toString())
            return stringBuilder.toString()
        }
    }
}