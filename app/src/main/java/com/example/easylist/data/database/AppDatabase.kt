package com.example.easylist.data.database


import com.example.easylist.data.dao.ItemDao
import com.example.easylist.data.dao.ItemListDao
import com.example.easylist.data.entities.Item


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.easylist.data.entities.ItemList

@Database(entities = [Item::class, ItemList::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    abstract fun itemListDao(): ItemListDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Migration von Version 1 auf 2 (falls noch nicht ausgeführt)
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Item ADD COLUMN isClicked INTEGER DEFAULT 0 NOT NULL")
            }
        }

        // Migration von Version 2 auf 3 (Hinzufügen der ItemList-Tabelle und listId in Item)
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Neue Tabelle für ItemList erstellen
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS ItemList (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "name TEXT NOT NULL)"
                )

                // Neue Spalte listId zu Item hinzufügen (mit Default-Wert)
                database.execSQL(
                    "ALTER TABLE Item ADD COLUMN listId INTEGER NOT NULL DEFAULT 1"
                )
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
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3) // Alle Migrationen hinzufügen
                    .fallbackToDestructiveMigration() // Datenbank löschen, falls keine Migration existiert
                    .build().also { INSTANCE = it }
            }
        }
    }
}





