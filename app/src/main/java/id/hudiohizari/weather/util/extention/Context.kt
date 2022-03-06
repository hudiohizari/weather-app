package id.hudiohizari.weather.util.extention

import android.content.Context
import android.widget.Toast
import id.hudiohizari.weather.R

fun Context.toast(message: String?) {
    Toast.makeText(
        this,
        message ?: getString(R.string.emptyText),
        Toast.LENGTH_SHORT
    ).show()
}