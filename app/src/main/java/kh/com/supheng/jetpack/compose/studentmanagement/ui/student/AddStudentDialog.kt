
package kh.com.supheng.jetpack.compose.studentmanagement.ui.student

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

@Composable
fun AddStudentDialog(
    onDismiss: () -> Unit,
    onSave: (String, String, String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Add Student")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = false
                    },
                    label = { Text("Name") },
                    isError = nameError,
                    supportingText = {
                        if (nameError) {
                            Text("Name cannot be empty")
                        }
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
                    supportingText = {
                        if (emailError) {
                            Text("Enter a valid email")
                        }
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
                    supportingText = {
                        if (phoneError) {
                            Text("Phone cannot be empty")
                        }
                    }
                )

                OutlinedTextField(
                    value = course,
                    onValueChange = { course = it },
                    label = { Text("Course") }
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

                    if (!nameError && !emailError && !phoneError) {
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
