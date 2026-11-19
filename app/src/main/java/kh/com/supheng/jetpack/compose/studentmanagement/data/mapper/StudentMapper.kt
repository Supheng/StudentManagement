package kh.com.supheng.jetpack.compose.studentmanagement.data.mapper

import kh.com.supheng.jetpack.compose.studentmanagement.data.local.StudentEntity
import kh.com.supheng.jetpack.compose.studentmanagement.data.model.Student
fun StudentEntity.toStudent(): Student {
    return Student(
        id = id,
        name = name,
        email = email,
        phone = phone,
        course = course
    )
}

fun Student.toStudentEntity(): StudentEntity {
    return StudentEntity(
        id = id,
        name = name,
        email = email,
        phone = phone,
        course = course
    )
}