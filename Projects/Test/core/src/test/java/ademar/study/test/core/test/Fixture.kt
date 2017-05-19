package ademar.study.test.core.test

import ademar.study.test.core.model.Error
import ademar.study.test.core.model.HelloWorld

object Fixture {

    object error {

        val CODE = 1
        val MESSAGE = "Some error"
        val JSON = """
        {
            "code": $CODE,
            "message": "$MESSAGE"
        }
        """

        fun makeException(): Throwable {
            return Exception("Some error")
        }

        fun makeModel(): Error {
            val model = Error()
            model.code = CODE
            model.message = MESSAGE
            return model
        }

    }

    object hellos {

        val JSON = """
        [${helloWorld.JSON}]
        """

        fun makeModel(): List<HelloWorld> {
            return listOf(helloWorld.makeModel())
        }

    }

    object helloWorld {

        val MESSAGE = "Hello World!"
        val JSON = """
        {
            "message": "$MESSAGE"
        }
        """

        fun makeModel(): HelloWorld {
            val model = HelloWorld()
            model.message = MESSAGE
            return model
        }

    }

}
