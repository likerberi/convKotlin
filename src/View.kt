import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class View {

    var MATCHING_TARGET_NUMBER = 3
    var MATCHING_START_NUMBER = 0

    @Throws(IOException::class)
    fun InputGameStartOrEnd(): Int {
        println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
        val br = BufferedReader(InputStreamReader(System.`in`))
        return br.readLine().toInt()
    }

    companion object {
        /* 숫자 값 입력 */
        @Throws(IOException::class)
        fun inputNumber(): String {
            print("숫자를 입력해 주세요 : ")
            val br = BufferedReader(InputStreamReader(System.`in`))
            return br.readLine()
        }
    }

    fun GameResultMessage(ball: Int, strike: Int) {
        /* 결과 조합 */
        val resultStringBuilder = StringBuilder()
        if (strike == MATCHING_TARGET_NUMBER) {
            resultStringBuilder.append("3개의 숫자를 모두 맞히셨습니다! 게임 종료")
        }
        if (ball == MATCHING_START_NUMBER && strike == MATCHING_START_NUMBER) {
            resultStringBuilder.append("nothin'")
        }
        if (ball > MATCHING_START_NUMBER) {
            resultStringBuilder.append(ball)
            resultStringBuilder.append("볼 ")
        }
        if (strike > MATCHING_START_NUMBER && strike < MATCHING_TARGET_NUMBER) {
            resultStringBuilder.append(strike)
            resultStringBuilder.append("스트라이크 ")
        }
        println(resultStringBuilder.toString())
    }
}