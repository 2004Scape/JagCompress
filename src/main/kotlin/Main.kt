import java.io.File
import java.io.FileOutputStream

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        print("Usage: jagcompress <input files>")
        return;
    }

    for (path in args) {
        var input = File(path).readBytes()
        var output = FileOutputStream("$path.gz")
        var stream = JagexGzipOutputStream(output)
        stream.write(input)
        stream.close()
    }
}