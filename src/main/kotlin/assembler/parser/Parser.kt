package assembler.parser

interface Parser {
    fun hasMoreLines(): Boolean
    fun advance()
    fun instructionType(): Instruction
    fun symbol(): String
    fun dest(): String
    fun comp(): String
    fun jump(): String
}