package rick.and.morty.domain.usecases

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import rick.and.morty.data.datasource.dto.Character
import rick.and.morty.data.repository.RickMortyRepository
import rick.and.morty.utils.Response
import java.io.IOException
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository:RickMortyRepository
) {
    operator fun invoke (): Flow<Response<List<Character>>> = flow {
        try {
            emit(Response.Loading<List<Character>>())
            val list = repository.getCharacters().results
            Log.i("CHARACTER",""+list)
            emit(Response.Success<List<Character>>(list))
        }catch (e : HttpException){
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }catch (e : IOException){
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
    }
}