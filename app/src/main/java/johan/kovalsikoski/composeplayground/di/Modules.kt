package johan.kovalsikoski.composeplayground.di

import johan.kovalsikoski.composeplayground.ui.feature.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val mainViewModelModule = module {
    viewModel { MainViewModel() }
}

val viewModelModules = mainViewModelModule