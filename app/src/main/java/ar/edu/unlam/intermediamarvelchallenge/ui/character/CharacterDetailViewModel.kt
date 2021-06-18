package ar.edu.unlam.intermediamarvelchallenge.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.intermediamarvelchallenge.data.models.Character
import ar.edu.unlam.intermediamarvelchallenge.data.models.NetResult
import ar.edu.unlam.intermediamarvelchallenge.data.repositories.CharacterRepository
import ar.edu.unlam.intermediamarvelchallenge.utils.Status
import kotlinx.coroutines.launch

class CharacterDetailViewModel (private val charactersRepository: CharacterRepository) : ViewModel() {

  val _characters = MutableLiveData<Character>()
    val statusDetail=MutableLiveData<Status>()



     fun loadCharacter(id: Int) {
        viewModelScope.launch {
            when (val response = charactersRepository.getCharacterDetail(id)) {
                is NetResult.Success -> {
                    _characters.postValue(response.data.charactersList.characters[0])
                    statusDetail.value=Status.SUCCESS
                }
                is NetResult.Error -> {
                    statusDetail.value=Status.ERROR
                }
            }
        }
    }

}