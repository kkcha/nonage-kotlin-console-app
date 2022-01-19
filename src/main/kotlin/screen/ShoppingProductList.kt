package screen

import data.Product

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
            for(index in 0 until productSize) {
                println("${index}.${categoryProducts[index].name}")
            }
        } else {
            showEmptyProductMessage(selectedCategoty)
        }
    }

    private fun showEmptyProductMessage(selectedCategoty: String) {
        println("[$selectedCategoty] 카테고리 상품이 등록되기 전입니다.")
    }
}