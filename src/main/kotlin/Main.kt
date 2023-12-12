fun main(args: Array<String>) {

    // 메뉴 보드 리스트
    var menuList = arrayOf(MenuBoard("생과일", 1), MenuBoard("주스", 2), MenuBoard("탕후루", 3))

    addMenus(menuList) // 메뉴 추가.

    // 키오스크 전체 프로그램.
    while(true)
    {
        println("과일 가게")
        // 메뉴 보드 정보를 보여줌.
        for(menuBoard in menuList)
        {
            menuBoard.displayMenuBoardInfo()
        }
        // 보드 선택 입력
        var boardSelection: Int = 0
        try {
            boardSelection = readln().toInt()
        } catch (e : java.lang.NumberFormatException)
        {
            // 숫자가 아니면 다시.
            println("메뉴 종류 번호를 입력하세요.")
            continue
        }
        if(boardSelection !in 1 .. menuList.size)
        {
            // 1 ~ 메뉴 사이즈 사이 번호가 아니면 다시.
            println("유효하지 않은 번호입니다.")
            continue
        }

        // 메뉴 선택 프로그램.
        while (true)
        {
            // 메뉴 정보 보여줌.
            menuList[boardSelection- 1].displayMenu()
            // 메뉴 선택 입력.
            var menuSelection: Int = 0
            try {
                menuSelection = readln().toInt()
            } catch (e : java.lang.NumberFormatException)
            {
                // 숫자가 아니면 다시.
                println("메뉴 종류 번호를 입력하세요.")
                continue
            }
            if(menuSelection !in 1 .. menuList[boardSelection - 1].getMenuSize())
            {
                // 1 ~ 메뉴 보드에 등재된 메뉴 리스트 사이즈가 아니면 다시.
                println("유효하지 않은 번호입니다.")
                continue
            }
            // 끝.
            break
        }
        println("프로그램을 종료합니다.")
        break
    }

}

fun addMenus(menuList: Array<MenuBoard>)
{
    // 생과일 메뉴 추가.
    menuList[0].addMenu(Fruit(700, "딸기", 40, "맛있는 제철 딸기", 5))
    menuList[0].addMenu(Fruit(1500, "바나나", 20, "당도 높은 바나나(당뇨병 환자 엄금)", 4))
    menuList[0].addMenu(Fruit(2000, "사과", 30, "중력이 좋아하는 사과", 2))
    menuList[0].addMenu(Fruit(1000, "키위", 15, "상큼한 키위", 3))
    menuList[0].addMenu(Fruit(1500, "복숭아", 10, "탱글탱글한 복숭아", 2))
    menuList[0].addMenu(Fruit(1800, "포도", 10, "편히 먹을 수 있는 씨없는 포도", 3))
    // 주스 메뉴 추가
    menuList[1].addMenu(Juice(2000, "딸기 주스", 20, "산지 직송 딸기 주스", 1))
    menuList[1].addMenu(Juice(2000, "바나나 주스", 10, "아침을 안 먹는 당신을 위한 칼로리 높은 바나나 주스(다이어트 하는 사람 엄금)", 1))
    menuList[1].addMenu(Juice(2000, "사과 주스", 30, "식후 소화를 돕는 사과 주스", 1))
    menuList[1].addMenu(Juice(2000, "키위 주스", 10, "상큼한 키위 주스", 1))
    menuList[1].addMenu(Juice(2000, "복숭아 주스", 10, "강제 물복이 된 복숭아", 1))
    menuList[1].addMenu(Juice(2000, "포도 주스", 6, "대기업 보다 맛있는 포도 주스", 1))
    // 탕후루 메뉴 추가
    menuList[2].addMenu(Tanghulu(3000, "딸기 탕후루", 50, "근본 탕후루", 3))
    menuList[2].addMenu(Tanghulu(3000, "바나나 탕후루", 10, "혈당 스타", 3))
    menuList[2].addMenu(Tanghulu(3000, "사과 탕후루", 0, "옆집 잼민이도 좋아하는 사과 탕후루", 3))
    menuList[2].addMenu(Tanghulu(3000, "키위 탕후루", 2, "백종원도 울고갈 설탕 듬뿍 키위 탕후루", 3))
    menuList[2].addMenu(Tanghulu(3000, "복숭아 탕후루", 12, "강제 딱복이 된 복숭아", 3))
    menuList[2].addMenu(Tanghulu(3000, "포도 탕후루", 50, "샤인머스켓 아님 취급 안함.", 3))
}