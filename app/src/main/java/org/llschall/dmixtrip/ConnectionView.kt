package org.llschall.dmixtrip

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ConnectionView() {

    val logs = remember {
        mutableStateListOf("-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#")
    }

    Column {
        for (i in 0..<logs.size) {
            Text(
                text = logs[i],
                fontSize = 18.sp
            )
        }
        Button(onClick = {
            logs.add("> " + System.currentTimeMillis() + " started")
            logs.removeFirst()
        }) {
            Text(
                text = "Start",
                fontSize = 28.sp
            )
        }
    }
}