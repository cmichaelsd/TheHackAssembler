package assembler.code

interface Code {
    fun dest(string: String): String?
    fun comp(string: String): String?
    fun jump(string: String): String?
}