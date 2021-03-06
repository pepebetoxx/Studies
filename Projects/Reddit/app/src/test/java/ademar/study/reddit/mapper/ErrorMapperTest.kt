package ademar.study.reddit.mapper

import ademar.study.reddit.test.BaseTest
import ademar.study.reddit.test.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.Mockito.`when` as whenever

class ErrorMapperTest : BaseTest() {

    @Test
    fun testTransform() {
        val mapper = ErrorMapper()
        val viewModel = mapper.transform(Fixture.error.makeModel())
        assertThat(viewModel.code).isEqualTo(Fixture.error.CODE)
        assertThat(viewModel.message).isEqualTo(Fixture.error.MESSAGE)
    }

}
