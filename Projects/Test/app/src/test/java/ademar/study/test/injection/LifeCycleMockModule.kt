package ademar.study.test.injection

import ademar.study.test.view.base.BaseActivity
import dagger.Module
import dagger.Provides
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@Module
class LifeCycleMockModule {

    @Mock lateinit var mockBaseActivity: BaseActivity

    init {
        MockitoAnnotations.initMocks(this)
    }

    @Provides
    @LifeCycleScope
    fun provideBaseActivity() = mockBaseActivity

}
