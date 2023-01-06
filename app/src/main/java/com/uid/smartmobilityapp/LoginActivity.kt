package com.uid.smartmobilityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.uid.smartmobilityapp.ui.company.CompanyViewModel
import com.uid.smartmobilityapp.ui.flexible_intent.FlexibleIntentViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: CompanyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = CompanyViewModel
    }

    fun logInClick(view: View) {
        val usernameInput: TextView = findViewById<TextView>( R.id.username_input_id )
        val usernameError: TextView = findViewById<TextView>( R.id.username_error_id )
        val passwordInput: TextView = findViewById<TextView>( R.id.password_input_id )
        val passwordError: TextView = findViewById<TextView>( R.id.password_error_id )
        val signInMessage: TextView = findViewById<TextView>( R.id.signinMessage )
        var isUsernameValid = false
        var isPasswordValid = false

        if(usernameInput.text.isEmpty()) {
            usernameError.text = getString(R.string.username_error_string)
        } else {
            if(usernameInput.text.length < 3) {
                usernameError.text = getString(R.string.username_short_error_string)
            } else {
                usernameError.text = ""
                isUsernameValid = true
            }
        }

        if(passwordInput.text.isEmpty()) {
            passwordError.text = getString(R.string.password_error_string)
        } else {
            if(passwordInput.text.length < 7) {
                passwordError.text = getString(R.string.password_short_error_string)
            } else {
                passwordError.text = ""
                isPasswordValid = true
            }
        }

        if(isUsernameValid and isPasswordValid) {
            if(usernameInput.text.contentEquals( "username") and passwordInput.text.contentEquals("password")) {
                signInMessage.text = getString(R.string.authentication_success_string)
                signInMessage.setTextColor(getColor(R.color.purple_700));

                val intent = Intent ( this, UserActivity::class.java )
                startActivity ( intent )

            } else if(validateCompany(usernameInput.text.toString(), passwordInput.text.toString())) {
                signInMessage.text = getString(R.string.authentication_success_string)
                signInMessage.setTextColor(getColor(R.color.purple_700));

                val intent = Intent ( this, CompanyActivity::class.java )
                startActivity ( intent )

            }
            else {
                signInMessage.text = getString(R.string.authentication_failed_string)
                signInMessage.setTextColor(getColor(R.color.red));
            }
        } else {
            signInMessage.text = ""
        }
    }

    private fun validateCompany(username: String, password: String): Boolean {
        for(company in viewModel.companies.value!!) {
            if(company.username.contentEquals(username) && company.password.contentEquals(password)) {
                viewModel.loggedInUsername.value = company.username
                viewModel.loggedInEmail.value = company.email
                return true
            }
        }
        return false
    }
}