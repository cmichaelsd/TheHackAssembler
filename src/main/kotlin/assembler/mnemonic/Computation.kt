package assembler.mnemonic

object Computation : Mnemonic {
    override val validMnemonics: Map<String, String> = mapOf(
        // Address Computations.
        Pair("0",   "0101010"),
        Pair("1",   "0111111"),
        Pair("-1",  "0111010"),
        Pair("D",   "0001100"),
        Pair("A",   "0110000"),
        Pair("!D",  "0001101"),
        Pair("!A",  "0110001"),
        Pair("-D",  "0001111"),
        Pair("-A",  "0110011"),
        Pair("D+1", "0011111"),
        Pair("A+1", "0110111"),
        Pair("D-1", "0001110"),
        Pair("A-1", "0110010"),
        Pair("D+A", "0000010"),
        Pair("D-A", "0010011"),
        Pair("A-D", "0000111"),
        Pair("D&A", "0000000"),
        Pair("D|A", "0010101"),

        // Memory Computations.
        Pair("M",   "1110000"),
        Pair("!M",  "1110001"),
        Pair("-M",  "1110011"),
        Pair("M+1", "1110111"),
        Pair("M-1", "1110010"),
        Pair("D+M", "1000010"),
        Pair("D-M", "1010011"),
        Pair("M-D", "1000111"),
        Pair("D&M", "1000000"),
        Pair("D|M", "1010101")
    )
}