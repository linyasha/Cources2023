package dev.lynko.cources2023.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import dev.lynko.cources2023.databinding.ActivityAuthBinding
import dev.lynko.cources2023.ui.model.ValidateState
import dev.lynko.cources2023.ui.viewModel.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {

    lateinit var binding: ActivityAuthBinding

    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        initObservers()
    }

    private fun initObservers() {
        authViewModel.state.observe(this) { status ->
            with(binding) {
                when(status) {
                    ValidateState.SUCCESS -> {
                        //TODO("Show progress dialog")
                    }
                    ValidateState.FAIL -> {
                        Toast.makeText(this@AuthActivity, "Check data!", Toast.LENGTH_SHORT).show()
                    }
                    ValidateState.DEFAULT -> {}
                }
            }
        }
        lifecycleScope.launch {
            authViewModel.authState.collectLatest { status ->
                if (status.isSuccess) {
                    startActivity(Intent(this@AuthActivity, AnimalsActivity::class.java))
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AuthActivity, "Network Error: ${status.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initUi() {
        with(binding) {
            loginBtn.setOnClickListener {
                authViewModel.login(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            signInBtn.setOnClickListener {
                authViewModel.signUp(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
     }
}