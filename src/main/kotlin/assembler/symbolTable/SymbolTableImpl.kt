package assembler.symbolTable

class SymbolTableImpl : SymbolTable {
    /**
     * Hash map to contain symbol / address association.
     */
    override val map: MutableMap<String, Int> = mutableMapOf(
        // Virtual Registers
        Pair("R0",     0),
        Pair("R1",     1),
        Pair("R2",     2),
        Pair("R3",     3),
        Pair("R4",     4),
        Pair("R5",     5),
        Pair("R6",     6),
        Pair("R7",     7),
        Pair("R8",     8),
        Pair("R9",     9),
        Pair("R10",    10),
        Pair("R11",    11),
        Pair("R12",    12),
        Pair("R13",    13),
        Pair("R14",    14),
        Pair("R15",    15),

        // I/O Pointers
        Pair("SCREEN", 16384),
        Pair("KBD",    24576),

        // VM Control Pointers
        Pair("SP",     0),
        Pair("LCL",    1),
        Pair("ARG",    2),
        Pair("THIS",   3),
        Pair("THAT",   4),
    )

    /**
     * Adds <symbol, address> to the table.
     *
     * @param symbol the current symbol which to add to the symbol table
     */
    override fun addEntry(symbol: String, address: Int) {
        map[symbol] = address
    }

    /**
     * Determines if the table contains the given symbol.
     *
     * @param  symbol  the current symbol being checked to see if it exists in the symbol table
     * @return Boolean
     */
    override fun contains(symbol: String): Boolean = symbol in map

    /**
     * Returns the address associated with the symbol.
     *
     * @param  symbol the current symbol offered to fetch associated address
     * @return Int
     */
    override fun getAddress(symbol: String): Int? = map[symbol]
}