package ademar.study.test.mapper

import ademar.study.test.core.model.HelloWorld
import ademar.study.test.core.model.StandardErrors
import ademar.study.test.model.HelloWorldViewModel
import javax.inject.Inject

class HelloWorldMapper @Inject constructor(

        private val standardErrors: StandardErrors

) {

    fun transform(helloWorld: HelloWorld): HelloWorldViewModel {
        return HelloWorldViewModel(helloWorld.message ?: standardErrors.UNKNOWN.message)
    }

}