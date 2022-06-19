import java.io.IOException
import View

class BaseballGame {
    var Views = View()
    val GAME_FINISH_STRIKE_COUNT = 3
    @Throws(IOException::class)
    fun GamePlaying() {

        /* 컴퓨터(정답) 랜덤한 수 생성 */
        val createAnswerNumber = CreateAnswerNumber()
        val answerBallList: List<Ball?> = Ball.inputListToBalls(createAnswerNumber.CreateRandomNumberList())
        var clearFlag = false
        while (!clearFlag) {
            try {
                /* 사용자 랜덤한 수 입력 */
                val userBallList: List<Ball?> = Ball.inputStringToBalls(View.inputNumber())
                clearFlag = GameResultCheckAndReturnClearFlag(answerBallList, userBallList)
            } catch (e: IllegalArgumentException) {
                println("서로 다른 수로 이루어진 3자리의 수를 입력해주세요")
            }
        }
    }

    @get:Throws(IOException::class)
    val isGameReplayChoice: Int
        get() {
            val inputView = View()
            return inputView.InputGameStartOrEnd()
        }

    /* 게임 결과 확인 (숫자 비교, 결과 값 도출) */
    fun GameResultCheckAndReturnClearFlag(answerBallList: List<Ball?>, userBallList: List<Ball?>): Boolean {
        val ballCompare = BallCompare()
        val ballStatusResult: BallStatus = ballCompare.ballCompareResult(answerBallList, userBallList)
        val ballCount: Int = ballStatusResult.getBallCount()
        val strikeCount: Int = ballStatusResult.getStrikeCount()
        Views.GameResultMessage(ballCount, strikeCount)
        return (strikeCount == GAME_FINISH_STRIKE_COUNT)
    }
}