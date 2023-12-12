class Menu(
    private var price: Int,
    private var name: String,
    private var storeCount: Int,
    private var explanation: String,
    private var orderCountLimit: Int,
)
{
    fun displayInfo()
    {
        println("| ${name} | ${price} | ${explanation}")
    }
}