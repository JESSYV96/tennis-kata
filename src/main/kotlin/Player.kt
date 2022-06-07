import java.util.*
class Player {
    var name: String
        set(value) {
            field = value.replaceFirstChar {
                if (it.isLowerCase())
                    it.titlecase(Locale.getDefault())
                else it.toString()
            }
        }
    var point: Int = 0
    var game: Int = 0
    var status: PlayerStatus = PlayerStatus.Game

    constructor(playerName: String) {
        name = playerName
    }

    fun score() {
       when (point) {
            0 -> {
                point = TennisRule.score.getValue("15")
            }
            1 -> {
                point = TennisRule.score.getValue("30")
            }
            2 -> {
                point = TennisRule.score.getValue("40")
            }
            3 -> lastPoint()
            else -> {
               throw Exception("Error occurred by score")
            }
        }
    }

    private fun lastPoint() {
        when (status) {
            PlayerStatus.Game -> {
                if (point == 3) {
                    status = PlayerStatus.WinGame
                    game += 1
                }
            }
            else -> {

            }
        }


    }
}

