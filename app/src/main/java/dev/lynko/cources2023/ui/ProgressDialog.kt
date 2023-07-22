package dev.lynko.cources2023.ui

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import dev.lynko.cources2023.R

class ProgressDialog(context: Context) {

    private val dialog = AlertDialog.Builder(context)
        .setView(R.layout.layout_progress)
        .setCancelable(false)
        .create()
        .apply {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

    fun show(show: Boolean) {
        if (show) {
            dialog.show()
        } else {
            try {
                 dialog.dismiss()
            } catch (e: Exception) {
                Log.d("l", "exception: $e")
            }
        }
    }

}