package com.codecodecoffee.android.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codecodecoffee.android.sharedmodel.Post


const val VERSION = 1

@Database(
    entities = [Post::class],
    version = VERSION
)
abstract class AppDatabase : RoomDatabase() {

}