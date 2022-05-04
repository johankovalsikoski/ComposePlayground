package johan.kovalsikoski.composeplayground.di

import johan.kovalsikoski.composeplayground.ui.feature.host_activity.HostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val hostViewModel = module {
    viewModel { HostViewModel() }
}

val viewModelModules = hostViewModel