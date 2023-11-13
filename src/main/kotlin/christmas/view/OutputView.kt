package christmas.view

import christmas.util.EventType

class OutputView {
    init {
        printStartMsg()
    }

    private fun formatCurrency(price:Int):String{
        return String.format("%,d원", price)
    }
    private fun printStartMsg(){
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }
    fun printEventMsg(date:Int){
        println("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!".format(date))
    }
    fun printMenu(menuList:List<Map<String,Int>>){
        println("\n<주문메뉴>")
        menuList.flatMap { it.entries }.forEach { (key,value)-> println("$key ${value}개") }
    }
    fun printTotalPrice(totalPrice:Int){
        println("\n<할인 전 총주문 금액>")
        println(formatCurrency(totalPrice))
    }
    fun printFreeGift(applicableEvents:List<String>){
        println("\n<증정 메뉴>")
        if(applicableEvents.contains(EventType.FREE_GIFT.name)) println("샴페인 1개") else println("없음")
    }
    fun printPromotionHistory(discountDetails:List<Pair<String,Int>>){
        println("\n<혜택 내역>")
        discountDetails.forEach {
            println("${it.first}: -${formatCurrency(it.second)}")
        }
    }
    fun printTotalDiscount(totalDiscount:Int){
        println("\n<총혜택 금액>")
        println("-${formatCurrency(totalDiscount)}")
    }
    fun printFinalPayment(finalPayment:Int){
        println("\n<할인 후 예상 결제 금액>")
        println(formatCurrency(finalPayment))
    }
    fun printEventBadge(eventBadge:String){
        var output = ""
        println("\n<12월 이벤트 배지>")
        output = if(EventType.BADGE_EVENT.promotionName.contains(eventBadge)) eventBadge else "없음"
        println(output)
    }
}
