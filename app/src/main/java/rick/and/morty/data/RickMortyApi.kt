package rick.and.morty.data

import retrofit2.http.GET
import rick.and.morty.data.datasource.dto.Result

interface RickMortyApi {

     @GET("character")
     suspend fun getCharacters(): Result
}