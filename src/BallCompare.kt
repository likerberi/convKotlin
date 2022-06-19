import Ball.BallStatus
import java.util.*

class BallCompare {
    fun ballCompareResult(answerBallList: List<Ball>, userBallList: List<Ball>): BallStatus {
        val ballStatusList: MutableList<BallStatus?> = ArrayList()
        for (userBall in userBallList) {
            ballStatusList.add(compareBalls(userBall, answerBallList))
        }
        val strike = Collections.frequency(ballStatusList, BallStatus.STRIKE)
        val ball = Collections.frequency(ballStatusList, BallStatus.BALL)
        return BallStatus(strike, ball)
    }

    companion object {
        fun compareBall(answerBall: Ball, userBall: Ball): BallStatus {
            if (answerBall.round == userBall.round && answerBall.ball == userBall.ball) {
                return BallStatus.STRIKE
            }
            if (answerBall.ball == userBall.ball)
                return BallStatus.BALL
            return BallStatus.NOTHING
        }

        fun compareBalls(userBall: Ball, answerBallList: List<Ball>): BallStatus {
            var returnBallStatus = BallStatus.NOTHING
            var index = 0

            /* Strike 또는 Ball 이면 반복문 중단하고 return */
            while (returnBallStatus == BallStatus.NOTHING && index < 3) {
                returnBallStatus = compareBall(userBall, answerBallList[index])
                index++
            }
            return returnBallStatus
        }
    }
}