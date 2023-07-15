package dev.lynko.data.network.model

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import dev.lynko.domain.models.Status

data class NetworkResult(
    val status: Status,
    val authResult: AuthResult?
)
