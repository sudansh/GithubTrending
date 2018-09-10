package com.sudansh.trending.util

import com.sudansh.trending.data.network.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant) {
    companion object {
        private val instant = Executor { it.run() }
    }
}