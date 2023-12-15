fun main() {
    // 장바구니 더미 DB
    val cartMap = HashMap<Menu, Int>()
    // 잔고 더미 DB
    var clientWallet = 0

    // 메뉴 보드 리스트
    val menuList = arrayOf(MenuBoard("생과일", 1), MenuBoard("주스", 2), MenuBoard("탕후루", 3))

    addMenus(menuList) // 메뉴 추가.

    // 키오스크 전체 프로그램.
    while (true) {
        println(parseForColor("\"과일 가게에 오신 것을 환영합니다.\"", PrintColorCodeEnum.GREEN))

        // 초기 메뉴 표시 및 선택
        println("1. 메뉴 구경하러 가기")
        println("2. 잔액 충전하기")
        println("3. 프로그램 종료")
        val mainMenuSelection = checkReadlnValidation(1, 3)
        if (mainMenuSelection == -1) continue

        when (mainMenuSelection) {
            // 메뉴 구경하러 가기
            1 -> {
                println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.")
                println("[Food Menu]")
                // 메뉴 보드 정보를 보여줌.
                for (menuBoard in menuList) {
                    menuBoard.displayMenuBoardInfo()
                }
                val limit: Int

                if (cartMap.isNotEmpty()) {
                    println("[Order Menu]")
                    println("4. 장바구니 주문")
                    println("5. 장바구니 내역 취소")
                    limit = menuList.size + 2
                } else limit = menuList.size

                // 보드 선택 입력
                val boardSelection = checkReadlnValidation(1, limit)
                if (boardSelection == -1) continue

                when (boardSelection) {
                    1, 2, 3 -> {
                        // 메뉴 선택 프로그램.
                        while (true) {
                            // 메뉴 정보 보여줌.
                            menuList[boardSelection - 1].displayMenu()
                            println("0. 뒤로가기")
                            // 메뉴 선택 입력.

                            val menuSelection = checkReadlnValidation(0, menuList[boardSelection - 1].getMenuSize())
                            if (menuSelection == -1) continue
                            if (menuSelection == 0) break

                            //상세 주문 여부 기능
                            while (true) {
                                //선택한 세부 메뉴 정보 표시
                                val chosenMenu = menuList[boardSelection - 1].getMenus()[menuSelection - 1]
                                println(parseForColor(chosenMenu.displayInfo(), PrintColorCodeEnum.GREEN))
                                println("위 메뉴를 장바구니에 추가하시겠습니까?")
                                println("1. 확인          2. 취소")
                                val putCartSelection = checkReadlnValidation(1, 2)
                                if (putCartSelection == -1) continue
                                if (putCartSelection == 2) break

                                println("${chosenMenu.getName()}가 장바구니에 추가되었습니다.")
                                cartMap[chosenMenu] = 1 + cartMap.getOrDefault(chosenMenu, 0)
                                break
                            }
                        }
                    }
                    // 장바구니 주문
                    4 -> {
                        println("아래와 같이 주문하시겠습니까?\n")
                        println("[Orders]")
                        //카트맵에 들어있는 제품만 필터해서 map으로 꺼내오기
                        cartMap.forEach{
                            println("${it.key.displayInfo()} | ${it.value}개 |")
                        }
                        println()
                        val cartTotalAmount = cartMap.map { menu ->
                            menu.key.getPrice() * menu.value
                        }.fold(0) { total, value -> total + value }
                        println("[Total]")
                        parseForColor("$cartTotalAmount 원", PrintColorCodeEnum.PURPLE)

                        println("1.주문      2.메뉴판")
                        val orderSelection = checkReadlnValidation(1, 2)
                        if (orderSelection == -1) continue
                        if (orderSelection == 2) continue

                        //잔고보다 적을시 뒤로
                        if (cartTotalAmount > clientWallet) {
                            println("현재 잔액은 ${clientWallet}원 으로 ${cartTotalAmount - clientWallet}원이 부족해서 주문할 수 없습니다.")
                            continue
                        }
                        clientWallet -= cartTotalAmount
                        println("주문이 처리되었습니다! 이용해 주셔서 감사합니다.")
                        println("현재 잔고는 $clientWallet 원입니다.")
                        continue
                    }
                    // 장바구니 내역 취소
                    5 -> {
                        println("[장바구니 내역]")
                        val cartList = cartMap.entries.toList()
                        for ((idx, cartEntry) in cartList.withIndex()) {
                            println(
                                parseForColor(
                                    "${idx + 1}. ${cartEntry.key.getName()} : ${cartEntry.value}개",
                                    PrintColorCodeEnum.CYAN
                                )
                            )
                        }
                        println(parseForColor("0. 뒤로가기", PrintColorCodeEnum.CYAN))
                        var cancelSelection: Int
                        while (true) {
                            println("무엇을 취소하시겠습니까?")
                            cancelSelection = checkReadlnValidation(0, cartMap.size)
                            if (cancelSelection == -1) continue
                            if (cancelSelection == 0) break

                            println("몇 개를 취소하시겠습니까? ${cartList[cancelSelection - 1].value}개까지 취소 가능.")
                            val cancelCountSelection = checkReadlnValidation(0, cartList[cancelSelection - 1].value)
                            if (cancelCountSelection == -1) continue
                            if (cancelCountSelection == 0) continue
                            cartMap[cartList[cancelSelection - 1].key] =
                                cartMap[cartList[cancelSelection - 1].key]!! - cancelCountSelection
                            if (cartMap[cartList[cancelSelection - 1].key] == 0) {
                                cartMap.remove(cartList[cancelSelection - 1].key)
                            }
                            println("${cancelCountSelection}개가 취소되었습니다.")
                            break
                        }
                    }
                }
            }
            // 잔액 충전하기
            2 -> {
                println("얼마를 충전하시겠습니까? 최대 100만원까지 입금 가능.")
                val moneyAmountToTransfer = checkReadlnValidation(1, 1000000)
                if (moneyAmountToTransfer == -1) continue
                clientWallet += moneyAmountToTransfer
                println("$moneyAmountToTransfer 이 충전되었습니다.")
                println("현재 잔고는 $clientWallet 입니다.")
                continue
            }
            // 프로그램 종료
            3 -> {
                println("프로그램을 종료합니다.")
                break
            }
        }

    }

}

//리턴값 -1일시 validation 불통과
// 통과시 readln 반환
private fun checkReadlnValidation(startNum: Int, limit: Int): Int {
    val parsedReadlnValue: Int
    try {
        // 보드 선택 입력
        parsedReadlnValue = readln().toInt()
    } catch (e: NumberFormatException) {
        // 숫자가 아니면 다시.  -> lv4 요구사항
        println("숫자 값을 입력하세요.")
        return -1
    }
    if (parsedReadlnValue !in startNum..limit) {
        // 유효값 번호가 아니면 다시.
        println("유효한 값을 넣어주세요.")
        return -1
    }
    return parsedReadlnValue
}

fun addMenus(menuList: Array<MenuBoard>) {
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

fun parseForColor(msg: String, printColorCodeEnum: PrintColorCodeEnum): String {
    return "${printColorCodeEnum.code} $msg ${PrintColorCodeEnum.EXIT.code}"
}

