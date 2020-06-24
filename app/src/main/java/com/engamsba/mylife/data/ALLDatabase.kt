package com.engamsba.mylife.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.engamsba.mylife.data.dao.NoteDataBaseDao
import com.engamsba.mylife.data.model.Note


@Database(
    entities = [Note::class],
    version = 2,
    exportSchema = false
)
abstract class ALLDatabase: RoomDatabase(){

          abstract val noteDataBaseDao: NoteDataBaseDao

         companion object{

             @Volatile
             private var INSTANCE: ALLDatabase? = null

             fun getInstance(context: Context): ALLDatabase {

                 synchronized(this){
                     var instance =
                         INSTANCE

                     if (instance == null){
                         instance = Room.databaseBuilder(
                             context.applicationContext,
                             ALLDatabase::class.java,
                             "note_database"
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

//    abstract fun getNoteDatabaseDao(): NoteDataBaseDao
////    abstract val taskDatabaseDao: TasksDataBaseDao
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: ALLDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
//            INSTANCE ?: buildDatabse(context).also {
//                INSTANCE = it
//            }
//        }
//
//        private fun buildDatabse(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            ALLDatabase::class.java,
//            "database_all"
//        ).build()
//    }

//        fun getInstance(application: Application): ALLDatabase {
//
//            synchronized(this){
//                var instance =
//                    INSTANCE
//
//                if (instance == null){
//                    instance = Room.databaseBuilder(
//                        application.applicationContext,
//                        ALLDatabase::class.java,
//                        "note_database"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//
//    }
