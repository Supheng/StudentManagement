
package kh.com.supheng.jetpack.compose.studentmanagement.ui.student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kh.com.supheng.jetpack.compose.studentmanagement.R
import kh.com.supheng.jetpack.compose.studentmanagement.data.model.Student
import kh.com.supheng.jetpack.compose.studentmanagement.viewmodel.StudentViewModel

@Composable
fun StudentScreen(
    viewModel: StudentViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    var showAddDialog by remember {
        mutableStateOf(false)
    }

    var studentToDelete by remember {
        mutableStateOf<Student?>(null)
    }

    var studentToEdit by remember {
        mutableStateOf<Student?>(null)
    }

    val filteredStudents = uiState.students.filter { student ->
        student.name.contains(uiState.searchQuery, ignoreCase = true) ||
                student.email.contains(uiState.searchQuery, ignoreCase = true) ||
                student.course.contains(uiState.searchQuery, ignoreCase = true)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    showAddDialog = true
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.add_student),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            // Header
            Text(
                text = stringResource(R.string.student_count),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(R.string.student_management_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Student statistics card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(22.dp)
                ) {
                    Text(
                        text = stringResource(R.string.total_students_label),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary.copy(
                            alpha = 0.8f
                        ),
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = uiState.students.size.toString(),
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        text = stringResource(R.string.registered_students),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary.copy(
                            alpha = 0.8f
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Search field
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = { query ->
                    viewModel.searchQuery(query)
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(stringResource(R.string.search_students_hint))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search)
                    )
                },
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Section title
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.student_list),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(
                        R.string.student_count,
                        filteredStudents.size
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Student content
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                uiState.errorMessage != null -> {
                    Text(
                        text = uiState.errorMessage
                            ?: stringResource(R.string.something_went_wrong),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                filteredStudents.isEmpty() -> {
                    EmptyStudentState(
                        isSearching = uiState.students.isNotEmpty()
                    )
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(
                            bottom = 100.dp
                        )
                    ) {
                        items(
                            items = filteredStudents,
                            key = { student -> student.id }
                        ) { student ->

                            StudentCard(
                                student = student,
                                onEdit = {
                                    studentToEdit = student
                                },
                                onDelete = {
                                    studentToDelete = student
                                }
                            )
                        }
                    }
                }
            }
        }

        // Add Student Dialog
        if (showAddDialog) {
            AddStudentDialog(
                onDismiss = {
                    showAddDialog = false
                },
                onSave = { name, email, phone, course ->

                    val student = Student(
                        name = name,
                        email = email,
                        phone = phone,
                        course = course
                    )

                    viewModel.addStudent(student)
                    showAddDialog = false
                }
            )
        }

        // Edit Student Dialog
        if (studentToEdit != null) {
            EditStudentDialog(
                student = studentToEdit!!,
                onDismiss = {
                    studentToEdit = null
                },
                onSave = { updatedStudent ->
                    viewModel.updateStudent(updatedStudent)
                    studentToEdit = null
                }
            )
        }

        // Delete Confirmation
        if (studentToDelete != null) {
            AlertDialog(
                onDismissRequest = {
                    studentToDelete = null
                },
                title = {
                    Text(stringResource(R.string.delete_student_title))
                },
                text = {
                    Text(
                        stringResource(
                            R.string.confirm_delete_student,
                            studentToDelete?.name.orEmpty()
                        )
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            studentToDelete?.let { student ->
                                viewModel.deleteStudent(student)
                            }
                            studentToDelete = null
                        }
                    ) {
                        Text(
                            stringResource(R.string.delete),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            studentToDelete = null
                        }
                    ) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            )
        }
    }
}

// Student Card
@Composable
fun StudentCard(
    student: Student,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Student initials
                Surface(
                    modifier = Modifier.size(52.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = student.name
                                .trim()
                                .split(Regex("\\s+"))
                                .filter { it.isNotEmpty() }
                                .take(2)
                                .mapNotNull { it.firstOrNull() }
                                .joinToString("")
                                .uppercase()
                                .ifEmpty { "?" },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = student.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = student.course,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.email),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = student.email,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(R.string.phone),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = student.phone,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onEdit
                ) {
                    Text(stringResource(R.string.edit))
                }

                TextButton(
                    onClick = onDelete
                ) {
                    Text(
                        stringResource(R.string.delete),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

// Empty State
@Composable
fun EmptyStudentState(
    isSearching: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = if (isSearching) {
                stringResource(R.string.no_matching_students)
            } else {
                stringResource(R.string.no_students_yet)
            },
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (isSearching) {
                stringResource(R.string.try_different_keyword)
            } else {
                stringResource(R.string.add_first_student)
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
