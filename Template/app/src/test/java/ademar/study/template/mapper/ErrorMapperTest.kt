package ademar.study.template.mapper

import ademar.study.template.test.BaseTest
import ademar.study.template.test.Fixture
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ErrorMapperTest : BaseTest() {

    @Test
    fun testTransform() {
        val mockError = Fixture.error.makeModel()
        whenever(mockStandardErrors.toError(mockError)).thenReturn(mockError)

        val mapper = ErrorMapper(mockStandardErrors)
        val viewModel = mapper.transform(mockError)
        assertThat(viewModel.code).isEqualTo(Fixture.error.CODE)
        assertThat(viewModel.message).isEqualTo(Fixture.error.MESSAGE)
    }

}
