package kh.com.supheng.jetpack.compose.studentmanagement.`data`.local

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CourseDao_Impl(
  __db: RoomDatabase,
) : CourseDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCourseEntity: EntityInsertAdapter<CourseEntity>

  private val __deleteAdapterOfCourseEntity: EntityDeleteOrUpdateAdapter<CourseEntity>

  private val __updateAdapterOfCourseEntity: EntityDeleteOrUpdateAdapter<CourseEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCourseEntity = object : EntityInsertAdapter<CourseEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `courses` (`id`,`name`,`description`,`duration`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CourseEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.description)
        statement.bindText(4, entity.duration)
      }
    }
    this.__deleteAdapterOfCourseEntity = object : EntityDeleteOrUpdateAdapter<CourseEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `courses` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CourseEntity) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfCourseEntity = object : EntityDeleteOrUpdateAdapter<CourseEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `courses` SET `id` = ?,`name` = ?,`description` = ?,`duration` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CourseEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.description)
        statement.bindText(4, entity.duration)
        statement.bindLong(5, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertCourse(course: CourseEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCourseEntity.insert(_connection, course)
  }

  public override suspend fun deleteCourse(course: CourseEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfCourseEntity.handle(_connection, course)
  }

  public override suspend fun updateCourse(course: CourseEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfCourseEntity.handle(_connection, course)
  }

  public override fun getAllCourses(): Flow<List<CourseEntity>> {
    val _sql: String = "SELECT * FROM courses"
    return createFlow(__db, false, arrayOf("courses")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _result: MutableList<CourseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CourseEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpDuration: String
          _tmpDuration = _stmt.getText(_columnIndexOfDuration)
          _item = CourseEntity(_tmpId,_tmpName,_tmpDescription,_tmpDuration)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
