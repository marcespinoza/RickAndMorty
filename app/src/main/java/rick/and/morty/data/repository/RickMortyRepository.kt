package rick.and.morty.data.repository

import rick.and.morty.data.datasource.dto.Result

interface RickMortyRepository {

    suspend fun getCharacters(): Result
}