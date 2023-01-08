package com.uid.smartmobilityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.uid.smartmobilityapp.models.CompanyDetails
import com.uid.smartmobilityapp.ui.company.CompanyViewModel
import com.uid.smartmobilityapp.ui.flexible_intent.FlexibleIntentViewModel
import com.uid.smartmobilityapp.ui.flexible_intent.model.FlexibleIntent
import java.util.regex.Pattern

class SigninActivity : AppCompatActivity() {
    private lateinit var progressBar : ProgressBar
    private lateinit var viewModel: CompanyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.INVISIBLE

        viewModel = CompanyViewModel
    }

    fun signupClick(view: View) {
        val usernameInput: TextView = findViewById<TextView>( R.id.username_input_id )
        val usernameError: TextView = findViewById<TextView>( R.id.username_error_id )
        val passwordInput: TextView = findViewById<TextView>( R.id.password_input_id )
        val passwordError: TextView = findViewById<TextView>( R.id.password_error_id )
        val emailInput: TextView = findViewById<TextView>( R.id.email_input_id )
        val emailError: TextView = findViewById<TextView>( R.id.email_error_id )
        val personNameInput: TextView = findViewById<TextView>( R.id.contact_person_name_input_id )
        val personNameError: TextView = findViewById<TextView>( R.id.contact_person_name_error_id )
        val personPhoneInput: TextView = findViewById<TextView>( R.id.contact_person_phone_input_id )
        val personPhoneError: TextView = findViewById<TextView>( R.id.contact_person_phone_error_id )
        val websiteInput: TextView = findViewById<TextView>( R.id.company_website_input_id )
        val websiteError: TextView = findViewById<TextView>( R.id.company_website_error_id )
        val signInMessage: TextView = findViewById<TextView>( R.id.signinMessage )

        val isUsernameValid: Boolean = validateLengthAndEmpty(usernameInput, usernameError) && validateUsername(usernameInput, usernameError)
        val isPasswordValid: Boolean = validateLengthAndEmpty(passwordInput, passwordError)
        val isEmailValid: Boolean =
            validateLengthAndEmpty(emailInput, emailError) && isEmailValid(emailInput, emailError) && validateEmail(emailInput, emailError)
        val isPersonNameValid: Boolean = validateLengthAndEmpty(personNameInput, personNameError)
        val isPersonPhoneValid: Boolean = validateLengthAndEmpty(personPhoneInput, personPhoneError)&& isPhoneValid(personPhoneInput, personPhoneError)
        val isWebsiteValid: Boolean = validateLengthAndEmpty(websiteInput, websiteError)


        if(isUsernameValid and isPasswordValid and isEmailValid and isPersonNameValid and isPersonPhoneValid and isWebsiteValid) {
            signInMessage.text = "Signup successful"
            viewModel.companies.value?.add(
                CompanyDetails(
                    usernameInput.text.toString(),
                    passwordInput.text.toString(),
                    emailInput.text.toString(),
                )
            )
            progressBar.visibility = View.VISIBLE
            Handler().postDelayed({
                progressBar.visibility = View.INVISIBLE
                val intent = Intent ( this, MainActivity::class.java )
                startActivity(intent)
            }, 1500)
        } else {
            signInMessage.text = ""
        }
    }

    private fun validateUsername(input: TextView, errorMessage: TextView): Boolean {
        for(company in viewModel.companies.value!!) {
            if(company.username.contentEquals(input.text)) {
                errorMessage.text = "Username already exists"
                return false
            }
        }
        errorMessage.text = ""
        return true
    }

    private fun validateEmail(input: TextView, errorMessage: TextView): Boolean {
        for(company in viewModel.companies.value!!) {
            if(company.email.contentEquals(input.text)) {
                errorMessage.text = "Email already exists"
                return false
            }
        }
        errorMessage.text = ""
        return true
    }

    private fun validateLengthAndEmpty(input: TextView, errorMessage: TextView): Boolean {
        return if(input.text.isEmpty()) {
            errorMessage.text = getString(R.string.username_error_string)
            false
        } else {
            if(input.text.length < 5) {
                errorMessage.text = getString(R.string.username_short_error_string)
                false
            } else {
                errorMessage.text = ""
                true
            }
        }
    }

    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
    private fun isEmailValid(input: TextView, errorMessage: TextView): Boolean {
        return if (EMAIL_REGEX.toRegex().matches(input.text.toString())){
            errorMessage.text = ""
            true
        } else {
            errorMessage.text = "Email invalid"
            false
        }
    }

    val PHONE_REG = "\\+?[0-9]{10,13}"
    private fun isPhoneValid(input: TextView, errorMessage: TextView): Boolean {
        return if (PHONE_REG.toRegex().matches(input.text.toString())){
            errorMessage.text = ""
            true
        } else {
            errorMessage.text = "Phone number invalid"
            false
        }
    }
}