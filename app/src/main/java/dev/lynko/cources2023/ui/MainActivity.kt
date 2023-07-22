package dev.lynko.cources2023.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toolbar
import kotlin.random.Random
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import dev.lynko.cources2023.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
//TODO("Wifi localhost")
//private const val ENDPOINT = "http://192.168.100.3:3000"



//https://api.nasa.gov/planetary/apod?api_key=zUTcPX0Ac65Zo8gLnVv7qwimBbz67fwo1k7WINXw
private const val ENDPOINT = "https://api.nasa.gov"
private const val BOOKS_URI = "/planetary/apod?"
private const val TITLE = "title"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            button.setOnClickListener {
                val book = editText.text
                GlobalScope.launch {
                    addBook(book.toString())
                }
            }
        }
        GlobalScope.launch(Dispatchers.IO) {
            getBooksAndShowIt()
        }

    }

    suspend fun getBooksAndShowIt() {
        val httpUrlConnection = URL(ENDPOINT + BOOKS_URI).openConnection() as HttpURLConnection
        httpUrlConnection.apply {
            connectTimeout = 10000 // 10 seconds
            requestMethod = "GET"
            doInput = true
        }
        if (httpUrlConnection.responseCode != HttpURLConnection.HTTP_OK) {
            withContext(Dispatchers.Main) {
                //Toast.makeText
            }
            return
        }
        val streamReader = InputStreamReader(httpUrlConnection.inputStream)
        var text: String = ""
        streamReader.use {
            text = it.readText()
        }
        val books = mutableListOf<String>()
        val json = JSONObject(text)
        val title = json.getString(TITLE)
//        for (i in 0 until json.length()) {
//            val jsonBook = json.getJSONObject(i)
//            val title = jsonBook.getString(TITLE)
//            books.add(title)
//        }
        httpUrlConnection.disconnect()

        withContext(Dispatchers.Main) {
            binding.textView.text = title
        }
    }

    suspend fun addBook(book: String) {
        val httpUrlConnection = URL(ENDPOINT + BOOKS_URI).openConnection() as HttpURLConnection
        val body = JSONObject().apply {
            put(TITLE, book)
            put(TITLE, book)
            put(TITLE, book)
            put(TITLE, book)
            put(TITLE, book)
        }
        httpUrlConnection.apply {
            connectTimeout = 10000 // 10 seconds
            requestMethod = "POST"
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
        }
        OutputStreamWriter(httpUrlConnection.outputStream).use {
            it.write(body.toString())
        }
        httpUrlConnection.responseCode
        if (httpUrlConnection.responseCode != HttpURLConnection.HTTP_OK) {

        } else {
            httpUrlConnection.disconnect()
            getBooksAndShowIt()
        }
    }
}