package assembler.mnemonic

object Computation : Mnemonic {
    override val validMnemonics: Map<String, String> = mapOf(
        // Address Computations.
        Pair("0", "101010"),
        Pair("1", "111111"),
        Pair("-1", "111010"),
        Pair("D", "001100"),
        Pair("A", "110000"),
        Pair("!D", "001101"),
        Pair("!A", "110001"),
        Pair("-D", "001111"),
        Pair("-A", "110011"),
        Pair("D+1", "011111"),
        Pair("A+1", "110111"),
        Pair("D-1", "001110"),
        Pair("A-1", "110010"),
        Pair("D+A", "000010"),
        Pair("D-A", "010011"),
        Pair("A-D", "000111"),
        Pair("D&A", "000000"),
        Pair("D|A", "010101"),

        // Memory Computations.
        Pair("M", "110000"),
        Pair("!M", "110001"),
        Pair("-M", "110011"),
        Pair("M+1", "110111"),
        Pair("M-1", "110010"),
        Pair("D+M", "000010"),
        Pair("D-M", "010011"),
        Pair("M-D", "000111"),
        Pair("D&M", "000000"),
        Pair("D|M", "010101")
    )
}