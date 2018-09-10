package com.sudansh.trending.util

import android.databinding.BindingConversion
import android.view.View

@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int {
	return if (visible) View.VISIBLE else View.GONE
}