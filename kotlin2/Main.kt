import java.io.IOException

object Application {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val baseballGame = BaseballGame()
        val GameIsRunning = 1
        var inputGameStartOrEnd = GameIsRunning
        while (inputGameStartOrEnd == GameIsRunning) {
            baseballGame.GamePlaying()
            inputGameStartOrEnd = baseballGame.isGameReplayChoice
        }
    }
}