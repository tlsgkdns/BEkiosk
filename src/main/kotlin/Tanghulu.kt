class Tanghulu (private var price: Int,
                private var name: String,
                private var storeCount: Int,
                private var explanation: String,
                private var orderCountLimit: Int): Menu(price, name, storeCount, explanation, orderCountLimit)
{

                    override fun getPrice(): Int
                    {
                        val ret = price.toDouble() * 0.7
                        return ret.toInt();
                    }
}