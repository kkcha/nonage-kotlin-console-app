package extensions

// 카테고리명 입력시 사용
fun String?.getNotEmptyString(): String {
    var input = this // this는 Nullable String
    while(input.isNullOrBlank()) {
        println("값을 입력해 주세요")
        input = readLine()
    }
    return input.trim()
}

// 사용자에게 번호를 입력 받을 때 int 타입으로 변환할 수 있는 값인지 확인
fun String?.getNotEmptyInt(): Int {
    var input = this?.trim()
    while(input.isNullOrEmpty() || input.toIntOrNull() == null) {
        println("값을 입력해 주세요")
        input = readLine()
    }
    return input.toInt()
}