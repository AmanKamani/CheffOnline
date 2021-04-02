package jb.svb.cheffonline.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(messageResId: Int) {
    Snackbar.make(this, messageResId, Snackbar.LENGTH_LONG)
        .show()
}