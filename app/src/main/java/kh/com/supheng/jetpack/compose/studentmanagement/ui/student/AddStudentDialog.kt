
package kh.com.supheng.jetpack.compose.studentmanagement.ui.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddStudentDialog(
    onDismiss: () -> Unit,
    onSave: (String, String, String, String) -> Unit
) {
    // 1. Input states
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }

    // 2. Error states
    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var courseError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("Add New Student")
        },

        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Name
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Name") },
                    isError = nameError,
                    singleLine = true,
                    supportingText = {
                        if (nameError) {
                            Text("Name cannot be empty")
                        }
                    }
                )

                // Email
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email") },
                    isError = emailError,
                    singleLine = true,
                    supportingText = {
                        if (emailError) {
                            Text("Enter a valid email address")
                        }
                    }
                )

                // Phone
                OutlinedTextField(
                    value = phone,
                    onValueChange = {
                        phone = it
                        phoneError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Phone") },
                    isError = phoneError,
                    singleLine = true,
                    supportingText = {
                        if (phoneError) {
                            Text("Phone cannot be empty")
                        }
                    }
                )

                // Course
                OutlinedTextField(
                    value = course,
                    onValueChange = {
                        course = it
                        courseError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Course") },
                    isError = courseError,
                    singleLine = true,
                    supportingText = {
                        if (courseError) {
                            Text("Course cannot be empty")
                        }
                    }
                )
            }
        },

        confirmButton = {
            TextButton(
                onClick = {
                    // 3. Validate inputs
                    nameError = name.isBlank()

                    emailError = !android.util.Patterns.EMAIL_ADDRESS
                        .matcher(email.trim())
                        .matches()

                    phoneError = phone.isBlank()

                    courseError = course.isBlank()

                    // 4. Save only if everything is valid
                    if (
                        !nameError &&
                        !emailError &&
                        !phoneError &&
                        !courseError
                    ) {
                        onSave(
                            name.trim(),
                            email.trim(),
                            phone.trim(),
                            course.trim()
                        )
                    }
                }
            ) {
                Text("Save")
            }
        },

        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
