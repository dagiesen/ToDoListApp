package com.example.easylist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Item::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Migration von Version 1 auf Version 2 (falls notwendig)
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Item ADD COLUMN isClicked INTEGER DEFAULT 0 NOT NULL")
            }
        }

        // Methode, um die Datenbank zu erhalten
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(MIGRATION_1_2)  // Migration hinzufügen
                    .fallbackToDestructiveMigration()  // Datenbank löschen, wenn keine Migration vorhanden ist
                    .build().also { INSTANCE = it }
            }
        }
    }
}





