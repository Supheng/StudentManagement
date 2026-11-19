package kh.com.supheng.jetpack.compose.studentmanagement.data.mapper

import kh.com.supheng.jetpack.compose.studentmanagement.data.local.UserEntity
import kh.com.supheng.jetpack.compose.studentmanagement.data.model.User

fun UserEntity.toUser(): User {
    return User(
        id = id,
        name = name,
        email = email
    )
}
