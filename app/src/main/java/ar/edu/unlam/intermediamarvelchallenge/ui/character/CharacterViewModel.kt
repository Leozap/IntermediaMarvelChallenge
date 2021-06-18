package ar.edu.unlam.intermediamarvelchallenge.ui.character

import ar.edu.unlam.intermediamarvelchallenge.data.models.NetResult
import ar.edu.unlam.intermediamarvelchallenge.data.repositories.CharacterRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.intermediamarvelchallenge.data.models.Character
import ar.edu.unlam.intermediamarvelchallenge.utils.Status
import kotlinx.coroutines.launch

class CharacterViewModel(private val charactersRepository: CharacterRepository) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val state=MutableLiveData<Status>()
    val characters: LiveData<List<Character>> get() = _characters

    init {
        loadCharacters(0)
    }

    private fun loadCharacters(offset: Int) {
        viewModelScope.launch {
            when (val response = charactersRepository.getCharacters(offset)) {
                is NetResult.Success -> {
                    _characters.postValue(response.data.charactersList.characters)
                    state.value=Status.SUCCESS
                }
                is NetResult.Error -> {
                    state.value=Status.ERROR
                }
            }
        }
    }

    fun loadMoreCharacters(moreCharacters: Int) {
        viewModelScope.launch {
            when(val response= charactersRepository.getCharacters(moreCharacters)){
                is NetResult.Success -> {
                    _characters.postValue(response.data.charactersList.characters)
                    state.value=Status.SUCCESS
                } is NetResult.Error -> {
                state.value=Status.ERROR
            }
            }

        }

    }

}