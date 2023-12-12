class MenuBoard
    (
    private var menuBoardName: String ,
    private var keyNumber: Int)
{
    private val menus: ArrayList<Menu> = ArrayList<Menu>()
    
    // 메뉴보드 정보 표시
    fun displayMenuBoardInfo()
    {
        println("${keyNumber}. ${menuBoardName}")
    }
    
    // 메뉴 리스트 정보 표시
    fun displayMenu()
    {
        // menus[idx] = menu
        for((idx, menu) in menus.withIndex())
        {
            print("${idx+1}. ")
            menu.displayInfo()
        }
    }
    
    // 메뉴보드에 메뉴 추가
    fun addMenu(menu: Menu)
    {
        menus.add(menu)
    }

    // 메뉴보드에 등재된 메뉴 사이즈 반환
    fun getMenuSize(): Int
    {
        return menus.size
    }
}