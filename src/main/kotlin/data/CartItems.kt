package data

object CartItems {
    // 프로젝트 전역에서 참조하므로 항상 같은 데이터를 유지
    // 상품 데이터를 저장할 변수 선언
    private val mutableProducts = mutableMapOf<Product, Int>() // 외부에서 수정할 수 없도록 제한
    val products: Map<Product, Int> = mutableProducts

    fun addProduct(product: Product) {
        mutableProducts[product]?.let {
            mutableProducts[product] = it + 1
        } ?: kotlin.run {
            mutableProducts[product] = 1
        }
    }
}