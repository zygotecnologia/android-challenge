#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_zygotecnologia_zygotv_secret_Keys_apiKey(JNIEnv *env, jobject object) {
    std::string api_key = "27490b1bf49c0e5ffaa07dfd947e9605";
    return env->NewStringUTF(api_key.c_str());
}

