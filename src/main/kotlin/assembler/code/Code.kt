package assembler.code

interface Code {
    fun dest(s: String): String?
    fun comp(s: String): String?
    fun jump(s: String): String?
}