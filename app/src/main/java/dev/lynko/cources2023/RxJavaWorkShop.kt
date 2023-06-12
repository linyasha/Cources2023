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
import dev.lynko.cources2023.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.*
//import androidx.lifecycle.lifecycleScope
import kotlin.random.Random

class RxJavaWorkShop : AppCompatActivity() {

    private var TAG = "MainActivity"

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //TODO 1 По нажатию на кнопку button, необходимо вызывать метод startRSStream
    }

    private fun startRStream() {

        //TODO 2 Реализуйте метод getObservable, который должен возвращать Observable<String>.
        // Observable должен испускать 5 чисел от 5 до 1.
//        val myObservable = getObservable()

        //TODO 3 Реализуйте метод getObserver, который должен возвращать Observer<String>.
        // В методе создайте и верните анонимный объект Observer(object : Observer<String>) и переопределите
        // необходимые методы. В каждом переопределенном методе выводите Toast с названием переопределенного метода.
        // В методе onNext, Toast должен выводить число, которое пришло из Observable.
//        val myObserver = getObserver()

        //TODO 4 Имея myObservable и myObserver, произведите подписку(subscribe). Observable должен выполняться
        // в фоновом потоке, а обработка значений Observer в Main потоке.

    }

//    private fun getObserver() {
//        return object :
//    }

//    private fun getObservable() {
//        return Observable.
//    }

}
