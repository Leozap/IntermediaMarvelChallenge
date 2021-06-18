package ar.edu.unlam.intermediamarvelchallenge.ui.event


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.edu.unlam.intermediamarvelchallenge.utils.Status
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.intermediamarvelchallenge.data.models.Appearance
import ar.edu.unlam.intermediamarvelchallenge.data.models.Event
import ar.edu.unlam.intermediamarvelchallenge.data.models.NetResult
import ar.edu.unlam.intermediamarvelchallenge.data.repositories.EventRepository
import kotlinx.coroutines.launch

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {
    private val _comic = MutableLiveData<List<Appearance>>()
    private val _event = MutableLiveData<List<Event>>()
    val event: LiveData<List<Event>> get() = _event
    val status = MutableLiveData<Status>()
    val statusEvent = MutableLiveData<Status>()

    init {
        loadEvent(0)
    }

    private fun loadEvent(offset: Int) {
        viewModelScope.launch {
            when (val response = eventRepository.getEvents(offset)) {
                is NetResult.Success -> {
                    _event.postValue(response.data.eventsList.events.sortedBy { it.start })
                    statusEvent.value = Status.SUCCESS
                }
                is NetResult.Error -> {
                    statusEvent.value = Status.ERROR
                }
            }
        }
    }

    fun loadComic(id: String) {
        viewModelScope.launch {
            when (val response = eventRepository.getComicsByIdEvent(id)) {
                is NetResult.Success -> {
                    _comic.postValue(response.data.comicsList.comics)
                    status.value = Status.SUCCESS
                }
                is NetResult.Error -> {
                    status.value = Status.ERROR
                }

            }
        }
    }



}


