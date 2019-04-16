package com.sudansh.trending.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import com.google.android.material.snackbar.Snackbar
import android.view.View

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (T) -> Unit) {
	this.observe(owner, Observer { it ->
		it?.let {
			observer(it)
		}
	})
}

fun <X, Y> LiveData<X>.switch(transform: (x: X) -> LiveData<Y>): LiveData<Y> {
	return Transformations.switchMap(this) {
		return@switchMap transform(it)
	}
}

inline fun View.snack(message: String,
	length: Int = Snackbar.LENGTH_LONG,
	action: Snackbar.() -> Unit = {}) {
	val snack = Snackbar.make(this, message, length)
	snack.action()
	snack.show()
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
	setAction(action, listener)
	color?.let { setActionTextColor(color) }
}
