package tech.fika.pokedex.local.database

import app.cash.sqldelight.Transacter

fun interface DatabaseFactory<T : Transacter> {
    fun create(): T
}
