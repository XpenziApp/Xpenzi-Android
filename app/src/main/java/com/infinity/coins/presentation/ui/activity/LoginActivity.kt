package com.infinity.coins.presentation.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.infinity.coins.presentation.ui.screen.LoginScreen
import com.infinity.coins.presentation.vm.LoginViewModel
import java.util.concurrent.TimeUnit

class LoginActivity : ComponentActivity() {

    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private var storedVerificationId: String? = ""


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

                when (e) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        // Invalid request
                    }

                    is FirebaseTooManyRequestsException -> {
                        // The SMS quota for the project has been exceeded
                    }

                    is FirebaseAuthMissingActivityForRecaptchaException -> {
                        // reCAPTCHA verification attempted with null Activity
                    }
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
            }
        }

        setContent {
            LoginScreen(
                viewModel = viewModel,
                onValidPhoneNumber = {
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91$it")
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this@LoginActivity)
                        .setCallbacks(callbacks)
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)
                },
                onInvalidPhoneNumber = {

                }
            )
        }
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    companion object {
        private const val TAG = "PhoneAuthActivity"
    }
}