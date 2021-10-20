import assembler.code.CodeImpl
import assembler.parser.Instruction
import assembler.parser.ParserImpl
import assembler.symbolTable.SymbolTableImpl
import java.util.concurrent.atomic.AtomicInteger

object Driver {
    /**
     * Generates a list of binaries from the given file.
     *
     * @param  parserImpl      the implementation of the Parser interface to parse assembly files
     * @param  symbolTableImpl the implementation of the SymbolTable interface to retrieve the symbols address
     * @return List<String>
     */
    fun generateBinaries(parserImpl: ParserImpl, symbolTableImpl: SymbolTableImpl): List<String> {
        val registerCounter = AtomicInteger(15)
        val list = mutableListOf<String>()
        while (parserImpl.hasMoreLines()) {
            parserImpl.advance()
            val symbol = parserImpl.symbol()
            if (parserImpl.instructionType() == Instruction.A_INSTRUCTION && !symbolTableImpl.contains(symbol)) {
                symbolTableImpl.addEntry(symbol, registerCounter.incrementAndGet())
            }
            val binary = createBinaryForInstructionType(parserImpl, symbolTableImpl)
            if (binary.isNotBlank()) list.add(binary)
        }
        parserImpl.reset()
        return list
    }

    /**
     * Parse the document and enter label symbols into the SymbolTable.
     *
     * @param parserImpl      the implementation of the Parser interface to parse assembly files
     * @param symbolTableImpl the implementation of the SymbolTable interface to store assembly symbols
     */
    fun parseLabels(parserImpl: ParserImpl, symbolTableImpl: SymbolTableImpl) {
        while (parserImpl.hasMoreLines()) {
            parserImpl.advance()
            if (parserImpl.instructionType() == Instruction.L_INSTRUCTION) {
                symbolTableImpl.addEntry(parserImpl.symbol(), parserImpl.significantLine + 1)
            }
        }
        parserImpl.reset()
    }

    /**
     * Creates binary string for C_INSTRUCTION and A_INSTRUCTION type.
     *
     * @param  parserImpl      the implementation of the Parser interface to parse assembly files
     * @param  symbolTableImpl the implementation of the SymbolTable interface to store assembly symbols
     * @return String
     */
    private fun createBinaryForInstructionType(parserImpl: ParserImpl, symbolTableImpl: SymbolTableImpl): String {
        return if (parserImpl.instructionType() == Instruction.C_INSTRUCTION) {
            getCInstructionInBinary(parserImpl.dest(),  parserImpl.comp(), parserImpl.jump())
        } else if (parserImpl.instructionType() == Instruction.A_INSTRUCTION) {
            getNonCInstructionInBinary(parserImpl.symbol(), symbolTableImpl)
        } else ""
    }

    /**
     * Constructs a binary string for an L_INSTRUCTION or A_INSTRUCTION.
     *
     * @param  symbol          the symbol to be converted to a binary string
     * @param  symbolTableImpl the implementation of the SymbolTable interface to retrieve the symbols address
     * @return String
     */
    private fun getNonCInstructionInBinary(symbol: String, symbolTableImpl: SymbolTableImpl): String {
        val address = symbolTableImpl.getAddress(symbol) ?: return ""
        return Integer.toBinaryString(address).padStart(16, '0')
    }

    /**
     * Constructs a binary string for a C_INSTRUCTION.
     *
     * @param  dest   the destination portion of the current C_INSTRUCTION
     * @param  comp   the computation portion of the current C_INSTRUCTION
     * @param  jump   the jump portion of the current C_INSTRUCTION
     * @return String
     */
    private fun getCInstructionInBinary(dest: String, comp: String, jump: String): String {
        val d = CodeImpl.dest(dest) ?: return ""
        val c = CodeImpl.comp(comp) ?: return ""
        val j = CodeImpl.jump(jump) ?: return ""
        return "${c}${d}${j}".padStart(13, '0').padStart(16, '1')
    }
}