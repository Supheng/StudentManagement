
package kh.com.supheng.jetpack.compose.studentmanagement.ui.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kh.com.supheng.jetpack.compose.studentmanagement.data.model.Student


@Composable
fun EditStudentDialog(
    student: Student,
    onDismiss: () -> Unit,
    onSave: (Student) -> Unit
) {
    // Start with existing student information
    var name by remember(student.id) {
        mutableStateOf(student.name)
    }

    var email by remember(student.id) {
        mutableStateOf(student.email)
    }

    var phone by remember(student.id) {
        mutableStateOf(student.phone)
    }

    var course by remember(student.id) {
        mutableStateOf(student.course)
    }

    // Error states
    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var courseError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("Edit Student")
        },

        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = false
                    },
                    label = { Text("Name") },
                    isError = nameError,
                    singleLine = true,
                    supportingText = {
                        if (nameError) Text("Name cannot be empty")
                    }
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = false
                    },
                    label = { Text("Email") },
                    isError = emailError,
                    singleLine = true,
                    supportingText = {
                        if (emailError) Text("Enter a valid email")
                    }
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = {
                        phone = it
                        phoneError = false
                    },
                    label = { Text("Phone") },
                    isError = phoneError,
                    singleLine = true,
                    supportingText = {
                        if (phoneError) Text("Phone cannot be empty")
                    }
                )

                OutlinedTextField(
                    value = course,
                    onValueChange = {
                        course = it
                        courseError = false
                    },
                    label = { Text("Course") },
                    isError = courseError,
                    singleLine = true,
                    supportingText = {
                        if (courseError) Text("Course cannot be empty")
                    }
                )
            }
        },

        confirmButton = {
            TextButton(
                onClick = {
                    nameError = name.isBlank()

                    emailError = !android.util.Patterns.EMAIL_ADDRESS
                        .matcher(email.trim())
                        .matches()

                    phoneError = phone.isBlank()
                    courseError = course.isBlank()

                    if (
                        !nameError &&
                        !emailError &&
                        !phoneError &&
                        !courseError
                    ) {
                        onSave(
                            student.copy(
                                name = name.trim(),
                                email = email.trim(),
                                phone = phone.trim(),
                                course = course.trim()
                            )
                        )
                    }
                }
            ) {
                Text("Update")
            }
        },

        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

