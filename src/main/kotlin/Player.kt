class Player {
    var score: String = "love"

    fun score() {
        score = when (score) {
            "love" -> "15"
            "15" -> "30"
            "30" -> "40"
            "40" -> "love"
            else -> {
                "Error occurred"
            }
        }
    }
}

