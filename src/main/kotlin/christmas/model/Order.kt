package christmas.model

import christmas.model.data.MenuItem
import christmas.model.data.OrderForm

class Order() {

    val totalPrice: Int
        get() = _totalPrice
    private var _totalPrice = 0

    private var _orderForms = mutableListOf<OrderForm>()
    val orderForms: List<OrderForm>
        get() = _orderForms

    fun writeOrderForm(menuItems: List<MenuItem>, validOrder: List<Map<String, Int>>) {
        val quantities = validOrder.flatMap { it.values }
        menuItems.forEachIndexed { index, menuItem -> _orderForms.add(OrderForm(menuItem, quantities[index])) }
    }

    fun calculateTotalPrice(validOrder: List<Map<String, Int>>, priceList: List<Int>) {
        var total = 0
        validOrder.flatMap { it.values }.forEachIndexed { index, quantity ->
            total += quantity * priceList[index]
        }
        _totalPrice = total
    }


}