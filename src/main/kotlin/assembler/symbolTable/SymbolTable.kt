package assembler.symbolTable

interface SymbolTable {
    val map: MutableMap<String, Int>
    fun addEntry(symbol: String, address: Int)
    fun contains(symbol: String): Boolean
    fun getAddress(symbol: String): Int?
}