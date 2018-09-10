package com.sudansh.trending

import android.arch.core.executor.testing.CountingTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.sudansh.trending.data.db.AppDatabase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class RepoDaoTest : KoinTest {

    @Rule
    @JvmField
    val countingTaskExecutorRule = CountingTaskExecutorRule()
    private lateinit var _db: AppDatabase
    private val db: AppDatabase
        get() = _db

    @Before
    fun initDb() {
        _db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        countingTaskExecutorRule.drainTasks(10, TimeUnit.SECONDS)
        _db.close()
    }

    @Test
    fun testFindAll() {
        val dao = db.repoDao()
        val entity = createRepo("foo", "bar", "zol", 1)
        dao.insert(entity)
        val entity2 = createRepo("foos", "bars", "zols", 2)
        dao.insert(entity2)
        val resultList = dao.findByName("foos").liveValue()

        assertEquals(entity2, resultList)
    }

}