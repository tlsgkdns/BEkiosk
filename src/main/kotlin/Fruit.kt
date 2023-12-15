class Fruit(
    price: Int,
    private var name: String,
    private var storeCount: Int,
    private var explanation: String,
    private var orderCountLimit: Int
) : Menu(price, name, storeCount, explanation, orderCountLimit) {

}