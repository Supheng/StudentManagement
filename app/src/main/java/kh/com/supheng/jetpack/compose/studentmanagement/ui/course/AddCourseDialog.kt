
package kh.com.supheng.jetpack.compose.studentmanagement.ui.course

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
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Course Name") },
                    isError = nameError,
                    singleLine = true,
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
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Description") },
                    isError = descriptionError,
                    singleLine = true,
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
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Duration") },
                    isError = durationError,
                    singleLine = true,
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
