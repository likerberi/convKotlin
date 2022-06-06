
class Controller(model: Model) {
    private val model: Model
    init {
        this.model = model
    }

    val number_end = 3
    fun startBaseBall() {
        val answers: MutableList<String> = ArrayList()
        model.generateAnswers(answers)
        var allInputsCorrect = false
        while (!allInputsCorrect) {
            val inputs: List<String> = InputView.getInputNumber()
            allInputsCorrect = isAllInputsCorrect(inputs, answers)
        }
    }

    private fun isAllInputsCorrect(inputs: List<String>, answers: MutableList<String>): Boolean {
        val ballCount: Int = model.getBallCount(inputs, answers)
        val strikeCount: Int = model.getStrikeCount(inputs, answers)
        ResultView.showResult(ballCount, strikeCount)
        if (strikeCount != number_end) {
            return false
        }
        if (ResultView.isNewGameStart()) {
            answers.clear()
            model.generateAnswers(answers)
            return false
        }
        return true
    }
}