package com.mozhimen.crashk.crashlytics

import com.mozhimen.libk.google.firebase.basic.optins.OApiInit_InApplication_FirebaseBasicMgr
import com.mozhimen.libk.google.firebase.crashlytics.optins.OPlugin_ClassPath_FirebaseCrashlytics
import com.mozhimen.libk.google.firebase.crashlytics.FirebaseCrashlyticsMgr
import com.mozhimen.libk.google.firebase.crashlytics.optins.OBuildApp_NeedMappingFileConfig

/**
 * @ClassName FirebaseCrashlytics
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/6/17
 * @Version 1.0
 */
object CrashKCrashlyticsMgr {
    @OBuildApp_NeedMappingFileConfig
    @JvmStatic
    @OPlugin_ClassPath_FirebaseCrashlytics
    @OApiInit_InApplication_FirebaseBasicMgr
    fun init() {
        FirebaseCrashlyticsMgr.init()
    }
}