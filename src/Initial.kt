class CreateAnswerNumber {
    fun CreateRandomNumberList(): List<Int?> {
        var randomNumberList: MutableList<Int?> = ArrayList()
        while (randomNumberList.size < 3) {
            randomNumberList = RandomNumberList(randomNumberList)
        }
        return randomNumberList
    }

    fun RandomNumberList(randomNumberList: MutableList<Int?>): MutableList<Int?> {
        val randomNumber = RandomNumber()
        if (!randomNumberList.contains(randomNumber)) {
            randomNumberList.add(randomNumber)
        }
        return randomNumberList
    }

    fun RandomNumber(): Int {
        return (Math.random() * (MAX_NUMBER - MIN_NUMBER)).toInt() + MIN_NUMBER
    }

    companion object {
        const val MIN_NUMBER = 1
        const val MAX_NUMBER = 9
    }
}