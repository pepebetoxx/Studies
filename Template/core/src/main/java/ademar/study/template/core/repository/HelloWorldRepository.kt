package ademar.study.template.core.repository

import ademar.study.template.core.ext.observeBody
import ademar.study.template.core.model.HelloWorld
import ademar.study.template.core.repository.datasource.HelloWorldCloudRepository
import ademar.study.template.core.repository.datasource.HelloWorldLocalRepository
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class HelloWorldRepository @Inject constructor(

        private val retrofit: Retrofit,
        private val cloud: HelloWorldCloudRepository,
        private val local: HelloWorldLocalRepository

) {

    fun getHelloWorld(): Observable<HelloWorld> {
        val cached = local.helloWorld
        return if (cached != null) {
            Observable.just(cached)
        } else {
            cloud.getHelloWorld()
                    .flatMap { retrofit.observeBody(it) }
                    .doOnNext { local.helloWorld = it }
        }
    }

    fun getAllHelloWorld(): Observable<List<HelloWorld>> {
        val cached = local.hellos
        return if (cached != null) {
            Observable.just(cached)
        } else {
            cloud.getAllHelloWorld()
                    .flatMap { retrofit.observeBody(it) }
                    .doOnNext { local.hellos = it }
        }
    }

}
