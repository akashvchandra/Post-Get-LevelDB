package com.example.testleveldb.data

import android.content.Context
import androidx.lifecycle.ViewModel
import org.iq80.leveldb.DB
import org.iq80.leveldb.Options
import org.iq80.leveldb.impl.Iq80DBFactory.*
import java.io.File

class LevelDBViewModel(private val appContext: Context)  : ViewModel() {
    private var db: DB? = null

    init {
        val databasePath = appContext.filesDir.absolutePath + File.separator + "my_database"
        openOrCreateDatabase(databasePath)
    }

    fun openOrCreateDatabase(databasePath: String) {
        val options = Options()
        options.createIfMissing(true)
        db = factory.open(File(databasePath), options)
    }

    fun writeToDatabase(key: String, value: String) {
        db?.put(bytes(key), bytes(value))
    }

    fun readFromDatabase(key: String): String? {
        val valueBytes = db?.get(bytes(key))
        return valueBytes?.let { String(it) }
    }

    fun closeDatabase() {
        db?.close()
    }

    override fun onCleared() {
        super.onCleared()
        closeDatabase()
    }
}
