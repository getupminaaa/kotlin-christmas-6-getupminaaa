package christmas.util

private const val ERROR_CONVENTION = "[ERROR] "

enum class ErrorMessage(val msg: String) {
    NOT_VALID_DATE(ERROR_CONVENTION + "유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_VALID_ORDER(ERROR_CONVENTION + "유효하지 않은 주문입니다. 다시 입력해 주세요.")
}