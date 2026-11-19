package kh.com.supheng.jetpack.compose.studentmanagement.data.repository
import javax.inject.Inject
import javax.inject.Singleton

import kh.com.supheng.jetpack.compose.studentmanagement.data.local.UserDao
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.UserEntity
import kh.com.supheng.jetpack.compose.studentmanagement.data.mapper.toUser
import kh.com.supheng.jetpack.compose.studentmanagement.data.model.User
import kh.com.supheng.jetpack.compose.studentmanagement.security.PasswordHasher

@Singleton
class AuthRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<User> {

        val normalizedEmail = email.trim().lowercase()

        if (userDao.getUserByEmail(normalizedEmail) != null) {
            return Result.failure(
                Exception("Email already registered")
            )
        }

        val salt = PasswordHasher.generateSalt()

        val passwordHash = PasswordHasher.hashPassword(
            password = password,
            salt = salt
        )

        val user = UserEntity(
            name = name.trim(),
            email = normalizedEmail,
            passwordHash = passwordHash,
            salt = salt
        )

        return try {
            val userId = userDao.insertUser(user).toInt()

            Result.success(
                user.copy(id = userId).toUser()
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<User> {

        val normalizedEmail = email.trim().lowercase()

        val user = userDao.getUserByEmail(normalizedEmail)
            ?: return Result.failure(
                Exception("Invalid email or password")
            )

        val enteredHash = PasswordHasher.hashPassword(
            password = password,
            salt = user.salt
        )

        if (enteredHash != user.passwordHash) {
            return Result.failure(
                Exception("Invalid email or password")
            )
        }

        return Result.success(user.toUser())
    }
}
