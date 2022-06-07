class TennisMatch(player1name: String, player2name: String) {
    val players: Players = mapOf(
        1 to Player(player1name),
        2 to Player(player2name)
    )
    private var matchStatus: MatchStatus = MatchStatus.GameTime
    get() {
        val player1 = getPlayer(1)
        val player2 = getPlayer(2)
        matchStatus =
            if (player1.status == PlayerStatus.Advantage || player2.status == PlayerStatus.Advantage)
                MatchStatus.AdvantageTime
            else if (player1.point == 3 && player2.point == 3)
                MatchStatus.DeuceTime
            else if (player1.status == PlayerStatus.WinGame || player2.status == PlayerStatus.WinGame)
                MatchStatus.GameWinning
            else
                MatchStatus.GameTime
            return field
    }

    fun getPlayer(playerKey: Int): Player {
        return players.getValue(playerKey)
    }

    fun printCurrentScore(player1: Player, player2: Player): String {
        return when (matchStatus) {
            MatchStatus.DeuceTime -> "Deuce ! ${formatScore(player1.point)} - ${formatScore(player2.point)}."
            MatchStatus.AdvantageTime -> "Advantage for Roger"
            MatchStatus.GameWinning -> {
                resetGame()
                "Score: ${player1.name}(${player1.game}) ${formatScore(player1.point)} " +
                        "vs ${player2.name}(${player2.game}) ${formatScore(player2.point)}."
            }
            else -> "Score: ${player1.name}(${player1.game}) ${formatScore(player1.point)} " +
                    "vs ${player2.name}(${player2.game}) ${formatScore(player2.point)}."

        }
    }

    private fun formatScore(point: Int): String {
        val scoreText = when (point) {
            0 -> "0"
            1 -> "15"
            2 -> "30"
            3 -> "40"
            else -> throw Exception("Format score error")
        }
        return scoreText
    }

    private fun resetGame() {
        resetPlayer(getPlayer(1))
        resetPlayer(getPlayer(2))
    }

    private fun resetPlayer(player: Player) {
        player.point = 0
        player.status = PlayerStatus.Game
    }
}