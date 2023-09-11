package tech.fika.pokedex.local.database

import tech.fika.pokedex.local.database.shared.schema

class DefaultDatabaseFactory(
    private val sqlDriverFactory: SqlDriverFactory,
) : DatabaseFactory<PokemonDatabase> {
    override fun create(): PokemonDatabase = PokemonDatabase(
        driver = sqlDriverFactory.create(PokemonDatabase::class.schema, "Database"),
    )
}
