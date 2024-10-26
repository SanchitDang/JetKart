package com.sanapplications.jetkart.domain.model

import androidx.lifecycle.ViewModel
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    private var userName: String? = null
    private var userEmail: String? = null

    init {
        checkAuthStatus()
        loadUserData() // Load user data if available
    }

    private fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
            userEmail = auth.currentUser?.email // Get the user's email from Firebase
        }
    }

    fun signIn(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userEmail = email // Save email on successful login
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signUp(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Fields can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userEmail = email // Save email on successful signup
                    userName = "name"   // Save name on successful signup
                    saveUserData("name", email) // Save user data to SharedPreferences
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signOut() {
        auth.signOut()
        clearUserData() // Clear user data on sign out
        _authState.value = AuthState.Unauthenticated
    }

    // Save user data in SharedPreferences
    private fun saveUserData(name: String, email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("USER_NAME", name)
        editor.putString("USER_EMAIL", email)
        editor.apply() // or editor.commit()
    }

    // Load user data from SharedPreferences
    private fun loadUserData() {
        userName = sharedPreferences.getString("USER_NAME", null)
        userEmail = sharedPreferences.getString("USER_EMAIL", null)
    }

    // Clear user data from SharedPreferences
    private fun clearUserData() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun getUserData(): Pair<String?, String?> {
        return Pair(userName, userEmail)
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}

