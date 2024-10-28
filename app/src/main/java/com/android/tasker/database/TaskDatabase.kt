package com.android.tasker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.tasker.converters.TypeConverter
import com.android.tasker.dao.TaskDao
import com.android.tasker.models.Task

// Task Database
@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
// Type Converter
@TypeConverters(TypeConverter::class)
abstract class TaskDatabase : RoomDatabase() {

    abstract val taskDao : TaskDao

    // Companion Object
    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null
        fun getInstance(context: Context): TaskDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_db"
                ).build().also {
                    INSTANCE = it
                }
            }

        }
    }

}