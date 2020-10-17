package com.inbedroom.couriertracking.customview

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.inbedroom.couriertracking.R
import kotlinx.android.synthetic.main.info_text_view.view.*

@Suppress("DEPRECATION")
class InfoTextView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.info_text_view, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.InfoTextView)

        customInfoLabel.text = attributes.getString(R.styleable.InfoTextView_textLabel)
        customInfoValue.text = attributes.getString(R.styleable.InfoTextView_textValue)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            customInfoLabel.setTextColor(
                attributes.getColor(
                    R.styleable.InfoTextView_labelColor,
                    context.getColor(R.color.defaultTextColor)
                )
            )
            customInfoValue.setTextColor(
                attributes.getColor(
                    R.styleable.InfoTextView_valueColor,
                    context.getColor(R.color.defaultTextColor)
                )
            )
        } else {
            customInfoLabel.setTextColor(
                attributes.getColor(
                    R.styleable.InfoTextView_labelColor,
                    resources.getColor(R.color.defaultTextColor)
                )
            )
            customInfoValue.setTextColor(
                attributes.getColor(
                    R.styleable.InfoTextView_valueColor,
                    resources.getColor(R.color.defaultTextColor)
                )
            )
        }

        attributes.recycle()
    }

    fun setValueText(labelText: String?) {
        if (!labelText.isNullOrEmpty()) {
            customInfoValue.text = labelText
        }
    }

    fun setValueColor(id: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            customInfoValue.setTextColor(context.getColor(id))
        } else {
            customInfoValue.setTextColor(resources.getColor(id))
        }
    }
}