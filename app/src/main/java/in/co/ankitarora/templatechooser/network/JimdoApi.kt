package `in`.co.ankitarora.templatechooser.network

import `in`.co.ankitarora.templatechooser.kotlin_data.TemplateDetails
import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url
import java.util.concurrent.Executors

interface JimdoApi {
    @GET("api/published_designs")
    fun getPublishedTemplateList(): Observable<List<String>>

    @GET
    @Headers("Accept:application/json")
    fun getDataPerDesign(@Url path: String): Observable<TemplateDetails>
}


//@SuppressLint("CheckResult")
//fun main() {
//    getAllTemplates().subscribeOn(Schedulers.trampoline()).observeOn(Schedulers.trampoline()).doOnComplete {
//        Log.d("OnNext", "knknk")
//    }.doOnNext {
//        Log.d("OnNext", "knknk")
//    }.subscribe {
//        Log.d("adasd", "asdsa")
//    }
//}

@SuppressLint("CheckResult")
fun getAllTemplates(): Observable<List<TemplateDetails>> {
    val templatesObservable = PublishSubject.create<List<TemplateDetails>>()
    RestClient()
        .getRetrofitClient().create(JimdoApi::class.java).getPublishedTemplateList().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
        .doOnError {
            templatesObservable.onError(it)
        }
        .subscribe { listOfTemplates ->
            val listOfTemplateObservables = mutableListOf<Observable<TemplateDetails>>()
            listOfTemplates.map {
                listOfTemplateObservables.add(
                    RestClient().getRetrofitClient().create(JimdoApi::class.java).getDataPerDesign(
                        it
                    )
                )
            }
            val mutableListOfTemplates = mutableListOf<TemplateDetails>()
            var counter = 0
            val resultObservable = Observable.merge(listOfTemplateObservables)
            resultObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.from(Executors.newFixedThreadPool(40))).doOnError {
                templatesObservable.onError(it)
            }.doOnComplete {
                templatesObservable.onComplete()
            }.subscribe {
                counter++;
                mutableListOfTemplates.add(it)
                if (counter == listOfTemplateObservables.size) {
                    templatesObservable.onNext(mutableListOfTemplates.toList())
                }
            }
        }
    return templatesObservable
}