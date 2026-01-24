package com.example.androidhostapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidhostapp.ui.theme.AndroidhostappTheme
import com.example.androidhostapp.ui.theme.BlueDivider
import com.example.androidhostapp.ui.theme.DarkNavy
import com.example.androidhostapp.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    onSubmit: (email: String, password: String) -> Unit = { _, _ -> },
    onSignUpClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Sign In",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.White
                    )
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(BlueDivider)
                )
            }
        },
        containerColor = Color.White
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Email field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("E-mail", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = LightGray,
                        focusedBorderColor = DarkNavy,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password field
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Password", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = LightGray,
                        focusedBorderColor = DarkNavy,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Sign up text
                val annotatedText = buildAnnotatedString {
                    append("Don't have an account? ")
                    pushStringAnnotation(tag = "SIGNUP", annotation = "signup")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Sign up")
                    }
                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "SIGNUP", start = offset, end = offset)
                            .firstOrNull()?.let {
                                onSignUpClick()
                            }
                    }
                )
            }

            // Submit button at bottom
            Button(
                onClick = { onSubmit(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkNavy
                )
            ) {
                Text(
                    text = "SUBMIT",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    AndroidhostappTheme {
        SignInScreen()
    }
}
