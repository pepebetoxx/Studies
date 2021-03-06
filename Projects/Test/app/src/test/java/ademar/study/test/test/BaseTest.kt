package ademar.study.test.test

import ademar.study.test.core.R
import ademar.study.test.core.injection.CoreModule
import ademar.study.test.core.injection.DaggerCoreComponent
import ademar.study.test.core.model.Error
import ademar.study.test.core.model.StandardErrors
import ademar.study.test.injection.DaggerLifeCycleMockComponent
import ademar.study.test.injection.LifeCycleMockModule
import android.content.Context
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.fail
import org.junit.After
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    lateinit var lifeCycleMockModule: LifeCycleMockModule
    lateinit var mockErrorUnknown: Error
    lateinit var mockErrorUnauthorized: Error

    @Mock lateinit var mockContext: Context
    @Mock lateinit var mockCoreModule: CoreModule
    @Mock lateinit var mockStandardErrors: StandardErrors

    private var rxError: Throwable? = null

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
        rxError = null

        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            Schedulers.trampoline()
        }
        RxJavaPlugins.setIoSchedulerHandler {
            Schedulers.trampoline()
        }
        RxJavaPlugins.setErrorHandler { error ->
            println("Received error $error stacktrace:")
            error.printStackTrace()
            rxError = error
        }

        lifeCycleMockModule = LifeCycleMockModule()
        val coreComponent = DaggerCoreComponent.builder()
                .coreModule(mockCoreModule)
                .build()
        DaggerLifeCycleMockComponent.builder()
                .lifeCycleMockModule(lifeCycleMockModule)
                .coreComponent(coreComponent)
                .build()

        mockErrorUnknown = Error().apply {
            code = 1
            message = "Mock Error Unknown"
        }
        mockErrorUnauthorized = Error().apply {
            code = 2
            message = "Mock Error Unauthorized"
        }

        whenever(mockContext.getString(R.string.error_message_unknown)).thenReturn("UNKNOWN")
        whenever(mockContext.getString(R.string.error_message_unauthorized)).thenReturn("UNAUTHORIZED")

        whenever(mockStandardErrors.UNKNOWN).thenReturn(mockErrorUnknown)
        whenever(mockStandardErrors.UNAUTHORIZED).thenReturn(mockErrorUnauthorized)
    }

    @After
    open fun tearDown() {
        if (rxError != null) {
            fail("Error on rx: $rxError")
        }
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

}
