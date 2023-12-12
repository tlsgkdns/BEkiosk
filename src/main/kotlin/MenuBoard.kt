class MenuBoard
    (
    private var menuBoardName: String ,
    private var keyNumber: Int)
{
    private val menus: ArrayList<Menu> = ArrayList<Menu>()
    fun displayMenuBoardInfo()
    {
        println("${keyNumber}. ${menuBoardName}")
    }
    fun displayMenu()
    {
        for((idx, menu) in menus.withIndex())
        {
            // menus[idx] = menu
            print("${idx+1}. ")
            menu.displayInfo()
        }
    }
    fun addMenu(menu: Menu)
    {
        menus.add(menu)
    }
    fun getMenuSize(): Int
    {
        return menus.size
    }
}