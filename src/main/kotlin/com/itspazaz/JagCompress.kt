package com.itspazaz

import java.io.File
import java.io.FileOutputStream

import org.openrs2.compress.bzip2.*
import org.openrs2.compress.gzip.*

fun main(args: Array<String>) {
    if (args.isEmpty() || args.size < 2 || (args[0] != "gz" && args[0] != "bz2")) {
        print("Usage: jagcompress <gz/bz2> <input files>")
        return;
    }

    var lib: LibBzip2? = null
    val type: String = args[0]
    if (type == "bz2") {
        lib = LibBzip2.load()
    }

    for ((index, path) in args.withIndex()) {
        if (index == 0) {
            continue
        }

        try {
            val input = File(path).readBytes()

            if (type == "gz") {
                val output = FileOutputStream("$path.gz")
                val stream = JagexGzipOutputStream(output)
                stream.write(input)
                stream.close()
            } else if (type == "bz2") {
                val output = FileOutputStream("$path.bz2")
                val stream = Bzip2OutputStream(lib, output, 1)
                stream.write(input)
                stream.close()
            }
        } catch (ex: Exception) {
            println("Failed to " + type + " compress " + path);
        }
    }
}
