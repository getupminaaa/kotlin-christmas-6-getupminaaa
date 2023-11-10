package christmas.util

object Validator {
    fun isItInteger(input:String){
        val value = input.toIntOrNull()
        require(value != null) { "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요." }
    }
}