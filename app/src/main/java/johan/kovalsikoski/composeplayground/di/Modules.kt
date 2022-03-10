package johan.kovalsikoski.composeplayground.di

import johan.kovalsikoski.composeplayground.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val mainViewModelModule = module {
    viewModel { MainViewModel() }
}

val viewModelModules = mainViewModelModule