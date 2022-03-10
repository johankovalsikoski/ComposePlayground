package johan.kovalsikoski.composeplayground

import android.app.Application
import johan.kovalsikoski.composeplayground.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ProjectApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ProjectApplication)
            modules(viewModelModules)
        }
    }
}