package assembler.mnemonic

object Destination : Mnemonic {
    override val validMnemonics: Map<String, String> = mapOf(
        Pair("", "000"),
        Pair("M", "001"),
        Pair("D", "010"),
        Pair("DM", "011"),
        Pair("A", "100"),
        Pair("AM", "101"),
        Pair("AD", "110"),
        Pair("ADM", "111")
    )
}