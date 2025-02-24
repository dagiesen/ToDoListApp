package com.example.easylist.data.database


import com.example.easylist.data.dao.ItemDao
import com.example.easylist.data.dao.ItemListDao
import com.example.easylist.data.entities.Item


import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.easylist.data.entities.ItemList

@Database(
    version = 1,
    entities = [Item::class, ItemList::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun itemListDao(): ItemListDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Methode, um die Datenbank zu erhalten
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Löscht die DB bei Versionsänderung
                    .build().also { INSTANCE = it }
            }
        }
    }
}



