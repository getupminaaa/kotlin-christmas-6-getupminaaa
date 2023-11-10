package christmas.view

class OutputView {
    init {
        printStartMsg()
    }

    private fun printStartMsg(){
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }
    fun printEventMsg(date:Int){
        println("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!".format(date))
    }
}