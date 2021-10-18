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
}