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
public class StudentDao_Impl(
  __db: RoomDatabase,
) : StudentDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfStudentEntity: EntityInsertAdapter<StudentEntity>

  private val __deleteAdapterOfStudentEntity: EntityDeleteOrUpdateAdapter<StudentEntity>

  private val __updateAdapterOfStudentEntity: EntityDeleteOrUpdateAdapter<StudentEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfStudentEntity = object : EntityInsertAdapter<StudentEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `students` (`id`,`name`,`email`,`phone`,`course`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: StudentEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.email)
        statement.bindText(4, entity.phone)
        statement.bindText(5, entity.course)
      }
    }
    this.__deleteAdapterOfStudentEntity = object : EntityDeleteOrUpdateAdapter<StudentEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `students` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: StudentEntity) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfStudentEntity = object : EntityDeleteOrUpdateAdapter<StudentEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `students` SET `id` = ?,`name` = ?,`email` = ?,`phone` = ?,`course` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: StudentEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.email)
        statement.bindText(4, entity.phone)
        statement.bindText(5, entity.course)
        statement.bindLong(6, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertStudent(student: StudentEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfStudentEntity.insert(_connection, student)
  }

  public override suspend fun deleteStudent(student: StudentEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfStudentEntity.handle(_connection, student)
  }

  public override suspend fun updateStudent(student: StudentEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfStudentEntity.handle(_connection, student)
  }

  public override fun getAllStudents(): Flow<List<StudentEntity>> {
    val _sql: String = "SELECT * FROM students"
    return createFlow(__db, false, arrayOf("students")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _columnIndexOfPhone: Int = getColumnIndexOrThrow(_stmt, "phone")
        val _columnIndexOfCourse: Int = getColumnIndexOrThrow(_stmt, "course")
        val _result: MutableList<StudentEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: StudentEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_columnIndexOfEmail)
          val _tmpPhone: String
          _tmpPhone = _stmt.getText(_columnIndexOfPhone)
          val _tmpCourse: String
          _tmpCourse = _stmt.getText(_columnIndexOfCourse)
          _item = StudentEntity(_tmpId,_tmpName,_tmpEmail,_tmpPhone,_tmpCourse)
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
