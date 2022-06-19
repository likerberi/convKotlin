class BallStatus(strikeCount: Int, ballCount: Int) {
    private var strikeCount = strikeCount
    private var ballCount = ballCount

    fun BallStatus(strikeCount: Int, ballCount: Int) {
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