package assembler.mnemonic

object Destination : Mnemonic {
    override val validMnemonics: Map<String, String> = mapOf(
        Pair("",  ""),
        Pair("M", ""),
        Pair("D", ""),
        Pair("A", "")
    )

    override fun contains(s: String): Boolean {
        return s.none { !validMnemonics.containsKey(it.toString()) }
    }

    override fun toBinary(s: String): String? {
        val preset = arrayOf(0, 0, 0)
        if (!contains(s)) return null
        for (c in s) {
            if (c == 'A') preset[0] = 1
            if (c == 'D') preset[1] = 1
            if (c == 'M') preset[2] = 1
        }
        return preset.joinToString("")
    }
}