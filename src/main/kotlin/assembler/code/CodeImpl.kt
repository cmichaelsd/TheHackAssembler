package assembler.code

import assembler.mnemonic.*

object CodeImpl : Code {
    /**
     * Returns the binary code of the dest mnemonic.
     *
     * @return String
     * @throws DestinationException
     */
    @Throws(DestinationException::class)
    override fun dest(s: String): String? {
        return if (Destination.contains(s)) Destination.toBinary(s)
        else throw DestinationException("Destination $s is an invalid destination token.")
    }

    /**
     * Returns the binary code of the comp mnemonic.
     *
     * @return String
     * @throws ComputationException
     */
    @Throws(ComputationException::class)
    override fun comp(s: String): String? {
        return if (Computation.contains(s)) Computation.toBinary(s)
        else throw ComputationException("Computation $s is an invalid computation token.")
    }

    /**
     * Returns the binary code of the jump mnemonic.
     *
     * @return String
     * @throws JumpException
     */
    @Throws(JumpException::class)
    override fun jump(s: String): String? {
        return if (Jump.contains(s)) Jump.toBinary(s)
        else throw JumpException("Jump $s is an invalid jump token.")
    }
}