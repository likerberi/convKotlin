import java.util.*
import java.util.function.Consumer
import kotlin.collections.HashSet

class Model {
    val zero = 0
    val number_end = 3
    val number_max = 8

    fun generateAnswers(answers: MutableList<String>) {
        val random = Random()
        val answersSet: MutableSet<Int> = HashSet()
        collectNumbers(random, answersSet)
        answersSet.forEach(Consumer { answer: Int -> answers.add(answer.toString()) })
    }

    fun getStrikeCount(inputs: List<String>, answers: List<String>): Int {
        var count: Int = zero
        for (input in inputs) {
            count += if (isStrike(inputs.indexOf(input), input, answers)) 1 else 0
        }
        return count
    }

    fun getBallCount(inputs: List<String>, answers: List<String>): Int {
        var count: Int = zero
        for (input in inputs) {
            count += if (isBall(inputs.indexOf(input), input, answers)) 1 else 0
        }
        return count
    }

    private fun isStrike(index: Int, input: String, answers: List<String>): Boolean {
        return input == answers[index]
    }

    private fun isBall(index: Int, input: String, answers: List<String>): Boolean {
        return input != answers[index] && answers.contains(input)
    }

    private fun collectNumbers(random: Random, answersSet: MutableSet<Int>) {
        while (answersSet.size != number_end) {
            answersSet.add(random.nextInt(number_max) + 1)
        }
    }
}