import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class InputView {
    /* 게임 시작 종료 선택 */
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
}