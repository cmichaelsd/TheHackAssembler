package assembler.mnemonic

object Destination : Mnemonic {
    override val validMnemonics: MutableMap<String, String> = mutableMapOf(Pair("",  "000"))

    init {
        val permutations = mutableListOf<String>()
        permutation(permutations, "AMD")
        for (permutation in permutations) {
            validMnemonics[permutation] = generateBinary(permutation)
        }
    }

    /**
     * Generates binaries for a permutation of the set {A, M, D}.
     *
     * @param  s      the string to be parsed as a binary string
     * @return String
     */
    private fun generateBinary(s: String): String {
        val preset = arrayOf(0, 0, 0)
        for (c in s) {
            if (c == 'A') preset[0] = 1
            if (c == 'D') preset[1] = 1
            if (c == 'M') preset[2] = 1
        }
        return preset.joinToString("")
    }

    /**
     * Generates every permutation for a given string.
     *
     * @param list   a list which permutations are loaded into
     * @param s      a string which to create permutations from
     * @param prefix the prefix to begin the operation with, default is a blank string
     */
    private fun permutation(list: MutableList<String>, s: String, prefix: String = "") {
        for ((index, character) in s.withIndex()) {
            val newPrefix = "$prefix$character"
            list.add(newPrefix)
            permutation(list, "${s.substring(0, index)}${s.substring(index + 1, s.length)}", newPrefix)
        }
    }
}