package net.hermlon.gcgtimetable.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    TimetableProfile::class,
    TimetableCourse::class,
    TimetableLesson::class,
    TimetableSource::class], version = 1, exportSchema = false)
abstract class TimetableDatabase : RoomDatabase() {

    abstract val timetableProfileDao: TimetableProfileDao
    abstract val timetableSourceDao: TimetableSourceDao

    companion object {

        @Volatile
        private var INSTANCE: TimetableDatabase? = null

        fun getInstance(context: Context): TimetableDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TimetableDatabase::class.java,
                        "timetable_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}