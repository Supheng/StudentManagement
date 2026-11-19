package kh.com.supheng.jetpack.compose.studentmanagement.ui.course

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import kh.com.supheng.jetpack.compose.studentmanagement.data.local.CourseEntity

@Composable
fun EditCourseDialog(
    course: CourseEntity,
    onDismiss: () -> Unit,
    onSave: (CourseEntity) -> Unit
) {

    var name by remember(course.id) {
        mutableStateOf(course.name)
    }

    var description by remember(course.id) {
        mutableStateOf(course.description)
    }

    var duration by remember(course.id) {
        mutableStateOf(course.duration)
    }
    var nameError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }
    var durationError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("Edit Course")
        },

        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = false
                    },
                    isError = nameError,
                    singleLine = false,
                    label = { Text("Course Name") },
                    supportingText = {
                        if (nameError){
                            Text("Name cannot be empty")
                        }
                    }
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it
                        descriptionError = false
                    },
                    isError = descriptionError,
                    singleLine = false,
                    label = { Text("Description") },
                    supportingText = {
                        if (descriptionError){
                            Text("description cannot be empty")
                        }
                    }
                )

                OutlinedTextField(
                    value = duration,
                    onValueChange = {
                        duration = it
                        durationError = false
                    },
                    singleLine = false,
                    isError = durationError,
                    label = { Text("Duration") },
                    supportingText = {
                        if (durationError){
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

                    if (
                        !nameError &&
                        !descriptionError &&
                        !durationError
                    ) {
                        val updatedCourse = course.copy(
                            name = name.trim(),
                            description = description.trim(),
                            duration = duration.trim()
                        )

                        onSave(updatedCourse)
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