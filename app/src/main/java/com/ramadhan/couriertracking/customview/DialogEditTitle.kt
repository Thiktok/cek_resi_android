package com.ramadhan.couriertracking.customview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ramadhan.couriertracking.R
import kotlinx.android.synthetic.main.custom_dialog_ask.*

class DialogEditTitle(private val positiveListener: DialogListener?): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_dialog_ask, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDialogNegative.setOnClickListener {
            dismiss()
        }
        btnDialogPositive.setOnClickListener {
            val text = etDialogContent.text
            if (text.isNullOrEmpty()) {
                etDialogContent.error = "Must fill this field to make change"
                etDialogContent.requestFocus()
            }
            else{
                if (positiveListener != null){
                    positiveListener.onPositiveDialog(text.toString())
                }else{
                    val dialogListener = activity as DialogListener
                    dialogListener.onPositiveDialog(text.toString())
                }
                dismiss()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        val params = dialog?.window?.attributes

        params?.width = ViewGroup.LayoutParams.MATCH_PARENT
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT

        dialog?.window?.attributes = params
    }

    interface DialogListener{
        fun onPositiveDialog(text: String?)
    }
}