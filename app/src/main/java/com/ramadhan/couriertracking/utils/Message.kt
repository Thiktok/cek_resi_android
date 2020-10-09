package com.ramadhan.couriertracking.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.ramadhan.couriertracking.R
import com.ramadhan.couriertracking.customview.DialogEditTitle

object Message {
    fun alert(context: Context, msg: String) {
        val dialogBuilder = AlertDialog.Builder(context)

        dialogBuilder.setTitle(context.getString(R.string.alert_title))
        dialogBuilder.setMessage(msg)

        dialogBuilder.setPositiveButton(R.string.ok_button) { _, _ ->
        }

        dialogBuilder.show()
    }

    fun alertEditText(
        fragmentManager: FragmentManager,
        positiveButton: DialogEditTitle.DialogListener
    ) {
        val dialog = DialogEditTitle(positiveButton)
        val fragmentTransaction = fragmentManager.beginTransaction()
        val prevDialog = fragmentManager.findFragmentByTag("dialog")
        if (prevDialog != null) {
            fragmentTransaction.remove(prevDialog)
        }
        fragmentTransaction.addToBackStack(null)
        dialog.show(fragmentTransaction, "dialog")
    }

    fun notify(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }
}