package com.sanapplications.jetkart.domain.model

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import com.sanapplications.jetkart.presentation.graphs.auth_graph.AuthScreen

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private val sharedPreferences: SharedPreferences = application
        .getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

    // MutableState properties for user data
    var userUid by mutableStateOf("")
        private set
    var userFirstName by mutableStateOf("")
        private set
    var userLastName by mutableStateOf("")
        private set
    var userEmail by mutableStateOf("")
        private set
    var userPhoneNumber by mutableStateOf("")
        private set
    var userAddress by mutableStateOf("")
        private set

    init {
        checkAuthStatus()
        loadUserData() // Load user data if available
    }

    private fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
            userEmail = auth.currentUser!!.email.toString() // Get the user's email from Firebase
        }
    }

    fun signIn(email: String, password: String, navController: NavController) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email or password can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    getUserFromDb(auth.currentUser?.uid.toString()) { user ->
                        saveUserData(
                            user!!.uid, user.email, user.firstName, user.lastName,
                            user.phoneNumber, user.address
                        )
                        _authState.value = AuthState.Authenticated
                        navController.navigate(AuthScreen.SignInSuccess.route)
                    }
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signUp(email: String, password: String, firstName: String, lastName: String, phoneNumber: String, address: String, navController: NavController) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Fields can't be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveUserToDb(auth.currentUser?.uid.toString(), email, firstName, lastName,
                        phoneNumber, address)
                    saveUserData(auth.currentUser?.uid.toString(), email, firstName, lastName,
                        phoneNumber, address)
                    _authState.value = AuthState.Authenticated
                    navController.navigate(AuthScreen.SignUpSuccess.route)
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
    private fun saveUserData(uid: String, email: String, firstName: String, lastName: String, phoneNumber: String, address: String) {
        val editor = sharedPreferences.edit()
        editor.putString("USER_UID", uid)
        editor.putString("USER_FIRST_NAME", firstName)
        editor.putString("USER_LAST_NAME", lastName)
        editor.putString("USER_EMAIL", email)
        editor.putString("USER_PHONE_NUMBER", phoneNumber)
        editor.putString("USER_ADDRESS", address)
        editor.apply() // or editor.commit()
    }

    // Load user data from SharedPreferences
    private fun loadUserData() {
        userUid = sharedPreferences.getString("USER_UID", "")!!
        userFirstName = sharedPreferences.getString("USER_FIRST_NAME", "")!!
        userLastName = sharedPreferences.getString("USER_LAST_NAME", "")!!
        userEmail = sharedPreferences.getString("USER_EMAIL", "")!!
        userPhoneNumber = sharedPreferences.getString("USER_PHONE_NUMBER", "")!!
        userAddress = sharedPreferences.getString("USER_ADDRESS", "")!!
    }

    // Clear user data from SharedPreferences
    private fun clearUserData() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    // Function to save user data to Firestore
    private fun saveUserToDb(documentId: String, email: String, firstName: String, lastName: String, phoneNumber: String, address: String) {
        val userMap = hashMapOf(
            "uid" to documentId,
            "email" to email,
            "firstName" to firstName,
            "lastName" to lastName,
            "phoneNumber" to phoneNumber,
            "address" to address
        )

        firestore.collection("users")
            .document(documentId)
            .set(userMap)
            .addOnSuccessListener {
                // Successfully written to Firestore
                // You can handle any success operations here if needed
            }
            .addOnFailureListener { e ->
                // Handle the error
                _authState.value = AuthState.Error("Error saving user data: ${e.message}")
            }
    }

    // Function to update user data in Firestore
    fun updateUserInDb() {
        // Create a map from the ViewModel properties
        val userMap = mutableMapOf<String, Any>().apply {
            if (userEmail.isNotEmpty()) put("email", userEmail)
            if (userFirstName.isNotEmpty()) put("firstName", userFirstName)
            if (userLastName.isNotEmpty()) put("lastName", userLastName)
            if (userPhoneNumber.isNotEmpty()) put("phoneNumber", userPhoneNumber)
            if (userAddress.isNotEmpty()) put("address", userAddress)
        }

        // Update the Firestore document
        firestore.collection("users")
            .document(userUid)
            .set(userMap, SetOptions.merge()) // Use merge to update only specified fields
            .addOnSuccessListener {
                // Successfully updated the Firestore document
                // Handle success if needed
                saveUserData(userUid, userEmail, userFirstName,
                    userLastName, userPhoneNumber, userAddress)
            }
            .addOnFailureListener { e ->
                // Handle the error
                _authState.value = AuthState.Error("Error updating user data: ${e.message}")
            }
    }

    // Function to retrieve user data from Firestore
    private fun getUserFromDb(documentId: String, onResult: (User?) -> Unit) {
        firestore.collection("users")
            .document(documentId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = document.toObject<User>()
                    onResult(user)
                } else {
                    onResult(null) // Document does not exist
                }
            }
            .addOnFailureListener { e ->
                _authState.value = AuthState.Error("Error retrieving user data: ${e.message}")
                onResult(null) // Handle the error and return null
            }
    }

    // Functions to update each property
    fun updateFirstName(newFirstName: String) {
        userFirstName = newFirstName
    }
    fun updateLastName(newLastName: String) {
        userLastName = newLastName
    }
    fun updateEmail(newEmail: String) {
        userEmail = newEmail
    }
    fun updatePhoneNumber(newPhoneNumber: String) {
        userPhoneNumber = newPhoneNumber
    }
    fun updateAddress(newAddress: String) {
        userAddress = newAddress
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}

data class User(
    val uid: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val address: String = ""
)
