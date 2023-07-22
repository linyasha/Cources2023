package dev.lynko.cources2023.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import dev.lynko.cources2023.NasaApiImpl
import dev.lynko.cources2023.databinding.ActivityNasaBinding
import dev.lynko.cources2023.utils.LoadingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NasaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNasaBinding

    private var progressDialog: ProgressDialog? = null

    private lateinit var viewModel: NasaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNasaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)
        viewModel = ViewModelProvider(this, NasaViewModelProviderFactory()).get(NasaViewModel::class.java)
        GlobalScope.launch(Dispatchers.IO) {
           val result = viewModel.getData()
            withContext(Dispatchers.Main) {
                result?.let {
                    with(binding) {
                        title.text = it.title
                        date.text = it.date
                        explanation.text = it.explanation
                        Glide.with(this@NasaActivity)
                            .load(it.url)
                            .into(binding.image)
                    }
                }
            }
        }
        initObservers()
    }

    private fun initObservers() {
        GlobalScope.launch {
            viewModel.state.collectLatest {
                when (it) {
                    LoadingState.LOAGING -> {
                        withContext(Dispatchers.Main) {
                            progressDialog?.show(true)
                        }
                    }
                    LoadingState.SUCCESS -> {
                        withContext(Dispatchers.Main) {
                            progressDialog?.show(false)

                        }
                    }
                    LoadingState.FAIL -> {
                        withContext(Dispatchers.Main) {
                            progressDialog?.show(false)
                            Toast.makeText(this@NasaActivity, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                    LoadingState.DEFAULT -> {}
                }
            }
        }
    }
}