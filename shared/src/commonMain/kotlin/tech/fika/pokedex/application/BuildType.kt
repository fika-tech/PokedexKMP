package tech.fika.pokedex.application

sealed interface BuildType {
    data object Develop : BuildType
    data class Mock(val host: String) : BuildType
    data object Staging : BuildType
    data object Production : BuildType
}
