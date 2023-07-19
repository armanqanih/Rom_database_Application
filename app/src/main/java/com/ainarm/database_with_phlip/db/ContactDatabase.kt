package com.ainarm.database_with_phlip.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.ainarm.database_with_phlip.dao.ContactDao
import com.ainarm.database_with_phlip.model.Contact

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase: RoomDatabase() {

    abstract val dao: ContactDao
}