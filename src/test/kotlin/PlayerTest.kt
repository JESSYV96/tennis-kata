
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Nested
    inner class ScoringTest {
        private lateinit var player: Player
        private lateinit var player2: Player

        @BeforeEach
        fun setUp() {
            player = Player("Tom")
            player2 = Player("Chicken Georges")
        }

        @Test
        fun `Player must start a match without point`() {
            val expectedValue = "love"
            assertEquals(expectedValue, player.point)
        }

        @Test
        fun `A player start a game with 0 game`() {
            val expectedValue = 0
            assertEquals(expectedValue, player.game)
        }

        @Test
        fun `When a player score a first point (from love) he should return 15`() {
            val expectedValue = "15"
            player.score()
            assertEquals(expectedValue, player.point)
        }

        @Test
        fun `When a player score a second point (from 15) he should return 30`() {
            val expectedValue = "30"
            player.score()
            player.score()
            assertEquals(expectedValue, player.point)
        }

        @Test
        fun `When a player score a third point (from 30) he should return 40`() {
            val expectedValue = "40"
            player.score()
            player.score()
            player.score()
            assertEquals(expectedValue, player.point)
        }

        @Test
        fun `When a player score a fourth point (from 40) he should return love`() {
            val expectedValue = "love"
            player.score()
            player.score()
            player.score()
            player.score()
            assertEquals(expectedValue, player.point)
        }

        @Test
        fun `When a player has scored 40, then score again he wins the game`() {
            val expectedValue = 1
            player.score()
            player.score()
            player.score()
            player.score()
            assertEquals(expectedValue, player.game)
        }
    }

}