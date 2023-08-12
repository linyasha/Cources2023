package dev.lynko.cources2023.ui.activity

import android.Manifest
import android.content.ContentUris
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.pawegio.kandroid.defaultSharedPreferences
import dev.lynko.cources2023.Prefs
import dev.lynko.cources2023.R
import dev.lynko.cources2023.databinding.ActivityMain2Binding
import kotlinx.coroutines.launch
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                0
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                0
            )
        }
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )

        val calendar = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, -1) //
        }
        val millisYesterday = calendar.timeInMillis

        Log.d("TEST_CONTENT", "onCreate: $calendar")
        Log.d("TEST_CONTENT", "onCreate: $millisYesterday")

        val selections = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
        val selectionsArgs = arrayOf(millisYesterday.toString())
        val selectionsOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"



        //@Query("SELECT _id, displayName FROM mediaImagesTable(uri) WHERE dateCreated >= ORDER BY dateCreated DESC")
        val images = mutableListOf<Image>()
        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selections,
            selectionsArgs,
            selectionsOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val uri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                images.add(Image(id, name, uri))
            }
        }
        lifecycleScope.launch {
            Glide.with(this@MainActivity).load(images.first().uri).centerCrop().into(binding.photo)
        }
        defaultSharedPreferences.getString(Prefs.KEY_THEME, "")
    }

    data class Image(
        val id: Long,
        val name: String,
        val uri: Uri
    )
}