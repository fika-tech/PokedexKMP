package tech.fika.pokedex.data.mapper

import tech.fika.pokedex.local.database.PokemonTable
import tech.fika.pokedex.entities.Pokemon
import tech.fika.pokedex.entities.PokemonType
import tech.fika.pokedex.remote.models.PokemonApiModel

object PokemonMapper {
    fun toEntity(apiModel: PokemonApiModel): Pokemon = Pokemon(
        id = apiModel.id,
        name = apiModel.name,
        type = apiModel.types.firstOrNull()?.type?.name.let(PokemonType::fromName),
        height = apiModel.height,
        weight = apiModel.weight,
        imageUrl = apiModel.sprites.other.home.frontDefault,
        thumbnailUrl = apiModel.sprites.frontDefault,
    )

    fun toEntity(dbModel: PokemonTable): Pokemon = Pokemon(
        id = dbModel.id.toInt(),
        name = dbModel.name,
        type = dbModel.type.let(PokemonType::fromName),
        height = dbModel.height.toInt(),
        weight = dbModel.weight.toInt(),
        imageUrl = dbModel.imageUrl,
        thumbnailUrl = dbModel.thumbnailUrl,
    )

    fun toDbModel(apiModel: PokemonApiModel): PokemonTable = PokemonTable(
        id = apiModel.id.toLong(),
        name = apiModel.name,
        type = apiModel.types.first().type.name,
        height = apiModel.height.toLong(),
        weight = apiModel.weight.toLong(),
        imageUrl = apiModel.sprites.other.home.frontDefault,
        thumbnailUrl = apiModel.sprites.frontDefault,
    )
}