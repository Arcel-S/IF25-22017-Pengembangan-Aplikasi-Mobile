package com.itera.pam.p1.solusi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itera.pam.p1.getPlatformName

@Composable
fun Handson1ScreenSolusi() {
    val platform = getPlatformName()
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Solusi 1: Expect/Actual")
        Text("Halo dari $platform!")
    }
}

@Composable
fun Handson2ScreenSolusi() {
    var count by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Solusi 2: Counter")
        Text("Nilai: $count")

        Row {
            Button(onClick = { count++ }) {
                Text("+")
            }
            Button(onClick = { count-- }) {
                Text("-")
            }
        }
    }
}

@Composable
fun Handson3ScreenSolusi() {
    Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nama: Mahasiswa ITERA")
            Text("NIM: 122140000")
            Row {
                Text("Platform: ")
                Text(getPlatformName())
            }
        }
    }
}
