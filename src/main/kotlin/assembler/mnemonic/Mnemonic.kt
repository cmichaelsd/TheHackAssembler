package assembler.mnemonic

interface Mnemonic {
    /**
     * A map containing valid sequences of characters or valid characters which comprise a mnemonic.
     */
    val validMnemonics: MutableMap<String, String>

    /**
     * Determines is a give token is a valid mnemonic.
     *
     * @param  s       the token being checked for validity
     * @return Boolean
     */
    fun contains(s: String): Boolean = validMnemonics.containsKey(s)

    /**
     * Returns the binary string equivalent of a token.
     *
     * @param  s       the token being converted to a binary string
     * @return String?
     */
    fun toBinary(s: String): String? = validMnemonics[s]
}