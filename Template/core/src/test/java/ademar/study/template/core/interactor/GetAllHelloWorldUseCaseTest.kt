package ademar.study.template.core.interactor

import ademar.study.template.core.repository.HelloWorldRepository
import ademar.study.template.core.test.BaseTest
import ademar.study.template.core.test.Fixture
import io.reactivex.Observable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class GetAllHelloWorldUseCaseTest : BaseTest() {

    @Mock lateinit var mockHelloWorldRepository: HelloWorldRepository

    @Test
    fun testExecute_success() {
        val useCase = GetAllHelloWorldUseCase(mockHelloWorldRepository)
        val mockHellos = listOf(Fixture.helloWorld.makeModel())

        whenever(mockHelloWorldRepository.getAllHelloWorld()).thenReturn(Observable.just(mockHellos))

        useCase.execute()
                .test()
                .assertResult(mockHellos)
                .assertNoErrors()

        verify(mockHelloWorldRepository).getAllHelloWorld()
        verifyNoMoreInteractions(mockHelloWorldRepository)
    }

    @Test
    fun testExecute_error() {
        val useCase = GetAllHelloWorldUseCase(mockHelloWorldRepository)
        val mockHelloWorldError = Fixture.error.makeModel()

        whenever(mockHelloWorldRepository.getAllHelloWorld()).thenReturn(Observable.error(mockHelloWorldError))

        useCase.execute()
                .test()
                .assertError(mockHelloWorldError)

        verify(mockHelloWorldRepository).getAllHelloWorld()
        verifyNoMoreInteractions(mockHelloWorldRepository)
    }

}
