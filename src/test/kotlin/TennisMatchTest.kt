
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class TennisMatchTest {

    @Nested
    inner class GameStartingTest {
        @Test
        fun `A tennis match starts with two players`() {
            val match = TennisMatch("Jojo", "Frank")
            val expectedValue = 2
            Assertions.assertEquals(expectedValue, match.players.size)
        }

        @Test
        fun `When tennis match starts Raphael and Roger should have 0 point`() {
            val match = TennisMatch("Louis", "Billy")
            val expectedValue = "Score: Louis(0) 0 vs Billy(0) 0."
            Assertions.assertEquals(expectedValue, match.printCurrentScore(match.getPlayer(1), match.getPlayer(2)))
        }

        @Test
        fun `When tennis match starts Jean and Willy should have 0 point`() {
            val match = TennisMatch("Jean", "Willy")
            val expectedValue = "Score: Jean(0) 0 vs Willy(0) 0."
            Assertions.assertEquals(expectedValue, match.printCurrentScore(match.getPlayer(1), match.getPlayer(2)))
        }

        @Test
        fun `Triangulation - When tennis match starts Max and Bob should have 0 point`() {
            val match = TennisMatch("Max", "Bob")
            val expectedValue = "Score: Max(0) 0 vs Bob(0) 0."
            Assertions.assertEquals(expectedValue, match.printCurrentScore(match.getPlayer(1), match.getPlayer(2)))
        }
    }

    @Nested
    inner class GameTest {
        private lateinit var match: TennisMatch

        @BeforeEach
        fun setUp() {
            match = TennisMatch("Raphael", "Roger")
        }

        @Test
        fun `When Raphael (player 1) scores against Roger (player 2) it should return 15 - 0 for Raphael`() {
            val expectedValue = "Score: Raphael(0) 15 vs Roger(0) 0."

            match.getPlayer(1).score()

            Assertions.assertEquals(expectedValue, match.printCurrentScore(match.getPlayer(1), match.getPlayer(2)))
        }

        @Test
        fun `When Roger (player 2) scores against Raphael (player 1) it should return 15 - 0 for Roger`() {
            val expectedValue = "Score: Raphael(0) 0 vs Roger(0) 15."

            match.getPlayer(2).score()

            Assertions.assertEquals(expectedValue, match.printCurrentScore(match.getPlayer(1), match.getPlayer(2)))
        }

        @Test
        fun `When Raphael (player 1) and Roger (player 2) score twice it should return 30 - 30 for Roger`() {
            val expectedValue = "Score: Raphael(0) 30 vs Roger(0) 30."

            match.getPlayer(1).score()
            match.getPlayer(1).score()

            match.getPlayer(2).score()
            match.getPlayer(2).score()

            Assertions.assertEquals(expectedValue, match.printCurrentScore(match.getPlayer(1), match.getPlayer(2)))
        }

        @Test
        fun `When Raphael (player 1) scores when the current score is 40 - 0 it should return 0 - 0 and Raphael win a game`() {
            val expectedValue = "Score: Raphael(1) 0 vs Roger(0) 0."

            match.getPlayer(1).score() // 15 - 0
            match.getPlayer(1).score()
            match.getPlayer(1).score()
            match.getPlayer(1).score()

            Assertions.assertEquals(expectedValue, match.printCurrentScore(match.getPlayer(1), match.getPlayer(2)))
        }

        @Test
        fun `Raphael (player 1) scores when the current score is 30 - 40 it should the game should say Deuce`() {
            val expectedValue = "Deuce ! 40 - 40."

            match.getPlayer(1).score() // 15 - 0
            match.getPlayer(2).score() // 15 - 15
            match.getPlayer(2).score() // 15 - 30
            match.getPlayer(2).score() // 15 - 40
            match.getPlayer(1).score() // 30 - 40
            match.getPlayer(1).score() // Deuce


            Assertions.assertEquals(expectedValue, match.printCurrentScore(match.getPlayer(1), match.getPlayer(2)))
        }

        @Test
        fun `Roger (player 2) scores when the current score is 40 - 30 it should the game should say Deuce`() {
            val expectedValue = "Deuce ! 40 - 40."

            match.getPlayer(1).score() // 15 - 0
            match.getPlayer(2).score() // 15 - 15
            match.getPlayer(2).score() // 15 - 30
            match.getPlayer(1).score() // 30 - 30
            match.getPlayer(1).score() // 40 - 30
            match.getPlayer(2).score() // Deuce


            Assertions.assertEquals(expectedValue, match.printCurrentScore(match.getPlayer(1), match.getPlayer(2)))
        }

        @Test
        fun `When the game is deuce, the next point should give advantage for the scorer`() {
            val expectedValue = "Advantage for Roger"

            match.getPlayer(1).score() // 15 - 0
            match.getPlayer(2).score() // 15 - 15
            match.getPlayer(2).score() // 15 - 30
            match.getPlayer(1).score() // 30 - 30
            match.getPlayer(1).score() // 40 - 30
            match.getPlayer(2).score() // Deuce
            match.getPlayer(2).score()

            Assertions.assertEquals(expectedValue, match.printCurrentScore(match.getPlayer(1), match.getPlayer(2)))
        }
    }
}