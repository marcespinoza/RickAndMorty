package rick.and.morty.ui

import rick.and.morty.data.datasource.dto.Character

data class RickMortiListState(
    val isLoading : Boolean = false,
    val characterList : List<Character> = emptyList(),
    val error : String = ""
)