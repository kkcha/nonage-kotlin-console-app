package screen

import data.CartItems
import data.Product
import extensions.getNotEmptyInt
import extensions.getNotEmptyString

class ShoppingProductList {
    private val products = arrayOf(
        Product("패션", "겨울 패딩"),
        Product("패션", "겨울 바지"),
        Product("전자기기", "핸드폰"),
        Product("전자기기", "블루투스 이어폰"),
        Product("전자기기", "노트북"),
        Product("반려동물용품", "건식 사료"),
        Product("반려동물용품", "습식 사료"),
        Product("반려동물용품", "치약"),
        Product("반려동물용품", "간식")
    )

    // 배열에서 제공하는 groupBy 연산을 사용하여 해당 카테고리 상품을 쉽게 조회
    private val categories: Map<String, List<Product>> = products.groupBy{
            product -> product.categotyLabel
    }

    // 상품 정보 표시
    // 사용자가 입력한 카테고리 정보를 받아 해당 카테고리의 상품을 출력
    fun showProducts(selectedCategoty: String) {
        val categoryProducts = categories[selectedCategoty]
        if (!categoryProducts.isNullOrEmpty()) {
            println("""
                ***====================================================***
                선택하신 [$selectedCategoty] 카테고리 상품입니다.
            """.trimIndent())
            val productSize = categoryProducts.size
            /*
            for(index in 0 until productSize) {
                println("${index}.${categoryProducts[index].name}")
            }
             */
            categoryProducts.forEachIndexed { index, product->
                println("${index}. ${product.name}")

            }
            showCartOption(categoryProducts, selectedCategoty)
        } else {
            showEmptyProductMessage(selectedCategoty)
        }
    }

    private fun showCartOption(categoryProducts: List<Product>, selectedCategoty: String) {
        println(
            """
            ***=====================================================***
            장바구니에 담을 상품 번호를 선택해주세요.
            """.trimIndent()
        )

        val selectedIndex = readLine().getNotEmptyInt()
        categoryProducts.getOrNull(selectedIndex)?.let { product ->
            CartItems.addProduct(product)
            println("=> 장바구니로 이동하시려면 #을, 계속 쇼핑하시려면 *를 입력해주세요.")
            val answer = readLine().getNotEmptyString()
            if (answer == "#") {
                val shoppingCart = ShoppingCart()
                shoppingCart.showCartItems()
            } else if (answer == "*") {
                showProducts(selectedCategoty)
            } else {
                // TODO 그 외 값을 입력한 경우에 대한 처리
            }
        }
    }

    private fun showEmptyProductMessage(selectedCategoty: String) {
        println("[$selectedCategoty] 카테고리 상품이 등록되기 전입니다.")
    }
}