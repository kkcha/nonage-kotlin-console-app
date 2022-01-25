package screen

import LINE_DIVIDER
import data.CartItems
import extensions.getNotEmptyString

class ShoppingCart : Screen() {
    private val products = CartItems.products

    fun showCartItems() {
        // 이 기능에 진입했다고 판단하여 스택에 저장
        ScreenStack.push(this)

        if (products.isNotEmpty()) {
            println(
                products.keys.joinToString (
                    separator = ", \n",
                    prefix = """
                    $LINE_DIVIDER
                    장바구니에 담은 상품 목록입니다.
                    
                    """.trimIndent()
                ) { product ->
                    "카테고리: ${product.categotyLabel} / 상품명: ${product.name} / 수량: ${products[product]}"
                }
            )
        } else {
            println("""
                장바구니에 담긴 상품이 없습니다.
            """.trimIndent())
        }
        showPreviousScreenOption()
    }

    private fun showPreviousScreenOption() {
        println(
            """
                $LINE_DIVIDER
                이전 화면으로 돌아가시겠습니까? (y/n)
            """.trimIndent()
        )
        when (readLine().getNotEmptyString()) {
            "y" -> {
                moveToPreviousScreen()
            }
            "n" -> {
                showCartItems()
            }
            else -> {
                // TODO 재입력 요청
            }
        }
    }

    // 이전 화면으로 이동
    private fun moveToPreviousScreen() {
        // 현재 화면에서 벗어남
        ScreenStack.pop()

        // 이전 화면에 이동했을 때 진입했을 때와 동일한 동작을 해야하므로
        // 이전 화면에 대한 참조필요
        when (val previousScreen = ScreenStack.peek()) {
            is ShoppingCategory -> {
                previousScreen.showCategories()
            }
            is ShoppingProductList -> {
                previousScreen.showProducts()  // selectedCategory 필요
            }
            is ShoppingCart, is ShoppingHome -> {
                // 아무 것도 하지 않음
            }
        }
    }
}
