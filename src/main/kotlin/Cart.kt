class Cart {
    private var cart = mutableMapOf<String, Int>()
    private var cost = 0
    fun addProduct(menu: Menu, count: Int)
    {
        val key = menu.getName()
        if(cart.containsKey(key))
            cost -= cart[key]!! * menu.getPrice()
        cart[key] = count
        cost += menu.getPrice() * count
    }
    fun isProductExist(menu: Menu): Boolean
    {
        return cart.containsKey(menu.getName())
    }
    fun payProducts(money: Int): Int
    {
        if(cost > money)
        {
            println("${cost - money}원 부족합니다. 돈을 더 넣어주세요.")
            return money
        }else
        {
            val remain = money - cost
            println("${cost}원 결제되었습니다.")
            println("남은 금액은 ${remain}원입니다.")
            cart = mutableMapOf<String, Int>()
            cost = 0
            return remain
        }
    }

    fun displayCartInfo()
    {
        println("[장바구니]")
        for(product in cart)
            println("${product.key}: ${product.value}개")
        println("총 ${cost}원입니다.")
    }
}