package assembler.parser

import org.junit.Before
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ParserImplTest {
    /**
     * The excepted significant lines to be read by ParserImpl class.
     */
    private val expectedResults = arrayOf(
        "@2",
        "D=A",
        "@3",
        "D=D+A",
        "@0",
        "M=D"
    )

    /**
     * A test instantiation of ParserImpl class.
     */
    private lateinit var testParserImpl: ParserImpl

    @Before
    fun setup() {
        val path = this.javaClass.getResource("/Add.asm")?.path ?: ""
        val file = File(path)
        testParserImpl = ParserImpl(file)
    }

    @Test
    fun hasMoreLines() {
        val isMoreLines = arrayOf(
            true,
            true,
            true,
            true,
            true,
            false
        )
        for (i in expectedResults.indices) {
            testParserImpl.advance()
            assertEquals(isMoreLines[i], testParserImpl.hasMoreLines())
        }
    }

    @Test
    fun advance() {
        for (result in expectedResults) {
            testParserImpl.advance()
            assertEquals(testParserImpl.currentInstruction, result)
        }
    }

    @Test
    fun instructionType() {
        val instructionTypes = arrayOf(
            Pair("@2", Instruction.A_INSTRUCTION),
            Pair("(LOOP)", Instruction.L_INSTRUCTION),
            Pair("D=D+A", Instruction.C_INSTRUCTION)
        )
        for (pair in instructionTypes) {
            testParserImpl.currentInstruction = pair.first
            assertEquals(pair.second, testParserImpl.instructionType())
        }
    }

    @Test
    fun symbol() {
        val instructionTypes = arrayOf(
            Pair("@2", "2"),
            Pair("(LOOP)", "LOOP")
        )
        for (pair in instructionTypes) {
            testParserImpl.currentInstruction = pair.first
            assertEquals(pair.second, testParserImpl.symbol())
        }
    }

    @Test
    fun dest() {
        val cInstructions = arrayOf(
            Pair("D=D+A", "D"),
            Pair("D+A", "")
        )
        for (pair in cInstructions) {
            testParserImpl.currentInstruction = pair.first
            assertEquals(pair.second, testParserImpl.dest())
        }
    }

    @Test
    fun comp() {
        val cInstructions = arrayOf(
            Pair("D=D+A", "D+A"),
            Pair("D+A", "D+A")
        )
        for (pair in cInstructions) {
            testParserImpl.currentInstruction = pair.first
            assertEquals(pair.second, testParserImpl.comp())
        }
    }

    @Test
    fun jump() {
        val cInstructions = arrayOf(
            Pair("D=D+A;JLE", "JLE"),
            Pair("D+A", "")
        )
        for (pair in cInstructions) {
            testParserImpl.currentInstruction = pair.first
            assertEquals(pair.second, testParserImpl.jump())
        }
    }
}