package dev.lynko.cources2023

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observable.just
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.reactivestreams.Subscriber
import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    /*
    val query = Observable.create { emitter ->
        while (true) {
            Thread.sleep(1000L)
            emitter.onNext(Random.nextInt(0, 15))
//            emitter.onError()
//            emitter.onComplete()
        }
    }
     */

    /*
    val query = Flowable.create({
            emitter ->
        while (true) {
            Thread.sleep(1000L)
            emitter.onNext(Random.nextInt(0, 15))
//            emitter.onError()
//            emitter.onComplete()
        }
    }, BackpressureStrategy.DROP)

     */

    /*
    val query = Single.create {
        it.onSuccess(10)
//        it.onError()
    }

     */

    /*
    val query = Maybe.create {
        it.onSuccess(10)
//        it.onComplete()
//        it.onError()
    }

     */

    /*
    val query = Completable.create {
        it.onComplete()
//        it.onError()
    }

     */

    //Schedulers
    //subscribeOn
    //observeOn
    //Маперы .map .flatMap .


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
//        query.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe {
//            Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
//        }
    }

    companion object {
        val TAG = "d_MainActivity"
    }

}