package rick.and.morty.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import rick.and.morty.data.repository.RickMortyRepository
import rick.and.morty.domain.usecases.CharactersUseCase
import rick.and.morty.utils.Response
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase
): ViewModel() {
    private val characterValue = MutableStateFlow(RickMortiListState())
    val _characterValue : StateFlow<RickMortiListState> = characterValue

    fun getAllCharactersData() = viewModelScope.launch (Dispatchers.IO){

        charactersUseCase().collect {
            when(it){
                is Response.Success->{
                    characterValue.value = RickMortiListState(characterList = it.data?: emptyList())
                    Log.d("toCharacter",_characterValue.value.toString())
                }
                is Response.Loading->{
                    characterValue.value = RickMortiListState(isLoading = true)
                    Log.d("toCharacter", it.data.toString())
                }
                is Response.Error->{
                    characterValue.value = RickMortiListState(error = it.message?: "Unexpected error")
                    Log.d("toCharacter",it.data.toString())
                }
            }
        }
    }
}