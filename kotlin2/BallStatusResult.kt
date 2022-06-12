class BallStatusResult(strikeCounts: Int, ballCounts: Int) {
    private var strikeCount = strikeCounts
    private var ballCount = ballCounts

    fun BallStatusResult(strikeCount: Int, ballCount: Int) {
        this.strikeCount = strikeCount
        this.ballCount = ballCount
    }

    fun getStrikeCount(): Int {
        return strikeCount
    }

    fun getBallCount(): Int {
        return ballCount
    }
}
