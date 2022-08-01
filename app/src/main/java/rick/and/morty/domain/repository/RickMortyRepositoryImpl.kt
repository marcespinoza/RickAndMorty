package rick.and.morty.domain.repository

import rick.and.morty.data.RickMortyApi
import rick.and.morty.data.datasource.dto.Result
import rick.and.morty.data.repository.RickMortyRepository
import javax.inject.Inject

class RickMortyRepositoryImpl @Inject constructor(
    private val api:RickMortyApi
):RickMortyRepository {
    override suspend fun getCharacters(): Result {
        return api.getCharacters()
    }
}