package com.bignerdranch.android.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.criminalintent.database.CrimeDataBase
import com.bignerdranch.android.criminalintent.database.migration_1_2
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

class CrimeRepository private constructor(context: Context){

    companion object{
        private const val DATABASE_NAME = "crime-database"
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun  get(): CrimeRepository{
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }

    private val database: CrimeDataBase = Room.databaseBuilder(
        context.applicationContext,
        CrimeDataBase::class.java,
        DATABASE_NAME,
//    ).build()
    ).addMigrations(migration_1_2)
        .build()

    private val crimeDao = database.crimeDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()
    fun getCrimeById(id: UUID): LiveData<Crime?> = crimeDao.getCrimeById(id)

    fun updateCrime(crime: Crime) {
        executor.execute {
            crimeDao.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime) {
        executor.execute {
            crimeDao.addCrime(crime)
        }
    }
}