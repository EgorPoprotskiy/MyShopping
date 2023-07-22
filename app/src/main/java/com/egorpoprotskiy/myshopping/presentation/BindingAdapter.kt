package com.egorpoprotskiy.myshopping.presentation

import androidx.databinding.BindingAdapter
import com.egorpoprotskiy.myshopping.R
import com.google.android.material.textfield.TextInputLayout

//6.2 Для выноса логики LiveData в внутрь макета
@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_name)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputNameCount")
fun bindErrorInputNameCount(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_nameCount)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputPriceCount")
fun bindErrorInputPriceCount(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_priceCount)
    } else {
        null
    }
    textInputLayout.error = message
}
