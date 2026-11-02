
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
import kh.com.supheng.jetpack.compose.studentmanagement.data.Model.Student

@Composable
fun EditStudentDialog(
    student: Student,
    onDismiss: () -> Unit,
    onSave: (Student) -> Unit
) {
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

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text("Edit Student")
        },

        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") }
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
                    val updatedStudent = student.copy(
                        name = name,
                        email = email,
                        phone = phone,
                        course = course
                    )

                    onSave(updatedStudent)
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
