package assembler.symbolTable

import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class SymbolTableImplTest {

    /**
     * User defined symbols to load into a symbol table.
     */
    private val userSymbols: Map<String, Int> = mapOf(
        Pair("@i",    16),
        Pair("@sum",  17),
        Pair("WRITE", 18),
        Pair("LOOP",  19)
    )

    /**
     * A test instantiation of SymbolTableImpl class.
     */
    private lateinit var symbolTable: SymbolTableImpl

    @Before
    fun setup() {
        symbolTable = SymbolTableImpl()
    }

    @Test
    fun addEntry() {
        for ((symbol, address) in userSymbols) {
            symbolTable.addEntry(symbol, address)
        }
        for (entry in userSymbols) {
            assertTrue(symbolTable.map.contains(entry.key))
        }
    }

    @Test
    fun contains() {
        for ((symbol, address) in userSymbols) {
            symbolTable.map[symbol] = address
        }
        for (entry in userSymbols) {
            assertTrue(symbolTable.contains(entry.key))
        }
    }

    @Test
    fun getAddress() {
        for ((key, value) in userSymbols) {
            symbolTable.map[key] = value
        }
        for ((symbol, address) in userSymbols) {
            assertEquals(address, symbolTable.getAddress(symbol))
        }
    }
}