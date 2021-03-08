#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_zygotecnologia_zygotv_utils_ApiKeyLibrary_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string apy_key = "27490b1bf49c0e5ffaa07dfd947e9605";
    return env->NewStringUTF(apy_key.c_str());
}