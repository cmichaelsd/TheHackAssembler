import assembler.parser.ParserImpl
import assembler.symbolTable.SymbolTableImpl
import java.io.File
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertTrue

internal class DriverTest {
    /**
     * The path to a test assembly file for parsing.
     */
    private val path = this.javaClass.getResource("/Rect.asm")!!.path

    /**
     * The file to be parsed.
     */
    private val file = File(path)

    /**
     * An instantiation of ParserImpl.
     */
    private val parserImpl = ParserImpl(file)

    /**
     * An instantiation of SymbolTableImpl.
     */
    private val symbolTableImpl = SymbolTableImpl()

    /**
     * The path to a test assembly binary file for parsing.
     */
    private val resultPath = this.javaClass.getResource("/Rect.hack")!!.path

    /**
     * The binary file to be parsed.
     */
    private val resultFile = File(resultPath)

    /**
     * A list of binaries from result file.
     */
    private val expectedResult = resultFile.useLines { it.toList() }

    @Test
    fun generateBinaries() {
        Driver.parseLabels(parserImpl, symbolTableImpl)
        assertContentEquals(expectedResult, Driver.generateBinaries(parserImpl, symbolTableImpl))
    }

    @Test
    fun parseLabels() {
        Driver.parseLabels(parserImpl, symbolTableImpl)
        for (label in arrayOf("LOOP", "INFINITE_LOOP")) {
           assertTrue(symbolTableImpl.contains(label))
        }
    }
}