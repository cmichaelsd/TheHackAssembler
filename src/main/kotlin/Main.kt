import java.io.File

const val FILE_ARGUMENT_INDEX = 0
const val VALID_FILE_READ_TYPE = ".asm"

fun main(args: Array<String>) {
    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")

    // If no arguments nothing to compile, return.
    if (args.isEmpty()) return

    // Get file from file path argument.
    val path = args[FILE_ARGUMENT_INDEX]
    val file = File(path)

    // If file is not a valid type or doesn't exist, return.
    if (!file.exists() && file.extension != VALID_FILE_READ_TYPE) return

    val lineList = mutableListOf<String>()

    file.useLines { lines ->
        lines.forEach { line ->
            lineList.add(line)
        }
    }

//    lineList.forEach { println(">  $it") }


}