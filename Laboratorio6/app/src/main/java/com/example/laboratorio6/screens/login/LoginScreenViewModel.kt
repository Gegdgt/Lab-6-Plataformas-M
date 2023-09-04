package com.example.laboratorio6.screens.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    fun signInWithEmailAndPassword(email: String, password: String, Gallery: ()-> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
                        Log.d("Lab6", "Se ha logueado")
                        Gallery()
                    }
                    else{
                        Log.d("Lab6", "signInWithEmailAndPassword: ${task.result.toString()}")

                    }
                }
        }
        catch (ex:Exception){
            Log.d("Lab6", "signInWithEmailAndPassword: ${ex.message}")
        }
    }
}