package com.example.myappliga

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var textHero: TextView
    private lateinit var textEnemy: TextView
    private lateinit var textGameStatus: TextView
    private lateinit var buttonAttack: Button
    private lateinit var buttonDefend: Button
    private lateinit var buttonHeal: Button
    private lateinit var buttonStartGame: Button

    private lateinit var hero: Hero
    private lateinit var enemy: Enemy
    private lateinit var game: Game

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        textHero = findViewById(R.id.textHero)
        textEnemy = findViewById(R.id.textEnemy)
        textGameStatus = findViewById(R.id.textGameStatus)
        buttonAttack = findViewById(R.id.buttonAttack)
        buttonDefend = findViewById(R.id.buttonDefend)
        buttonHeal = findViewById(R.id.buttonHeal)
        buttonStartGame = findViewById(R.id.buttonStartGame)

        // Initialize characters and game
        hero = Hero(name = "ONANAY", hp = 200, attack = 20, defense = 10)
        enemy = Enemy(name = "BOYET", hp = 200, attack = 20, defense = 10)
        game = Game(hero, enemy)

        // Set up button click listeners
        buttonAttack.setOnClickListener { performHeroAction { hero.attack(enemy) } }
        buttonDefend.setOnClickListener { performHeroAction { hero.defend() } }
        buttonHeal.setOnClickListener { performHeroAction { hero.heal() } }
        buttonStartGame.setOnClickListener { startGame() }
    }

    private fun startGame() {
        textGameStatus.text = "Game Started! Make your move."
        game.playGame()
        updateUI()
    }

    private fun performHeroAction(action: () -> Unit) {
        action()
        if (!enemy.isAlive() || !hero.isAlive()) {
            textGameStatus.text = "${if (hero.isAlive()) hero.name else enemy.name} wins!"
            return
        }
        enemy.makeRandomMove(hero)
        updateUI()
        if (!hero.isAlive()) {
            textGameStatus.text = "${enemy.name} wins!"
        } else if (!enemy.isAlive()) {
            textGameStatus.text = "${hero.name} wins!"
        }
    }

    private fun updateUI() {
        textHero.text = "Hero: HP: ${hero.hp}, DEF: ${hero.defense}, ATK: ${hero.attack}"
        textEnemy.text = "Enemy: HP: ${enemy.hp}, DEF: ${enemy.defense}, ATK: ${enemy.attack}"
    }
}
