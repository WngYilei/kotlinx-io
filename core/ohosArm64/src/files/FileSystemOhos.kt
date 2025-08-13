package kotlinx.io.files

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cstr
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.toKString
import platform.posix.basename
import platform.posix.dirname


// Implement by NativeNonApple
//internal actual fun metadataOrNullImpl(path: Path): FileMetadata? {
//    TODO("Not yet implemented")
//}

// Implement by unix
//internal actual fun atomicMoveImpl(source: Path, destination: Path) {
//
//}
//
//internal actual fun mkdirImpl(path: String) {
//
//}
//
//internal actual fun realpathImpl(path: String): String {
//    TODO("Not yet implemented")
//}


// Implement by nativeNonAndroid
//internal actual fun opendir(path: String): OpaqueDirEntry {
//    TODO("Not yet implemented")
//}
//
//@OptIn(ExperimentalForeignApi::class)
//internal actual class OpaqueDirEntry() : AutoCloseable {
//    actual fun readdir(): String? {
//        TODO("Not yet implemented")
//    }
//
//    actual override fun close() {
//        TODO("Not yet implemented")
//    }
//}

@OptIn(ExperimentalForeignApi::class)
internal actual fun dirnameImpl(path: String): String {
    if (!path.contains(SystemPathSeparator)) {
        return ""
    }
    memScoped {
        return dirname(path.cstr.ptr)?.toKString() ?: ""
    }
}

@OptIn(ExperimentalForeignApi::class)
internal actual fun basenameImpl(path: String): String {
    memScoped {
        return basename(path.cstr.ptr)?.toKString() ?: ""
    }
}

internal actual fun isAbsoluteImpl(path: String): Boolean = path.startsWith('/')

