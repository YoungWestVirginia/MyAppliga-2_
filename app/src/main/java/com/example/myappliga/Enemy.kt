package com.example.myappliga

import kotlin.random.Random

class Enemy(name: String, hp: Int, attack: Int, defense: Int) : Character(name, hp, attack, defense), CharacterActions {

    private val random = Random(System.currentTimeMillis())

    fun makeRandomMove(target: Character) {
        when (random.nextInt(3)) { // 0: attack, 1: defend, 2: heal
            0 -> attack(target)
            1 -> defend()
            2 -> heal()
        }
    }
}
