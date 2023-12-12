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
        println("| ${name} | ${getPrice()} | ${explanation}")
    }
    
    // 가격 반환
    open fun getPrice(): Int
    {
        return price;
    }
}