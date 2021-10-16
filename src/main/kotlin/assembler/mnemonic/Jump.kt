package assembler.mnemonic

object Jump : Mnemonic {
    override val validMnemonics: Map<String, String> = mapOf(
        Pair("", "000"),
        Pair("JGT", "001"),
        Pair("JEQ", "010"),
        Pair("JGE", "011"),
        Pair("JLT", "100"),
        Pair("JNE", "101"),
        Pair("JLE", "110"),
        Pair("JMP", "111")
    )
}