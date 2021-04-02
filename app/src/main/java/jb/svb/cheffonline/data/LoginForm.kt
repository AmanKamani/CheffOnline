package jb.svb.cheffonline.data

import android.util.Patterns

data class LoginForm(
    var email: String = "",
    var password: String = ""
) {

    private var errorMsg: String = ""

    fun validateEmail(): Boolean {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMsg = "You entered invalid email address"
            return false
        }
        return true
    }

    fun validatePassword(): Boolean {
        if (password.isEmpty()) {
            errorMsg = "Password can't be blank"
            return false
        }
        return true
    }

    fun validateLoginForm(): Boolean {
        if (!validateEmail() || !validatePassword()) {
            errorMsg = "Please enter valid email and a password"
            return false
        }
        return true
    }

    fun getErrorMessage(): String {
        return errorMsg
    }
}