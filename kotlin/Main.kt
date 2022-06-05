import java.util.*

//class Main() {
    fun main(args: Array<String>) {
        var tmp = mutableSetOf<Int>()
        while (tmp.size < 3) {
            tmp.add(Random().nextInt(9))
        }

        val correctAnswer = tmp.toString().toList().filter { it.isDigit() }
        //println("답: $correctAnswer")

        var count = 0
        var answer: List<Char>? = null

        while (count < 9) {
            do {
                answer = readLine()?.toList()?.distinct()
            } while (answer!!.size > 3 || (answer!!.count { it.isDigit() }) < 3)

            var score = mutableListOf<Int>(0, 0, 0)    // 0: s, 1: b, 2: o

            if (correctAnswer.equals(answer)) break

            correctAnswer.forEachIndexed { index, c ->
                val p = answer!!.indexOf(c)
                when {
                    p == index -> ++score[0]
                    p != -1 -> ++score[1]
                    else -> ++score[2]
                }
            }

            println("${count + 1}회말: ${score[0]} strike / ${score[1]} ball / ${score[2]} out")
            count++
        }


        when (count) {
            9 -> println("${count}회말 Game Over GG")
            else -> println("정답입니다.")
        }
    }
//}