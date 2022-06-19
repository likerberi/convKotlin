import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class Ball(val round: Int, val ball: Int) {
    enum class BallStatus {
        BALL, NOTHING, STRIKE
    }

    companion object {
        const val BALLS_COUNT = 3
        fun inputStringToBalls(inputString: String): List<Ball> {
            val inputNumberList = Arrays.stream(inputString.split("".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()).map { s: String -> s.toInt() }.collect(Collectors.toList())

            /* 유효성 체크 */
            ballsValidateCheck(inputNumberList)
            return inputListToBalls(inputNumberList)
        }

        private fun ballsValidateCheck(inputNumberList: List<Int>) {
            /* 숫자가 3개가 아니거나 중복된 숫자가 있을 경우, 숫자 0이 있을 경우 */
            require(
                !(inputNumberList.stream().distinct().count() != BALLS_COUNT.toLong() || inputNumberList.contains(0))
            )
        }

        fun inputListToBalls(inputNumberList: List<Int?>): List<Ball> {
            val ballList: MutableList<Ball> = ArrayList()
            for (i in inputNumberList.indices) {
                ballList.add(Ball(i + 1, inputNumberList[i]))
            }
            return ballList
        }
    }
}