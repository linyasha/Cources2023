package dev.lynko.cources2023

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import com.pawegio.kandroid.defaultSharedPreferences
import kotlinx.coroutines.*
import java.io.File
import kotlin.coroutines.coroutineContext

class MainActivity2 : AppCompatActivity() {
    
    

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val prefs = defaultSharedPreferences
        Log.d(TAG, "onCreate: ${prefs.getInt(KEY, 0)}")
        prefs.edit { 
            putInt(KEY, VALUE)
        }

        
        val scope = CoroutineScope(Job())
        scope.launch {
            val fileName = "myFileUseFile"
//            val fileData = "My file"
//            val file = File(this@MainActivity2.filesDir, fileName)
//            file.writeBytes(fileData.toByteArray())
//
            val fileName1 = "myFileUseStream"
//            val file1Data = "My file 1"
//            this@MainActivity2.openFileOutput(fileName1, Context.MODE_PRIVATE).use {
//                it.write(file1Data.toByteArray())
//            }

//            this@MainActivity2.fileList().forEach {
//                Log.d(TAG, "onCreate: ${it} ")
//            }
//
//            this@MainActivity2.openFileInput(fileName1).bufferedReader().useLines { lines ->
//                lines.fold("") { some, text ->
//                    Log.d(TAG, "onCreate: ${"$some\n$text"} ")
//                    "$some\n$text"
//                }
//            }

        }
    }

    companion object {
        const val TAG = "MAIN_AC"
        
        const val KEY = "SOME_KEY"
        const val VALUE = 12
    }

}