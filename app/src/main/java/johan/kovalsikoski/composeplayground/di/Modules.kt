package johan.kovalsikoski.composeplayground.di

import johan.kovalsikoski.composeplayground.ui.feature.first_screen.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val mainViewModelModule = module {
    viewModel { HomeScreenViewModel() }
}

val viewModelModules = mainViewModelModule