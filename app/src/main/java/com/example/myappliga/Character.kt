package com.example.myappliga

open class Character(val name: String, var hp: Int, val attack: Int, var defense: Int) {

    open fun attack(target: Character) {
        val damage = (this.attack - target.defense).coerceAtLeast(0)
        target.hp -= damage
        println("${this.name} attacks ${target.name} for $damage damage!")
    }

    open fun defend() {
        println("${this.name} defends! Defense increased temporarily.")
        defense += 5
    }

    open fun heal() {
        val healingAmount = (10..20).random()
        this.hp += healingAmount
        println("${this.name} heals for $healingAmount HP!")
    }

    fun isAlive(): Boolean {
        return this.hp > 0
    }
}
