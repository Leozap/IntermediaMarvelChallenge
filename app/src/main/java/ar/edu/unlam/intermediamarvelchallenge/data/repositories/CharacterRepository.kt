package ar.edu.unlam.intermediamarvelchallenge.data.repositories

import ar.edu.unlam.intermediamarvelchallenge.data.models.Character
import ar.edu.unlam.intermediamarvelchallenge.data.models.NetResult
import ar.edu.unlam.intermediamarvelchallenge.data.services.CharacterService
import ar.edu.unlam.intermediamarvelchallenge.data.services.CharactersResponse

class CharacterRepository(
        private val characterService: CharacterService
    ): BaseRepository() {

        suspend fun getCharacters(offset: Int, limit: Int = 15): NetResult<CharactersResponse> =
            handleResult(characterService.getCharacters(authParams.getMap(), offset, limit))

    suspend fun getCharacterDetail(id:Int,offset:Int=20): NetResult<CharactersResponse> =
        handleResult(characterService.getCharacterDetail(id,authParams.getMap(),offset))
}
