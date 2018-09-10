package com.sudansh.trending.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.Transformations
import android.support.design.widget.Snackbar
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
