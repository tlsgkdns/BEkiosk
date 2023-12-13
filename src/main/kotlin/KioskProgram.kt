class KioskProgram {
    private var money: Int = 0
    private var menuList = arrayOf(MenuBoard("생과일", 1), MenuBoard("주스", 2), MenuBoard("탕후루", 3))
    private var cart = Cart()
    init
    {
        addMenus(menuList)
        println("과일 가게에 오신 것을 환영합니다!")
        runProgram()
    }
    private fun runProgram()
    {
        while (true)
        {
            println("현재 소지하고 있는 금액: $money")
            cart.displayCartInfo()
            println("1. 주문하기")
            println("2. 돈 넣기")
            println("3. 결제하기")
            println("4. 프로그램 종료")
            var command = 0
            try {
                command = readln().toInt()
            } catch (e : java.lang.NumberFormatException)
            {
                println("올바르지 않은 입력입니다.")
                continue
            }
            when(command)
            {
                1 -> orderMenu()
                2 -> insertMoney()
                3 -> payCart()
                4 -> break
                else -> {
                    println("올바르지 않은 입력입니다.")
                }
            }
        }
    }
    private fun payCart()
    {
        cart.displayCartInfo()
        println("결제하시겠습니까?")
        println("결제는 1, 메인메뉴로 돌아가려면 아무거나 입력해주세요")
        val answer = readln()
        if(answer == "1")
        {
            money = cart.payProducts(money)
        }
        else
        {
            println("메인메뉴로 돌아갑니다.")
        }
    }
    private fun insertMoney()
    {
        while (true)
        {
            print("돈을 넣어주세요: ")
            var addMoney = 0
            try {
                addMoney = readln().toInt()
            } catch (e : java.lang.NumberFormatException)
            {
                println("유효하지 않은 값입니다. 다시 입력해주세여.")
                continue
            }
            if(money + addMoney < 0)
            {
                println("돈을 잘못 넣었습니다. 다시 넣어주세여.")
                continue
            }
            money += addMoney
            break
        }
    }
    private fun addMenus(menuList: Array<MenuBoard>)
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
    private fun orderMenu()
    {
        while(true)
        {
            println("0. 메인메뉴로...")
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
            if(boardSelection == 0)
            {
                println("메인메뉴로 돌아갑니다.")
                break
            }
            if(boardSelection !in 1 .. menuList.size)
            {
                // 1 ~ 메뉴 사이즈 사이 번호가 아니면 다시.
                println("유효하지 않은 번호입니다.")
                continue
            }
            selectMenu(boardSelection)
        }
    }
    private fun selectMenu(boardSelection: Int)
    {
        while (true)
        {
            println("0. 뒤로 가기")
            // 메뉴 정보 보여줌.
            menuList[boardSelection- 1].displayMenu()
            // 메뉴 선택 입력.
            var menuSelection: Int = 0
            try {
                menuSelection = readln().toInt()
            } catch (e : java.lang.NumberFormatException)
            {
                // 숫자가 아니면 다시.
                println("숫자를 입력하세요.")
                continue
            }
            if(menuSelection == 0)
            {
                break
            }
            if(menuSelection !in 1 .. menuList[boardSelection - 1].getMenuSize())
            {
                // 1 ~ 메뉴 보드에 등재된 메뉴 리스트 사이즈가 아니면 다시.
                println("유효하지 않은 번호입니다.")
                continue
            }
            if(menuList[boardSelection - 1].getMenu(menuSelection).isSoldOut())
            {
                println("해당 상품은 품절되었습니다. 다시 선택하세요")
                continue
            }
            inputMenuCount(boardSelection, menuSelection)
            // 끝.
            break
        }
    }
    private fun inputMenuCount(boardSelection: Int, menuSelection: Int)
    {
        val selectedMenu = menuList[boardSelection - 1].getMenu(menuSelection)
        while (true)
        {
            selectedMenu.displayInfo();
            val availableCount = selectedMenu.getStoreCount().coerceAtMost(selectedMenu.getOrderCountLimit())
            println("해당 메뉴를 몇 개 주문하시겠습니까? 뒤로 가려면 음수 또는 0을 입력하세여. (최대 주문 갯수: $availableCount)")
            var orderCount: Int = 0
            try {
                orderCount = readln().toInt()
            } catch (e : java.lang.NumberFormatException)
            {
                println("숫자를 입력해 주세여.")
                continue
            }
            if(orderCount <= 0)
            {
                println("프로그램으로 돌아갑니다...")
                continue
            }
            if(orderCount > availableCount)
            {
                println("최대 주문 갯수를 초과하였습니다. 다시 입력해주세여.")
                continue
            }
            cart.addProduct(selectedMenu, orderCount)
            println("${selectedMenu.getName()} ${orderCount}개를 장바구니에 추가하였습니다.")
            break
        }
    }
}