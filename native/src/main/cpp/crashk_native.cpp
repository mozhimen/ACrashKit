#include <jni.h>
#include <string>
#include "android/log.h"
#include "crashk_native/src/client/linux/handler/minidump_descriptor.h"
#include "crashk_native/src/client/linux/handler/exception_handler.h"

#define LOG_TAG "crashk_native"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)

bool MinidumpCallbak(const google_breakpad::MinidumpDescriptor &descriptor, void *context,
                     bool is_success) {
    LOGD("dump path:%s\n", descriptor.path());
    return is_success;
}

extern "C"
JNIEXPORT void JNICALL
Java_com_mozhimen_crashk_1native_CrashKNativeLib_init(JNIEnv *env, jobject thiz,
                                                      jstring save_path) {
    const char *path = env->GetStringUTFChars(save_path, 0);
    google_breakpad::MinidumpDescriptor descriptor(path);
    static google_breakpad::ExceptionHandler handler(descriptor, NULL, MinidumpCallbak, NULL, true,
                                                     -1);
    env->ReleaseStringUTFChars(save_path, path);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_mozhimen_crashk_1native_CrashKNativeLib_testNativeCrash(JNIEnv *env, jobject thiz) {
    int *a = nullptr;
    *a = 1;
}