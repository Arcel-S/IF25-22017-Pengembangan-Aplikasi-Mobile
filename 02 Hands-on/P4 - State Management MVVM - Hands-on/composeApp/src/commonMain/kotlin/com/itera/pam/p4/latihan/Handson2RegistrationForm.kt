package com.itera.pam.p4.latihan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Latihan 2: Form dengan State Hoisting (slide P4 hal. 30)
// Tugas: Buat reusable TextField component dengan state hoisting —
// komponennya sendiri STATELESS (tidak punya state internal), state-nya
// dipegang oleh parent (RegistrationForm) dan dikirim turun lewat parameter.
//
// Checklist:
// [ ] Stateless LabeledTextField
// [ ] Parameter: value, onValueChange
// [ ] State hoisted ke parent
// [ ] Name dan Email field
// [ ] Preview yang menampilkan "Hello, [name]!"
// [ ] Gunakan 1 komponen untuk kedua field

// Stateless TextField component
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}

// Parent yang menyimpan state
@Composable
fun Handson2Screen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Latihan 2: Form dengan State Hoisting")
        Spacer(modifier = Modifier.height(8.dp))

        LabeledTextField(
            label = "Name",
            value = name,
            onValueChange = { name = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        LabeledTextField(
            label = "Email",
            value = email,
            onValueChange = { email = it }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Hello, $name! Email: $email")
    }
}
