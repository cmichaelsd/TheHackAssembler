package assembler.mnemonic

interface Mnemonic {
    val validMnemonics: Map<String, String>
    fun contains(s: String): Boolean {
        if (validMnemonics.containsKey(s)) return true
        return false
    }

    fun toBinary(s: String): String? {
        return validMnemonics[s]
    }
}