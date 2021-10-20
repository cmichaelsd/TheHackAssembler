package assembler.mnemonic

interface Mnemonic {
    /**
     * A map containing valid sequences of characters or valid characters which comprise a mnemonic.
     */
    val validMnemonics: Map<String, String>

    /**
     * Determines is a give token is a valid mnemonic.
     *
     * @param  s       the token being checked for validity
     * @return Boolean
     */
    fun contains(s: String): Boolean {
        if (validMnemonics.containsKey(s)) return true
        return false
    }

    /**
     * Returns the binary string equivalent of a token.
     *
     * @param  s       the token being converted to a binary string
     * @return String?
     */
    fun toBinary(s: String): String? {
        return validMnemonics[s]
    }
}