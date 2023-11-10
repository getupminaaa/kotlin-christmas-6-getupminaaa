package christmas.util

object Validator {
    private val dateRegex = "^([1-9]|[12]\\d|3[01])\$".toRegex()

    fun isDateInRange(input:String){
        require(dateRegex.matches(input)){"[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."}
    }



}