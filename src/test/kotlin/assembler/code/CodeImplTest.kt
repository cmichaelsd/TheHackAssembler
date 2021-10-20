package assembler.code

import assembler.mnemonic.Computation
import assembler.mnemonic.Destination
import assembler.mnemonic.Jump
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CodeImplTest {
    @Test
    fun dest() {
        for ((key, value) in Destination.validMnemonics) {
            assertEquals(value, CodeImpl.dest(key))
        }
    }

    @Test
    fun comp() {
        for ((key, value) in Computation.validMnemonics) {
            assertEquals(value, CodeImpl.comp(key))
        }
    }

    @Test
    fun jump() {
        for ((key, value) in Jump.validMnemonics) {
            assertEquals(value, CodeImpl.jump(key))
        }
    }

    private fun permutation(list: MutableList<String>, string: String, prefix: String = "") {
        for ((index, character) in string.withIndex()) {
            val newPrefix = "$prefix$character"
            list.add(newPrefix)
            permutation(list, "${string.substring(0, index)}${string.substring(index + 1, string.length)}", newPrefix)
        }
    }

    private fun getBinary(string: String): String {
        val preset = arrayOf(0, 0, 0)
        for (character in string) {
            if (character == 'A') preset[0] = 1
            if (character == 'D') preset[1] = 1
            if (character == 'M') preset[2] = 1
        }
        return preset.joinToString("")
    }
}