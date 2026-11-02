package kh.com.supheng.jetpack.compose.studentmanagement.ui.course

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
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

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("Edit Course")
        },

        text = {
            Column {

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Course Name") }
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )

                OutlinedTextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("Duration") }
                )
            }
        },

        confirmButton = {
            TextButton(
                onClick = {

                    if (
                        name.isNotBlank() &&
                        description.isNotBlank() &&
                        duration.isNotBlank()
                    ) {

                        val updatedCourse = course.copy(
                            name = name,
                            description = description,
                            duration = duration
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