package assembler.parser

import assembler.mnemonic.*

class ParserImpl(private val assemblyInstructions: MutableList<String>) : Parser {
    /**
     * Total number of lines in the file to parse.
     */
    private var totalLines = assemblyInstructions.size - 1

    /**
     * The current line being parsed, will increase with each call to #advance().
     */
    private var currentLine = -1

    /**
     * The current instruction being parsed, is set in #advance().
     */
    var currentInstruction = ""

    /**
     * Determines if there are any more lines.
     *
     * @return Boolean
     */
    override fun hasMoreLines(): Boolean = currentLine < totalLines

    /**
     * Skips over white space and comments, if necessary.
     * Reads the next instruction from the input, and makes it the current instruction.
     * This routine should be called only if hasMoreLines is true.
     * Initially there is no current instruction.
     */
    override fun advance() {
        ++currentLine
        currentInstruction = ""

        while (assemblyInstructions[currentLine].isBlank() || assemblyInstructions[currentLine].indexOf("//") == 0) {
            if (hasMoreLines()) ++currentLine
            else return
        }

        val lineBeingRead = assemblyInstructions[currentLine]

        val commentIndex: Int = lineBeingRead.indexOf("//")

        for ((index, character) in lineBeingRead.withIndex()) {
            if (character == ' ') continue
            if (index == commentIndex) break
            currentInstruction += character
        }
    }

    /**
     * Returns the type of the current instruction:
     * A_INSTRUCTION for @xxx, where xxx is either a decimal number or a symbol.
     * C_INSTRUCTION for dest=comp;jump
     * L_INSTRUCTION for (xxx), where xxx is a symbol.
     *
     * @return Instruction
     */
    override fun instructionType(): Instruction {
        return if (currentInstruction[0] == '@') Instruction.A_INSTRUCTION
        else if (currentInstruction.matches("\\([A-Z]+\\)".toRegex(RegexOption.IGNORE_CASE))) Instruction.L_INSTRUCTION
        else Instruction.C_INSTRUCTION
    }

    /**
     * If the current instruction is (xxx), returns the symbol xxx.
     * If the current instruction is @xxx, returns the symbol or decimal xxx (as a string).
     *
     * Should be called only if instructionType is A_INSTRUCTION or L_INSTRUCTION.
     *
     * @return String
     */
    override fun symbol(): String {
        return if (instructionType() == Instruction.A_INSTRUCTION) currentInstruction.substring(1)
        else currentInstruction.substring(1, currentInstruction.length - 1)
    }

    /**
     * Returns the symbolic dest part of the current C-instruction (8 possibilities).
     *
     * Should be called only in instructionType is C_INSTRUCTION.
     *
     * @return String
     * @throws DestinationException
     */
    @Throws(DestinationException::class)
    override fun dest(): String {
        currentInstruction.let {
            val destExists = it.indexOf("=")
            val destination = if (destExists == -1) "" else it.substring(0, it.indexOf("="))
            return if (Destination.contains(destination)) destination
            else throw DestinationException("Illegal destination token at $currentLine.")
        }
    }

    /**
     * Returns the symbolic comp part of the current C-instruction (28 possibilities).
     *
     * Should be called only in instructionType is C_INSTRUCTION.
     *
     * @return String
     * @throws ComputationException
     */
    @Throws(ComputationException::class)
    override fun comp(): String {
        currentInstruction.let {
            val compExists = it.indexOf("=")
            val computation = if (compExists == -1) it else it.substring(compExists + 1, it.length)
            return if (Computation.contains(computation)) computation
            else throw ComputationException("Illegal computation token at $currentLine.")
        }
    }

    /**
     * Returns the symbolic jump part of the current C-instruction (8 possibilities).
     *
     * Should be called only in instructionType is C_INSTRUCTION.
     *
     * @return String
     * @throws JumpException
     */
    @Throws(JumpException::class)
    override fun jump(): String {
        currentInstruction.let {
            val jumpExists = it.indexOf(";")
            val jump = if (jumpExists == -1) "" else it.substring(jumpExists + 1, it.length)
            return if (Jump.contains(jump)) jump
            else throw JumpException("Illegal jump token at $currentLine.")
        }
    }
}