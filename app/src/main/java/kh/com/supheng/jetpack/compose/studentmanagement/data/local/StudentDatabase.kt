package kh.com.supheng.jetpack.compose.studentmanagement.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [StudentEntity::class,
        CourseEntity::class],
    version = 2,
    exportSchema = false
)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun courseDao() : CourseDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                CREATE TABLE IF NOT EXISTS `courses` (
                    `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    `name` TEXT NOT NULL,
                    `description` TEXT NOT NULL,
                    `duration` TEXT NOT NULL
                )
                """.trimIndent()
                )
            }
        }
    }
}