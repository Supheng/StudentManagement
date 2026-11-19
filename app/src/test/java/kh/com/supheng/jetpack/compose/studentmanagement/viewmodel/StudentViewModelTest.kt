package kh.com.supheng.jetpack.compose.studentmanagement.viewmodel

import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kh.com.supheng.jetpack.compose.studentmanagement.data.model.Student
import kh.com.supheng.jetpack.compose.studentmanagement.data.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse

@OptIn(ExperimentalCoroutinesApi::class)
class StudentViewModelTest {

    private lateinit var repository: StudentRepository
    private lateinit var viewModel: StudentViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        repository = mockk()

        every { repository.getAllStudents() } returns flowOf(emptyList())

        viewModel = StudentViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun addStudent_success() = runTest {

        // Arrange
        val student = Student(
            id = 1,
            name = "John",
            email = "john@gmail.com",
            phone = "012345678",
            course = "Android Development"
        )

        coEvery {
            repository.insertStudent(student)
        } just Runs

        // Act
        viewModel.addStudent(student)

        // Assert
        coVerify(exactly = 1) {
            repository.insertStudent(student)
        }

        assertEquals(
            "Student added successfully",
            viewModel.uiState.value.successMessage
        )

        assertFalse(viewModel.uiState.value.isLoading)
    }
    @Test
    fun addStudent_failure() = runTest {

        // Arrange
        val student = Student(
            id = 1,
            name = "John",
            email = "john@gmail.com",
            phone = "012345678",
            course = "Android Development"
        )

        coEvery {
            repository.insertStudent(student)
        } throws RuntimeException("Database error")

        // Act
        viewModel.addStudent(student)

        // Assert
        assertEquals(
            "Database error",
            viewModel.uiState.value.errorMessage
        )

        assertEquals(
            null,
            viewModel.uiState.value.successMessage
        )

        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun deleteStudent_success() = runTest {

        // Arrange
        val student = Student(
            id = 1,
            name = "John",
            email = "john@gmail.com",
            phone = "012345678",
            course = "Android Development"
        )

        coEvery {
            repository.deleteStudent(student)
        } just Runs

        // Act
        viewModel.deleteStudent(student)

        // Assert
        coVerify(exactly = 1) {
            repository.deleteStudent(student)
        }

        assertEquals(
            "Delete successfully",
            viewModel.uiState.value.successMessage
        )

        assertFalse(viewModel.uiState.value.isLoading)
    }

}
