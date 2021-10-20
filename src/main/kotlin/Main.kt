import assembler.parser.ParserImpl
import assembler.symbolTable.SymbolTableImpl
import java.io.File

const val FILE_ARGUMENT_INDEX = 0
const val VALID_FILE_READ_TYPE = ".asm"

fun main(args: Array<String>) {
    // Try adding program arguments at Run/Debug configuration.
    println("Program arguments: ${args.joinToString()}")

    // If no arguments nothing to compile, return.
    if (args.isEmpty()) return

    // Get file from argument.
    val file = File(args[FILE_ARGUMENT_INDEX])

    // If not valid file type, return.
    if (!file.exists() && file.extension != VALID_FILE_READ_TYPE) return

    // Initialize ParserImpl with file.
    val parserImpl = ParserImpl(file)

    // Initialize SymbolTableImpl.
    val symbolTableImpl = SymbolTableImpl()

    // First pass through for label instructions.
    Driver.parseLabels(parserImpl, symbolTableImpl)

    // Second pass through for variables and generating binaries.
    val list = Driver.generateBinaries(parserImpl, symbolTableImpl)

    // The file will be saved as a .hack file in the same directory as the parsed assembly file.
    val resultFilePath = "${file.parentFile}/${file.nameWithoutExtension}.hack"

    // Write binaries to an output file.
    File(resultFilePath).bufferedWriter().use { writer ->
        list.forEach { line ->
            writer.write(line)
            writer.newLine()
        }
    }
}
