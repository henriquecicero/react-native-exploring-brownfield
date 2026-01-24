package com.example.androidhostapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.androidhostapp.ui.SignInScreen
import com.example.androidhostapp.ui.theme.AndroidhostappTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidhostappTheme(dynamicColor = false) {
                SignInScreen(
                    onSubmit = { email, password ->
                        // TODO: Handle login
                    },
                    onSignUpClick = {
                        // TODO: Navigate to sign up
                    }
                )
            }
        }
    }
}
