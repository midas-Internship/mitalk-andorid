import org.gradle.api.JavaVersion

object Version {
    const val GRADLE_ANDROID = "7.2.2"
    const val GRADLE_KOTLIN = "1.7.0"
    const val GRADLE_KTLINT = "11.1.0"

    val JAVA_VERSION = JavaVersion.VERSION_1_8
    const val KOTLINX_COROUTINES = "1.6.0"

    const val COMPILE_SDK_VERSION = 33
    const val MIN_SDK_VERSION = 28
    const val TARGET_SDK_VERSION = 33

    const val CORE_KTX = "1.5.0"
    const val LIFECYCLE_KTX = "2.4.0-alpha01"

    const val COMPOSE_ACTIVITY = "1.3.1"
    const val COMPOSE = "1.2.0"
    const val COMPOSE_MATERIAL = "1.2.0"

    const val HILT = "2.38.1"

    const val ROOM = "2.4.3"

    const val RETROFIT = "2.7.1"
    const val OKHTTP = "3.14.9"

    const val JUNIT = "4.13.2"
    const val ANDROID_JUNIT = "1.1.2"
    const val ESPRESSO_CORE = "3.3.0"

    const val COIL = "0.10.0"
}