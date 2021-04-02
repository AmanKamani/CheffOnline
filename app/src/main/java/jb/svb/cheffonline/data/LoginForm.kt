package jb.svb.cheffonline.data

import android.util.Patterns
import jb.svb.cheffonline.R

data class LoginForm(
    var email: String = "",
    var password: String = ""
) {

    private var errorMsg: Int = -1

    fun validateEmail(): Boolean {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMsg = R.string.error_email_invalid
            return false
        }
        return true
    }

    fun validatePassword(): Boolean {
        if (password.isEmpty()) {
            errorMsg = R.string.error_password_empty
            return false
        }
        return true
    }

    fun validateLoginForm(): Boolean {
        if (!validateEmail() || !validatePassword()) {
            errorMsg = R.string.error_email_password
            return false
        }
        return true
    }

    fun getErrorMessage(): Int {
        return errorMsg
    }
}