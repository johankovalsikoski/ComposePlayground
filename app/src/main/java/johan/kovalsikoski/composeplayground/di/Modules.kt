package johan.kovalsikoski.composeplayground.di

import johan.kovalsikoski.composeplayground.ui.feature.first_screen.FirstScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val mainViewModelModule = module {
    viewModel { FirstScreenViewModel() }
}

val viewModelModules = mainViewModelModule