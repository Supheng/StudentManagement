
package kh.com.supheng.jetpack.compose.studentmanagement.ui.course

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*

@Composable
fun AddCourseDialog(
    onDismiss: () -> Unit,
    onSave: (String, String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }
    var durationError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Add Course")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = false
                    },
                    label = { Text("Course Name") },
                    isError = nameError,
                    supportingText = {
                        if (nameError) {
                            Text("Course name cannot be empty")
                        }
                    }
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it
                        descriptionError = false
                    },
                    label = { Text("Description") },
                    isError = descriptionError,
                    supportingText = {
                        if (descriptionError) {
                            Text("Description cannot be empty")
                        }
                    }
                )

                OutlinedTextField(
                    value = duration,
                    onValueChange = {
                        duration = it
                        durationError = false
                    },
                    label = { Text("Duration") },
                    isError = durationError,
                    supportingText = {
                        if (durationError) {
                            Text("Duration cannot be empty")
                        }
                    }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    nameError = name.isBlank()
                    descriptionError = description.isBlank()
                    durationError = duration.isBlank()

                    if (!nameError && !descriptionError && !durationError) {
                        onSave(
                            name.trim(),
                            description.trim(),
                            duration.trim()
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
