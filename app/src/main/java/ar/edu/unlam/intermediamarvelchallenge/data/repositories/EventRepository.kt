package ar.edu.unlam.intermediamarvelchallenge.data.repositories

import ar.edu.unlam.intermediamarvelchallenge.data.models.NetResult
import ar.edu.unlam.intermediamarvelchallenge.data.services.ComicsResponse
import ar.edu.unlam.intermediamarvelchallenge.data.services.EventResponse
import ar.edu.unlam.intermediamarvelchallenge.data.services.EventService

class EventRepository(
    private val eventService: EventService
) : BaseRepository() {
    suspend fun getEvents(offset: Int, limit: Int = 25): NetResult<EventResponse> =
        handleResult(eventService.getEvents(authParams.getMap(), offset, limit))
    suspend fun getComicsByIdEvent (id:String): NetResult<ComicsResponse> =
        handleResult(eventService.getComicsByIdEvent(id,authParams.getMap()))
}