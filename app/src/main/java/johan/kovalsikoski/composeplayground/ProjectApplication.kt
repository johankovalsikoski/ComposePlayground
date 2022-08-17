package johan.kovalsikoski.composeplayground

import johan.kovalsikoski.composeplayground.di.viewModelModules
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ProjectApplication)
            modules(viewModelModules)
        }
    }
}