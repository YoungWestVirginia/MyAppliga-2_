package com.example.myappliga

class Game(private val hero: Hero, private val enemy: Enemy) : GameActions {

    private val maxRounds = 6 // Limiting the game to 6 rounds

    fun playGame() {
        println("Game start! ${hero.name} vs ${enemy.name}")

        var round = 0

        while (hero.isAlive() && enemy.isAlive() && round < maxRounds) {
            println("Round ${round + 1}:")

            // Hero's turn
            val heroAction = (1..3).random()
            when (heroAction) {
                1 -> hero.attack(enemy)
                2 -> hero.defend()
                3 -> hero.heal()
            }

            if (!enemy.isAlive()) {
                gameOver(hero)
                return
            }

            // Enemy's turn
            enemy.makeRandomMove(hero)

            if (!hero.isAlive()) {
                gameOver(enemy)
                return
            }

            round++
        }

        if (round >= maxRounds) {
            println("Game over! It's a draw after $maxRounds rounds!")
        }
    }

    private fun gameOver(winner: Character) {
        println("Game over! ${winner.name} wins!")
    }
}
