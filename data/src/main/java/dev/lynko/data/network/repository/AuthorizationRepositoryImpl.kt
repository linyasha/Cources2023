package dev.lynko.data.network.repository

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dev.lynko.data.network.model.NetworkResult
import dev.lynko.domain.models.Status
import dev.lynko.domain.models.UserCreds
import dev.lynko.domain.repository.AuthorizationRepository
import kotlinx.coroutines.tasks.await

class AuthorizationRepositoryImpl(private val firebaseAuth: FirebaseAuth): AuthorizationRepository {

    override suspend fun login(userCreds: UserCreds): Status {
        val result = sendRequestToFirebaseAuth(AuthStatus.LOGIN, userCreds)
        //TODO("Save to local database e.x. userRepository.save())
        return result.status
    }

    override suspend fun registration(userCreds: UserCreds): Status {
        val result = sendRequestToFirebaseAuth(AuthStatus.CREATE, userCreds)
        //TODO("Save to local database)
        return result.status
    }

    private suspend fun sendRequestToFirebaseAuth(authType: AuthStatus, userCreds: UserCreds): NetworkResult {
        var authResult: AuthResult? = null
        val status = try {
            var status = false
            var errorMessage: String? = null
            authResult = (when(authType) {
                AuthStatus.LOGIN -> firebaseAuth.signInWithEmailAndPassword(
                    userCreds.email,
                    userCreds.password
                )
                AuthStatus.CREATE -> firebaseAuth.createUserWithEmailAndPassword(
                    userCreds.email,
                    userCreds.password
                )
            }).addOnCompleteListener {
                status = true
            }.addOnFailureListener {
                errorMessage = it.message
            }.addOnCanceledListener {
                errorMessage = Status.STATUS_CANCELED
            }.await()
            Log.d(TAG, "${authType.name} result user: ${authResult.user}  ")
            Status(status, errorMessage)
        } catch (e: Exception) {
            Status(
                isSuccess = false,
                e.message
            )
        }
        return NetworkResult(
            status = status,
            authResult = authResult
        )
    }

    companion object {
        const val TAG = "AuthorizationRepository"
    }

    enum class AuthStatus {
        LOGIN,
        CREATE
    }
}