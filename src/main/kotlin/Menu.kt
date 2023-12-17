open class Menu(
    private var price: Int,
    private var name: String,
    private var storeCount: Int,
    private var explanation: String,
    private var orderCountLimit: Int,
)
{
    // 메뉴 정보 반환
    fun displayInfo()
    {
        print("| $name | ${getPrice()} | $explanation |")
        if(storeCount == 0) println("(품절되었습니다.)")
        else println()
    }
    fun isSoldOut(): Boolean
    {
        return (storeCount == 0)
    }
    // 가격 반환
    open fun getPrice(): Int
    {
        return price
    }
    fun getName(): String
    {
        return name
    }
    fun getOrderCountLimit(): Int
    {
        return orderCountLimit
    }
    fun getStoreCount(): Int
    {
        return storeCount
    }
}