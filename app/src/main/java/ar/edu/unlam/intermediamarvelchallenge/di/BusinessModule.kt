package ar.edu.unlam.intermediamarvelchallenge.di


import ar.edu.unlam.intermediamarvelchallenge.data.repositories.CharacterRepository
import ar.edu.unlam.intermediamarvelchallenge.data.repositories.EventRepository
import ar.edu.unlam.intermediamarvelchallenge.ui.character.CharacterDetailViewModel
import ar.edu.unlam.intermediamarvelchallenge.ui.character.CharacterViewModel
import ar.edu.unlam.intermediamarvelchallenge.ui.event.EventViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val businessModule = module {

    viewModel { CharacterViewModel(get()) }
    single { CharacterRepository(get()) }
    viewModel { EventViewModel(get()) }
    viewModel{CharacterDetailViewModel(get())}
    single { EventRepository(get()) }
}