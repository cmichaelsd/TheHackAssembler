package assembler.code

import assembler.mnemonic.*

object CodeImpl : Code {
    /**
     * Returns the binary code of the dest mnemonic.
     *
     * @param  string               the destination string which to fetch binary equivalent for
     * @return String?
     * @throws DestinationException
     */
    @Throws(DestinationException::class)
    override fun dest(string: String): String? {
        return if (Destination.contains(string)) Destination.toBinary(string)
        else throw DestinationException("Destination $string is an invalid destination token.")
    }

    /**
     * Returns the binary code of the comp mnemonic.
     *
     * @param  string               the computation string which to fetch binary equivalent for
     * @return String
     * @throws ComputationException
     */
    @Throws(ComputationException::class)
    override fun comp(string: String): String? {
        return if (Computation.contains(string)) Computation.toBinary(string)
        else throw ComputationException("Computation $string is an invalid computation token.")
    }

    /**
     * Returns the binary code of the jump mnemonic.
     *
     * @param  string        the jump string which to fetch binary equivalent for
     * @return String
     * @throws JumpException
     */
    @Throws(JumpException::class)
    override fun jump(string: String): String? {
        return if (Jump.contains(string)) Jump.toBinary(string)
        else throw JumpException("Jump $string is an invalid jump token.")
    }
}