package dev.lynko.cources2023.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {

        } else {
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        initObservers()
        askNotificationPermission()
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

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {

            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}